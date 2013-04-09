<%-- 
    Document   : admin
    Created on : 13-03-2013, 11:42:41
    Author     : stephen
--%>


<%@page import="java.util.List"%>
<%@page import="dk.itu.iwxj.lapizzia.model.User" %>
<%@page import="dk.itu.iwxj.lapizzia.data.ProductDataBean" %>
<%@page import="dk.itu.iwxj.lapizzia.model.Product" %>


<%
    if (session.getAttribute("message") == null) {
      session.setAttribute("message", "");
    }
    
    int currentPage = 1;

    if(request.getParameter("current_page") != null) {
        currentPage = Integer.parseInt(request.getParameter("current_page"));
    }
%>

<!-- Redirect to the login page if 1) user not logged in, 2) user role is not admin ( >=10) -->
<%
    boolean redirect = false;

    User user = (User) session.getAttribute("user");
    System.out.println("User:" + user);
    if (user != null) {
        if (user.getRole() < 10) {
            redirect = true;
        }
    }
    else {
        redirect = true;
    }
    
    if (redirect) {
        response.sendRedirect("login.jsp");
    }
%>

<!-- Get the list of pizze to show -->
<%
    List<Product> pizze =  ProductDataBean.getInstance().list((currentPage-1)*10,10);
    int totalPizze = ProductDataBean.getInstance().numberOfProducts();
    int totalPages = totalPizze / 10;    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza management interface</title>
    </head>
    <body>
        <h1>Buongiorno!</h1>
        <div>
            <%= session.getAttribute("message") %>
        </div>
        
        <div>
            <h2>Pizze a vendite (<%= pizze.size() %> in tutti):</h2>
            
            <p>
            <table class="pizzalist">
            <tr><th>Pizza</th><th>Description</th><th>Price</th><th></th></tr>
            <%        
              int pizzeShown = 0;                 
              for (Product p : pizze) {
                  pizzeShown++;
                  out.println("<tr>");
                  out.println("<td>" + p.getName() + "</td>");
                  out.println("<td>" + p.getDescription() + "</td>");
                  out.println("<td>" + p.getPrice() + "</td>");
                  
                  out.println("<td><form action=\"Products\" method=\"post\">");
                  out.println("<input type=\"hidden\" name=\"id\" value=\"" + p.getId() +"\">");
                  out.println("<input type=\"submit\" value=\"Delete\" name=\"action\">");
                  out.println("</form></td>");
                  
                  out.println("</tr>");
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
        
        </div>
        
        <div>
            <h2>Add a new Pizza:</h2>
            <form action="Products" method="post">
                <br/><strong>Name:</strong><br />
                <input type="text" name="name" />
                
                <br/><strong>Description</strong><br />
                <input type="text" name="description" />
                
                <br/><strong>Price</strong><br />
                <input type="text" name="price" size="4" />
                
                <br/><input type="submit" value="Add" name="action"/>
            </form>
        </div>
    </body>
</html>
