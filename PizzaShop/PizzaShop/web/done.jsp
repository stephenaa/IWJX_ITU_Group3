<%-- 
    Document   : newjsp
    Created on : 15-03-2013, 13:13:20
    Author     : vibekelarsen
--%>

<%@page import="dk.itu.iwxj.lapizzia.model.OrderItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="dk.itu.iwxj.lapizzia.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/top.jsp" %>  
<tr><td foodname>&nbsp;</td></tr>
        <h3>Thx, your payment of this order went fine.</h3>
        <table>    
            <% String emptySpace = "&nbsp;";
                Map shopcart = (TreeMap) session.getAttribute("basket");
            %>     
            <tr><th>No</th><th>Pizza</th><th>Qty</th><th>Price</th><th>Total</th></tr>
            <%

                int amountTotalInBasket = 0;
                Iterator entries = shopcart.entrySet().iterator();
                while (entries.hasNext()) {
                    Entry thisEntry = (Entry) entries.next();
                    OrderItem i = (OrderItem) thisEntry.getValue();

                    amountTotalInBasket = amountTotalInBasket + i.getTotalPrice();
                    out.println("<tr>");
                    out.println("<td>" + i.getFkProductId() + "</td>");
                    out.println("<td>" + i.getName() + "</td>");
                    out.println("<td>" + i.getQuantity() + "</td>");
                    out.println("<td>" + i.getPrice() + "</td>");
                    out.println("<td align=\"right\">" + i.getTotalPrice() + "</td>");
                    out.println("<td>" + emptySpace + "</td>");
                    out.println("</tr>");
                }
                out.println("<tr><td colspan=\"5\" align=\"right\">" + amountTotalInBasket + "</td><tr>");

            %>
            <h3>Your order is ready for Dionisio to procces:</h3>
            <tr><td>Within 15 min. plus time to your adresse deliver you order at:</td></tr>
            <%
                out.println("<tr>");
                out.println("<tr><td>" + user.getUsername() + "</td></tr>");
                out.println("<tr><td>" + user.getZipcode() + emptySpace + user.getAddress() + "</td></tr>");
                out.println("<tr><td>" + "we'll call you on : " + user.getPhone() + "</td></tr>");
            %> 

        </table>
  <jsp:include page="/common/footer.jsp"/>  
