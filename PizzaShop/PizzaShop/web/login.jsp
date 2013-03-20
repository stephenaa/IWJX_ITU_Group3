<%-- 
    Document   : login
    Created on : Mar 12, 2013, 5:07:18 PM
    Author     : chwu
--%>

<%
    if (session.getAttribute("flash") == null) {
        session.setAttribute("flash", "");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <%= session.getAttribute("flash")%>
        </div>

        <h1>Pizza Login!</h1>
        <form action="Login" method="post">
            Username: <input type="text" name="name" /></p> 
        Password: <input type="password" name="password" />
        <br />
        <input type="submit" value="Login" name="action" />
    </form>
</body>
</html>
