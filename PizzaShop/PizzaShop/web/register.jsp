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
            table p {float:left;margin-top: 0px;}
            table td input {float: left;}

        </style>
        <script>
            
            function checkName(uname){
                if((uname.value == null)||(uname.value=="")||(uname.value.length < 1)){
                    var messageName= "<img src=\"img/wrong-icon.jpg\">";
                }  else {
                    var messageName = "<img src=\"img/right-icon.jpg\">"; 
                  
                }
                document.getElementById("demoName").innerHTML = messageName;
            }
            function checkPassword(password){
                if((password.value == null) || (password.value == "") || (password.value.length<6)){
                    var messagePass = "<img src=\"img/wrong-icon.jpg\">";
                    
                } else {
                    var messagePass = "<img src=\"img/right-icon.jpg\">"; 
                }
                document.getElementById("demoPass").innerHTML = messagePass; 
                 
            }
            function checkConPassword(confirmpassword){
                if((confirmpassword.value == null) || (confirmpassword.value == "") || (confirmpassword.value.length<6)||(password.value != confirmpassword.value)){
                    var messagePass = "<img src=\"img/wrong-icon.jpg\">";
                    
                } else {
                    var messagePass = "<img src=\"img/right-icon.jpg\">"; 
                }
                document.getElementById("demoConPass").innerHTML = messagePass; 
            }
            function checkZip(zipcode){
                var RE = /^-{0,1}\d*\.{0,1}\d+$/;
                if (RE.test(myForm.zipcode.value)&&(myForm.zipcode.value.length ==4))  
                {  
                    var messageEmail = "<img src=\"img/right-icon.jpg\">";
                }  else {
                    var messageEmail = "<img src=\"img/wrong-icon.jpg\">";}
                document.getElementById("demoZip").innerHTML = messageEmail;  
            }
            function checkPhone(phone){
                var RE = /^-{0,1}\d*\.{0,1}\d+$/;
                if (RE.test(myForm.phone.value)&&(myForm.phone.value.length ==8))  
                {  
                    var messageEmail = "<img src=\"img/right-icon.jpg\">";
                }  else {
                    var messageEmail = "<img src=\"img/wrong-icon.jpg\">";}
                document.getElementById("demoPhone").innerHTML = messageEmail;  
            }
            function checkEmail(emailAddr)
            {
                if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.emailAddr.value))  
                {  
                    var messageEmail = "<img src=\"img/right-icon.jpg\">";
                }  else {
                    var messageEmail = "<img src=\"img/wrong-icon.jpg\">";}
                document.getElementById("demoEmail").innerHTML = messageEmail;  
            }    
        </script>

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

        <form action="Login" method="post" id="myForm">
            <table border="0">
                <thead>
                    <tr>
                        <th>Username:</th>
                        <td><input id="uname" type="text" name="name"  onkeyup="checkName(this);" /><p id="demoName" ></p></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>Password:</th>
                        <td><input id="password" type="password" name="password" onkeyup="checkPassword(this);" /><p id="demoPass" ></p></td>
                    </tr>
                    <tr>
                        <th>Confirm Password: </th>
                        <td><input id="confirmpassword" type="password" name="confirmpassword" onkeyup="checkConPassword(this);"/><p id="demoConPass"></p></td>
                    </tr>
                    <tr>
                        <th>Address:</th>
                        <td> <input type="text" name="address" /></td>
                    </tr>
                    <tr>
                        <th>Zipcode: </th>
                        <td> <input id="zipcode" type="text" name="zipcode" maxlength="4" onkeyup="checkZip(this);"/><p id="demoZip"></p></td>
                    </tr>
                    <tr>
                        <th>Phone number: </th>
                        <td><input id="phone" type="text" name="phone" onkeyup="checkPhone(this);"/><p id="demoPhone"></p></td>
                    </tr>
                    <tr>
                        <th>Email: </th>
                        <td><input id="emailAddr" type="text" name="email" onkeyup="checkEmail(this);" /><p id="demoEmail" ></p></td>
                    </tr>
                </tbody>
            </table>  
            <input type="submit" value="Register" name="action" />
        </form>

    </body>
</html>
