<%-- 
    Document   : register
    Created on : Mar 12, 2013, 4:49:30 PM
    Author     : chwu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            table,th{
              text-align: left;  
            }
            p{
                color: red;
            }
  </style>
    </head>
    <body>
        <h1>Welcome to Pizza Shop!</h1>
        
        
<%
    if (session.getAttribute("Check") == null) {
        session.setAttribute("Check", "");
    }
%>



<p><c:forEach items="${Check}" var="Checklist">
      <c:out value='${Checklist}'/> </br> 
    </c:forEach>
    
            <form action="Login" method="post">
                <table border="0">
            <thead>
                <tr>
                    <th>Username:</th>
                    <td><input type="text" name="name" /></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>Password:</th>
                    <td><input type="text" name="password" /></td>
                </tr>
                <tr>
                    <th>Confirm Password: </th>
                    <td><input type="text" name="confirmpassword" /></td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td> <input type="text" name="address" /></td>
                </tr>
                <tr>
                    <th>Zipcode: </th>
                    <td> <input type="text" name="zipcode" /></td>
                </tr>
                <tr>
                    <th>Phone number: </th>
                    <td><input type="text" name="phone" /></td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td><input type="text" name="email" /></td>
                </tr>
            </tbody>
            </table>  
                <input type="submit" value="Register" name="action" />
            </form>
        

        
       
           
        
    </body>
</html>
