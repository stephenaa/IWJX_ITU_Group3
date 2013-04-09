<%-- 
    Document   : top.jsp
    Created on : 08-04-2013, 14:47:40
    Author     : vanl
--%>

<%@page import="dk.itu.iwxj.lapizzia.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! public String message;%>
<%! public User user;%>
<%
    message = (String) session.getAttribute("message");
    if (message == null) {
        session.setAttribute("message", "");

    }
    User user = (User) session.getAttribute("user");

%>
<!DOCTYPE html>
<html>
    <head>
        <script src="scripts/pizzalib.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>La Pizzashop </title>
        <link href="common/stylesheet.css" rel="stylesheet" type="text/css" />      
    </head>
    <body>
        <table width="70%" border="1" align="center" style="height:700; background-color: #FFF;" cellpadding="0" cellspacing="0">   
            <tr><td>&nbsp;</td></tr>
            <tr>
                <td height="80">
                    <table width="70%" height="80" border="0" style="background-image:url(img/la_top.jpg)">
                        <tr><td>&nbsp;</td></tr>
                    </table>
                </td>
            </tr>
            <tr><td>
                    <table><tr><td><div>
                               <span style="margin-right: 10px;">
                                        |&nbsp;&nbsp;<a href='/PizzaShop/Purchase'>Pizze</a>
                               <% if (user == null) {
                                    out.println("|&nbsp;&nbsp;<a href='/PizzaShop/Login'>Log ind</a>&nbsp;&nbsp;|");
                                   } else {
                                    out.println("|&nbsp;&nbsp;<a href='/PizzaShop/Login'> Hello &nbsp;" + user.getUsername() + "</a>&nbsp;&nbsp;|");
                                }%>

                                <% if (user != null && user.getRole() > 10) {
                                       out.println("<a href=\"admin.jsp\">Administration</a>");
                                 }%>
                                </span>
                    </div></td></tr></table>
                </td></tr>
            <tr>
            <td> 
             <%--Header tabel contains genric content -is cloes in footer.jsp--%>   
            <table width="70%" border="0" align="center" cellspacing="0">
                        <% if (message != null) {
                                out.println("<tr><td class='red'>" + message + "</td></tr>");
                            } else {
                                out.println("<tr><td></td></tr>");
                         }%>
             <tr>
             <td>    
