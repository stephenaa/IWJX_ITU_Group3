<%-- 
    Document   : footer
    Created on : 08-04-2013, 19:01:53
    Author     : vanl
--%>

<%@page import="dk.itu.iwxj.lapizzia.model.OrderItem"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
</table>
  </td>
   <td>  <%---Display shopcart -> TODO seperate into own jsp??? --%> 
       <%if(!request.getServletPath().contains("index")){%>  
       <table cellpadding="1" cellspacing="0" BORDERCOLOR="green">  
           <tr>
            <td>     
            <table cellpadding="0" cellspacing="0">   
            <tr><td colspan="5" align="left">In Your Basket:&nbsp;</td></tr>   
            <%
                Map shopcart = (TreeMap) session.getAttribute("basket");
            %>     
            <tr><td class="foodname">No</td><td class="foodname">Pizza</td><td class="foodname">Qty</td><td class="foodname">Price</td><td class="foodname">Total</td></tr>
            <%  String emptySpace = "&nbsp;";
                int amountTotalInBasket = 0;
            if(shopcart!=null){
                
                Iterator entries = shopcart.entrySet().iterator();
                while (entries.hasNext()) {
                    Entry thisEntry = (Entry) entries.next();
                    OrderItem i = (OrderItem) thisEntry.getValue();
                    amountTotalInBasket = amountTotalInBasket + i.getTotalPrice();
                    out.println("<tr>");
                    out.println("<td>" + i.getFkProductId() + emptySpace + "</td>");
                    out.println("<td>" + i.getName() + emptySpace + "</td>");
                    out.println("<td>" + i.getQuantity() + emptySpace + "</td>");
                    out.println("<td>" + i.getPrice() + emptySpace + "</td>");
                    out.println("<td align=\"right\">" + i.getTotalPrice() + emptySpace + "</td>");
                    out.println("<td><form action=\"Purchase\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"name\" value=\"" + i.getName() + "\">");
                    out.println("<input type=\"image\" value=\"remove\"  src=\"img/minus.gif\" name=\"action\">");
                    out.println("</form></td>");
                    out.println("</tr>");
                }
                out.println("<tr><td colspan='5'><br><span class='line'>----------------------------------------------------------</span></td></tr>");
                out.println("<tr><td colspan='5' align='right'>" + amountTotalInBasket + emptySpace + "</td><tr>");
            }
            %>  
            <% if(request.getServletPath().contains("purchase")){ %>
            <form action="Purchase" method="post">
                <tr><td colspan="5" align="right"><input type="submit" name="action" value="checkout"</td></tr>   
            </form>
             <%}%>                    
            </table> 
            &nbsp;
            </td>
        </tr>    
       </table>
       <%---Closed display shopcart if(!request.getServletPath().contains("index")){ --%> 
             <%}%>         
    </td>
  </tr>  
  <tr>
    <td valign="top" colspan="2">
        <table width="900" border="0" cellspacing="0" style="background-image:url(img/footer.jpg); background-repeat:repeat;" cellpadding="0">
            <tr><td height="27" align="center" class="footer">All Right Reserve with La PizzaShop</td> </tr>
        </table>
        <table width="900" border="0" cellspacing="0" cellpadding="0">
            <tr><td height="16">&nbsp;</td></tr>         
        </table>        
    </td>
    </tr>
    </table> 
     <%---Closed top.jsp's Header tabel contains genric content --%> 
  </body>
</html>         