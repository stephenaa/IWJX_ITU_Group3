<%-- 
    Document   : checkout
    Created on : 15-03-2013, 12:24:40
    Author     : vibekelarsen
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>

<%@include file="/common/top.jsp" %> 
<table>
    <tr><td foodname>&nbsp;</td></tr>    
    <tr><td foodname>You'r almost there...</td></tr>
    <tr><td>&nbsp;</td></tr>
    <% if (user == null) {
            out.println("<tr><td><a href='login.jsp'>Aleready a customer please login </a></td><tr>");
            out.println("<tr><td><a href='register.jsp'>Or feel free to registere here</a></td><tr>");
        } else {

            out.println("<tr>");
            out.println("<tr><td>" + user.getUsername() + "</td></tr>");
            out.println("<tr><td>" + user.getZipcode() + "&nbsp;" + user.getAddress() + "</td></tr>");
            out.println("<tr><td>" + "we'll call you on : " + user.getPhone() + "</td></tr>");
        }
    %> 
    <form action="Purchase" method="post">
        <tr><td>Card No<input type="text" name="card" size="30"/></td></tr>
                <%//TODO add hour of day - but rember to alter order table to timestamp
                    Calendar today = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    out.println("<tr><td colspan=\"5\" align=\"right\"> Devlivery " + sdf.format(today.getTime())
                            + "<input type=\"hidden\" name=\"delivery\" value=\"" + sdf.format(today.getTime()) + "\"></td>");
                %>
        <tr><td class="foodname">Comment? Allergies?</td></tr>
        <tr><td><textarea name="comment" rows="5" cols="60" maxlength="200"></textarea></td></tr>
        <tr><td align="right"><input type="submit" name="action" value="pay"</td></tr>   
    </form> 
</table>
<jsp:include page="/common/footer.jsp"/>  