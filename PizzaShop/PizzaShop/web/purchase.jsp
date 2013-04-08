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
    <script src="scripts/pizzalib.js"></script>
    
    <script language="javascript" type="text/javascript" >
    
    function initPage() {
        // Install onclick handlers etc.
        
        table = document.getElementById('orderTable');
        var elements = table.getElementsByTagName("a");
        
        for(var i=0; i<elements.length; i++) {            
            elements[i].onmouseover = function(e) {
                var target = (e.target) ? e.target : e.srcElement;
                
                // TODO:
                // I am trying to archive some closure ensurance that the callback
                // has access to the rigt XMLHttpRequest Object.
                // Does this work??
                http = getHttp();  
                request = "/PizzaShop/Products/" + target.id;
                
                // The callback passed to sendRequest is being constructed as an anonymous function below
                sendRequest(http,request, function() {
                    if (http.readyState === 4 && http.status === 200) {
                        div = document.getElementById("descriptionDiv");
                        if(div) {
                            div.innerHTML = http.responseText;
                        }
                        
                    }
                });
            };
      }       
    }    
    
    </script>
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Pizzashop </title>
    </head>
    <body onLoad="initPage();">
        <h1>La Pizzashop </h1>
        <div>
            <%= session.getAttribute("message")%>
        </div>

        <%
            List<Product> pizze = ProductDataBean.getInstance().list((currentPage-1)*10,10);
            int totalPizze = ProductDataBean.getInstance().numberOfProducts();
            int totalPages = totalPizze / 10;                
        %>

        <h2><%= pizze.size()%> deliziosa pizza per il vostro stomaco:</h2>
        <div style="float:left;">
        <table border="0" class="pizzalist" id="orderTable">
            <tr><th>No </th><th>Pizza</th><th>Price</th><th>&nbsp;</th></tr>
            <%
                int pizzeShown = 0;
                for (Product p : pizze) {
                    pizzeShown++;
                    out.println("<td><form action=\"Purchase\" method=\"post\" name=\"orderForm-" + p.getId() + "\">");
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<input type=\"hidden\" name=\"product_id\" value=\"" + p.getId() + "\">");
                    out.println("<td><a href=\"#\" id=\"" + p.getId() + "\">" + p.getName() + "</a></td>");
                    out.println("<td>" + p.getPrice() + ",-</td>");                    
                    out.println("<td><input type=\"text\" name=\"qty\" size=\"3\">");
                    out.println("<input type=\"submit\" value=\"AddToBasket\" name=\"action\"></td>");
                                        
                    out.println("</tr>");
                    out.println("</form></td>");
                }

            %>                            

        </table>
            
        </div>
        
            <!-- The 'descriptionDiv' div will be populated through AJAX -->
            <div id="descriptionDiv" style="float:left;width:20%;padding-left: 3%">
            &nbsp;
            </div>
            
            
            <div style="clear:both;"></div>
            
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
