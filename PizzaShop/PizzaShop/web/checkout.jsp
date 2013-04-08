<%-- 
    Document   : checkout
    Created on : 15-03-2013, 12:24:40
    Author     : vibekelarsen
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="dk.itu.iwxj.lapizzia.model.User" %>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dk.itu.iwxj.lapizzia.model.OrderItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%
    User user = (User) session.getAttribute("user");
    String error = (String) session.getAttribute("message");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="scripts/pizzalib.js"></script>
    
     <script language="javascript" type="text/javascript" >
    
    function initPage() {                
        http = getHttp();          
        request = "<% out.print("/PizzaShop/Users/" + user.getPhone()); %>";
                          
        field = document.getElementById('customer_name');             
        field.value = "Retreiving...";
        // The callback passed to sendRequest is being constructed as an anonymous function below                

        sendRequest(http,request, function() {                    
          if (http.readyState === 4 && http.status === 200) {                        
                  if (window.DOMParser) {
                   parser = new DOMParser();
                   xmlDoc = parser.parseFromString(http.responseText,"text/xml");
                  } else {
                      xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                      xmlDoc.async = false;
                      xmlDoc.loadXML(http.responseText);
                  }
                  document.getElementById('customer_name').value = xmlDoc.getElementsByTagName("name")[0].childNodes[0].nodeValue;                  
                  document.getElementById('customer_address').value = xmlDoc.getElementsByTagName("address")[0].childNodes[0].nodeValue;
                  document.getElementById('customer_zip').value = xmlDoc.getElementsByTagName("zipcode")[0].childNodes[0].nodeValue;                  
              }
        });        
    }
    </script>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Pizzashop </title>
    </head>

    <body onLoad="initPage();">
        <h2>Check out</h2>
        <div>
            <% if (user == null) {
                    out.println("<a href=\"login.jsp\">Login</a>");
                }
            %>
            <% if (user != null && error.isEmpty()) {
                    out.println("<h2> My friend " + user.getUsername() + "</h2>");
                } else if (user != null && !error.isEmpty()) {
                    out.println("<h2>" + error + "</h2>");
                }
            %> 
            <table>    
                <%
                    Map shopcart = (TreeMap) session.getAttribute("basket");
                %>     
                <tr><th>No </th><th>Pizza </th><th>Qty </th><th>Price </th><th>Total</th></tr>
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
                        out.println("<td><form action=\"Purchase\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"name\" value=\"" + i.getName() + "\">");
                        out.println("<input type=\"submit\" value=\"remove\" name=\"action\">");
                        out.println("</form></td>");
                        out.println("</tr>");
                    }
                    out.println("<tr><td colspan=\"5\" align=\"right\">" + amountTotalInBasket + "</td><tr>");

                %>
                <br>

                <form action="Purchase" method="post">
                    <tr><td colspan="5">Card No<input type="text" name="card" size="30"/></td></tr>
                            <%
                                Calendar today = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                out.println("<tr><td colspan=\"5\" align=\"right\"> Devlivery " + sdf.format(today.getTime())
                                        + "<input type=\"hidden\" name=\"delivery\" value=\"" + sdf.format(today.getTime()) + "\"></td>");
                            %>
                    <tr><td colspan="5">Comment? Allergies?</td></tr>
                    <tr><td colspan="5"><textarea name="comment" rows="5" cols="80" maxlength="200"></textarea></td></tr>
                    <tr><td colspan="5" align="right"><input type="submit" name="action" value="pay"</td></tr>   
                </form> 
            </table>
                    
                            <div>
                                Name:<br/>
                                <input type="text" name="customer_name" id="customer_name"/><br/>
                                Address:</br>
                                <input type="text" name="customer_address" id="customer_address"/><br/>
                                Zip:</br>
                                <input type="text" name="customer_zip" id="customer_zip"/><br/>
                            </div>
    </body>
</html>
