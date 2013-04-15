<%-- 
    Document   : purchase.jsp
    Created on : 14-03-2013, 17:09:26
    Author     : vanl
--%>
<%@page import="dk.itu.iwxj.lapizzia.data.ProductDataBean"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.TreeMap"%>
<%@page import="dk.itu.iwxj.lapizzia.model.OrderItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="dk.itu.iwxj.lapizzia.model.Product"%>
<%@page import="java.util.List"%>
<%@include file="/common/top.jsp" %>     
   
   <%    
   int currentPage = 1;
   if(request.getParameter("current_page") != null) {
        currentPage = Integer.parseInt(request.getParameter("current_page"));
    }
            List<Product> pizze = ProductDataBean.getInstance().list((currentPage-1)*10,10);
            int totalPizze = ProductDataBean.getInstance().numberOfProducts();
            int totalPages = totalPizze / 10;                
        %>
        <h3><%= pizze.size()%> deliziosa pizza per il vostro stomaco:</h3>
        <div style="float:left;">
        <table class="pizzalist" id="orderTable">
          <tr><td class="foodname">No</td><td class="foodname">Pizza</td><td class="foodname">Price</td><td class="foodname">&nbsp;</td></tr>
            <%
                int pizzeShown = 0;
                for (Product p : pizze) {
                    pizzeShown++;
                    out.println("<td><form action=\"Purchase\" method=\"post\" name=\"orderForm-" + p.getId() + "\">");
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<input type=\"hidden\" name=\"product_id\" value=\"" + p.getId() + "\">");
                    out.println("<td><a href=\"#\" id=\"" + p.getId() + "\">" + p.getName() + "</a></td>");
                    out.println("<td>" + p.getPrice() + ",-</td>");                    
                    out.println("<td><input type=\"text\" name=\"qty\" size=\"3\">");
                    out.println("<input type=\"image\" value=\"AddToBasket\" src=\"img/plus.gif\" name=\"action\"></td>");
                    out.println("</tr>");
                    out.println("</form></td>");
                }

            %>                            

        </table>
            
        </div>
        
            <!-- The 'descriptionDiv' div will be populated through AJAX -->
            <div id="descriptionDiv" style="float:left;width:20%;padding-left: 3%">
            &nbsp;
            </div>
            
            
            <div style="clear:both;"></div>
            
            <p><%= pizzeShown %> of <%= totalPizze %> total Pizzas shown. Page <%= currentPage %> of <%= totalPages %></p>
            <div>
            
            <%
                if(currentPage > 1) {
                    out.print("<div><a href=\"?current_page=" + (currentPage-1) + "\">&lt;&lt; Previous 10</a></div>");
                }
                
                if(currentPage <= totalPages ) {
                    out.print("<div><a href=\"?current_page=" + (currentPage+1) + "\">&gt;&gt; Next 10</a></div>");
                }
            %>
            </div> 
     <script language="javascript" type="text/javascript" > 
    function initPage() {
        // Install onclick handlers etc.
        
        table = document.getElementById('orderTable');
        var elements = table.getElementsByTagName("a");
        
        for(var i=0; i<elements.length; i++) {            
            elements[i].onmouseover = function(e) {
                var target = (e.target) ? e.target : e.srcElement;
                
                http = getHttp();  
                request = "/PizzaShop/Products/" + target.id;
                
                // The callback passed to sendRequest is being constructed as an anonymous function below
                sendRequest(http,request, function() {
                    if (http.readyState === 4 && http.status === 200) {
                        div = document.getElementById("descriptionDiv");
                        if(div) {
                            div.innerHTML = http.responseText;
                        }
                        
                    }
                });
            };
      }       
    } 
    
    initPage();
    
   </script>    
  <jsp:include page="/common/footer.jsp"/>  

