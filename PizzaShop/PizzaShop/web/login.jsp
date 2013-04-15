<%-- 
    Document   : login
    Created on : Mar 12, 2013, 5:07:18 PM
    Author     : chwu
--%>

<%@include file="/common/top.jsp" %>  

        <h3>Pizza Login</h3>
        <form action="Login" method="post">
        <tr><td>Username:<input type="text" name="name"/> </td></tr>
        <tr><td>Password:&nbsp;<input type="password" name="password" /></td></tr>
        <tr><td><input type="submit" value="Login" name="action" /></td></tr>
    </form>
  <jsp:include page="/common/footer.jsp"/>
