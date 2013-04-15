<%-- 
    Document   : index
    Created on : Mar 12, 2013, 4:32:58 PM
    Author     : chwu
--%>

<%@include file="/common/top.jsp" %> 
<h1>Welcome to Pizza Shop!</h1>
<div>
    <%
        if (user == null) {
            out.println("<a href=\"login.jsp\">Login</a></br>");
            out.println("<a href=\"register.jsp\">Register</a>");
        } else {
            out.println("<a href=\"Purchase\">Le nostre pizze sono buone nello stomaco</a>");
        }
    %>

</div>

</div>
<div>
    <img src="img/pisa.jpg" width="683" height="455" alt="pisa"/>
</div>
<jsp:include page="/common/footer.jsp"/> 
