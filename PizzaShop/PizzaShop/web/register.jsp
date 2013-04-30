<%-- 
    Document   : register
    Created on : Mar 12, 2013, 4:49:30 PM
    Author     : chwu
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
 <%@include file="/common/top.jsp" %>
  <table>
        <%
List<String> list = (List)session.getAttribute("Check");        
            if (session.getAttribute("Check") == null) {
                session.setAttribute("Check", "");
            }
if(list!=null){
for (String s: list){
 out.println("<tr>");
 out.println("<td colspan = \"2\" style=\"color:red\">"+s+"</td>");
 out.println("</tr>");
} 
}


%>
 
        <form action="Login" method="post" id="myForm">
           
                    <tr>
                        <td>Username:</td>
                        <td><input id="uname" type="text" name="name"  onkeyup="checkName(this);"/><div id="demoName"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input id="password" type="password" name="password" onkeyup="checkPassword(this);" /><div id="demoPass" /></td>
                    </tr>
                    <tr>
                        <td>Confirm Password: </td>
                        <td><input id="confirmpassword" type="password" name="confirmpassword" onkeyup="checkConPassword(this);"><div id="demoConPass"/></td>
                    </tr>
                    <tr>
                        <td>Address:</td>
                        <td> <input type="text" name="address" /></td>
                    </tr>
                    <tr>
                        <td>Zipcode: </td>
                        <td><input id="zipcode" type="text" name="zipcode" maxlength="4" onkeyup="checkZip(this);"/><div id="demoZip"/></td>
                    </tr>
                    <tr>
                        <td >Phone number: </td>
                        <td><input id="phone" type="text" name="phone" onkeyup="checkPhone(this);"/><div id="demoPhone"/></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input id="emailAddr" type="text" name="email" onkeyup="checkEmail(this);" /><div id="demoEmail"/></td>
                    </tr>
                <tr><td colspan="2"><input type="submit" value="Register" name="action" /></td></tr>
            </form> 
           </table>  
<jsp:include page="/common/footer.jsp"/> 
