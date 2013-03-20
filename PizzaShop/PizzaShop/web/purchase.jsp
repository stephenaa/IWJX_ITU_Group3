<%-- 
    Document   : purchase.jsp
    Created on : 14-03-2013, 17:09:26
    Author     : vibekelarsen
--%>
<%@page import="dk.itu.iwxj.lapizzia.data.ProductDataBean"%>
<%@page import="dk.itu.iwxj.lapizzia.model.User"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dk.itu.iwxj.lapizzia.model.OrderItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="dk.itu.iwxj.lapizzia.model.Product"%>
<%@page import="java.util.List"%>
<%
    if (session.getAttribute("message") == null) {
        session.setAttribute("message", "");
    }

    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
    }

    int currentPage = 1;
    if(request.getParameter("current_page") != null) {
        currentPage = Integer.parseInt(request.getParameter("current_page"));
    }
   
 %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Pizzashop </title>
    </head>
    <body>
        <h1>La Pizzashop </h1>
        <div>
            <%= session.getAttribute("message")%>
        </div>

        <%
            ProductDataBean pdb = new ProductDataBean();
            List<Product> pizze = pdb.list((currentPage-1)*10,10);
            int totalPizze = pdb.numberOfProducts();
            int totalPages = totalPizze / 10;                
        %>

        <h2><%= pizze.size()%> deliziosa pizza per il vostro stomaco:</h2>
        <table border="1" class="pizzalist">
            <tr><th>No </th><th>Pizza</th><th>Description</th><th>Price</th><th>Quantity</th></tr>
            <%
                int pizzeShown = 0;
                for (Product p : pizze) {
                    pizzeShown++;
                    out.println("<td><form action=\"Purchase\" method=\"post\">");
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<input type=\"hidden\" name=\"product_id\" value=\"" + p.getId() + "\">");
                    out.println("<td>" + p.getName() + "</td>");
                    out.println("<input type=\"hidden\" name=\"name\" value=\"" + p.getName() + "\">");
                    out.println("<td>" + p.getDescription() + "</td>");
                    out.println("<input type=\"hidden\" name=\"description\" value=\"" + p.getDescription() + "\">");
                    out.println("<td>" + p.getPrice() + "</td>");
                    out.println("<input type=\"hidden\" name=\"price\" value=\"" + p.getPrice() + "\">");
                    out.println("<td><input type=\"text\" name=\"qty\" size=\"3\">");
                    out.println("<input type=\"submit\" value=\"AddToBasket\" name=\"action\"></td>");
                    out.println("</tr>");
                    out.println("</form></td>");
                }

            %>                            

        </table>
            
            <p><%= pizzeShown %> of <%= totalPizze %> total Pizzas shown. Page <%= currentPage %> of <%= totalPages %></p>
            <div>
            
            <%
                if(currentPage > 1) {
                    out.print("<div><a href=\"?current_page=" + (currentPage-1) + "\">&lt;&lt; Previous 10</a></div>");
                }
                
                if(currentPage <= totalPages ) {
                    out.print("<div><a href=\"?current_page=" + (currentPage+1) + "\">&gt;&gt; Next 10</a></div>");
                }
            %>
            </div>
            
        <br>
        <hr>     
        <h2>In your basket:  </h2>
        <table border="1">    
            <%
                Map shopcart = (TreeMap) session.getAttribute("basket");
            %>     
            <tr><th>No</th><th>Pizza</th><th>Qty</th><th>Price</th><th>Total</th></tr>
            <%  String emptySpace = "&nbsp;";
                int amountTotalInBasket = 0;

                Iterator entries = shopcart.entrySet().iterator();
                while (entries.hasNext()) {
                    Entry thisEntry = (Entry) entries.next();
                    OrderItem i = (OrderItem) thisEntry.getValue();

                    amountTotalInBasket = amountTotalInBasket + i.getTotalPrice();
                    out.println("<tr>");
                    out.println("<td>" + i.getFkProductId() + emptySpace + "</td>");
                    out.println("<td>" + i.getName() + emptySpace + "</td>");
                    out.println("<td>" + i.getQuantity() + emptySpace + "</td>");
                    out.println("<td>" + i.getPrice() + emptySpace + "</td>");
                    out.println("<td align=\"right\">" + i.getTotalPrice() + emptySpace + "</td>");
                    out.println("<td><form action=\"Purchase\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"name\" value=\"" + i.getName() + "\">");
                    out.println("<input type=\"submit\" value=\"remove\" name=\"action\">");
                    out.println("</form></td>");
                    out.println("</tr>");
                }
                out.println("<tr><td colspan=\"5\" align=\"right\">" + amountTotalInBasket + emptySpace + "</td><tr>");

            %>  
            <form action="Purchase" method="post">
                <tr><td colspan="5" align="right"><input type="submit" name="action" value="checkout"</td></tr>   
            </form> 
        </table>
    </body>
</html>
