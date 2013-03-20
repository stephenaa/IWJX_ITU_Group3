<%-- 
    Document   : index
    Created on : Mar 12, 2013, 4:32:58 PM
    Author     : chwu
--%>


<%@page import="dk.itu.iwxj.lapizzia.model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Pizza Shop!</title>
    </head>
    <body>
        <h1>Welcome to Pizza Shop!</h1>

        <div>
            <% if (user == null) {
                    out.println("<a href=\"login.jsp\">Login</a></br>");
                    out.println("<a href=\"register.jsp\">Register</a>");
                }
            %>

            <% if (user != null && user.getRole() > 10) {
                    out.println("<a href=\"admin.jsp\">Administration</a>");
                } else if (user != null && user.getRole() ==1){ 
                 out.println("<a href=\"Purchase\">Le nostre pizze sono buone nello stomaco</a>");
                }
            %>

        </div>
        <div>
            <img src="img/pisa.jpg" width="683" height="455" alt="pisa"/>
        </div>

    </body>
</html>
