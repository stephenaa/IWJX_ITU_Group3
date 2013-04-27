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
    List<Product> pizze =  ProductDataBean.getInstance().list((currentPage-1)*10,10,0, 999);
    int totalPizze = ProductDataBean.getInstance().numberOfProducts(0, 999);
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
            <table class="pizzalist" id="productTable"">
            <tr><th>Pizza</th><th>Description</th><th>Price</th><th></th></tr>
            <%        
              int pizzeShown = 0;                 
              for (Product p : pizze) {
                  pizzeShown++;
                  out.println("<tr>");
                  out.println("<td>" + p.getName() + "</td>");
                  out.println("<td>" + p.getDescription() + "</td>");
                  out.println("<td><span id=\"" + p.getId() + "\">" + p.getPrice() + "</span>,-</td>");
                  
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

    
    <script src="scripts/pizzalib.js"></script>
    
    <script language="javascript" type="text/javascript" >

        function initPage() {
            table = document.getElementById('productTable');
            var elements = table.getElementsByTagName("span");
        
            for(var i=0; i<elements.length; i++) {            
                
                elements[i].onclick= function(e) {                             
                    var target;
                    if (!e) 
                        var e = window.event;
                    if (e.target) 
                        target = e.target;
                     else 
                         if (e.srcElement) 
                             target = e.srcElement;
                    if (target.nodeType == 3) 
                        target = target.parentNode;
                           
                    var newcontent = document.createElement("input");                    
                    savedOnclick = target.onclick;
                    target.onclick = null;
                    
                    newcontent.type = "text";
                    newcontent.size = 3;
                    newcontent.id = target.id;
                    newcontent.value = target.innerHTML;
                    
                    newcontent.onkeypress = function(e) {
                        if (!e) 
                            var e = window.event;
                        if (e.keyCode == 13) {
                            var target2;
                            if (!e) var e = window.event;
                                if (e.target) target2 = e.target;
                            else
                                if (e.srcElement) target2 = e.srcElement;
                            if (target2.nodeType == 3)
                                target2 = target2.parentNode;
                           
                            var content = document.createElement("span");
                            content.id = target2.id;
                            content.innerHTML = target2.value;
                            content.style.color="blue";
                            target.replaceChild(content,target.childNodes[0]);
                                      
                            http = getHttp();  
                            request = "/PizzaShop/Products/" + target2.id;

                            // The callback passed to sendRequest is being constructed as an anonymous function below
                            xml = "<pizza><price>" + target2.value + "</price></pizza>";
                            sendPUTRequest(http, request, xml, function() {
                                if (http.readyState === 4) {
                                    if (http.status === 200) {                                    
                                        content.style.color = "";
                                        alert("Price was changed");
                                    } else {
                                        content.style.color = "red";
                                        alert("Error changing prize!");
                                    }
                                }
                                target.onclick = savedOnclick;
                            });
                      
                      
                        }
                        
                    };

                    target.replaceChild(newcontent,target.childNodes[0]);

                };      
                
                
            }                
        };

        initPage();
    </script>
    
</html>
