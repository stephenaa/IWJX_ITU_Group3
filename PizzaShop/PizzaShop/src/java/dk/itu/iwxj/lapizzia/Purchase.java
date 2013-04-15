/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import dk.itu.iwxj.lapizzia.data.OrderDataBean;
import dk.itu.iwxj.lapizzia.model.Order;
import dk.itu.iwxj.lapizzia.model.OrderItem;
import dk.itu.iwxj.lapizzia.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chwu and others
 */
public class Purchase extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Purchase</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Purchase at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, OrderItem> basket;
        HttpSession session = request.getSession();
        if (session.getAttribute("basket") == null) {
            basket = new TreeMap<String, OrderItem>();
            session.setAttribute("basket", basket);
        }

        session.setAttribute("message", "");
        response.sendRedirect("purchase.jsp");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = "";
        HttpSession session = request.getSession();
        session.setAttribute("message", msg);
        String page = "purchase.jsp";

        Map basket = (TreeMap) session.getAttribute("basket");
        if (request.getParameter("action").equalsIgnoreCase("AddToBasket")) {
            addToBasket(request, basket);

        }
        if (request.getParameter("action").equalsIgnoreCase("remove")) {
            basket.remove((String) request.getParameter("name"));
        }
        if (request.getParameter("action").equalsIgnoreCase("checkout")) {
            page = "checkout.jsp";
        }
        if (request.getParameter("action").equalsIgnoreCase("pay")) {
            page = checkout(request);
        }

        response.sendRedirect(page);
    }

    public void addToBasket(HttpServletRequest request, Map basket) {
        //todo test are the quanty filled in with a number
        OrderItem line = new OrderItem();
        System.out.println("Adding to basket" + request.getParameterMap());
        System.out.println("Product id:" + request.getParameter("product_id") + ", quantity:" + request.getParameter("qty"));
        request.getSession().setAttribute("message", "");
        line.initFromRequest(request);
        basket.put(line.getName(), line);
    }

    public String checkout(HttpServletRequest request) {
        String page = "done.jsp";
        Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, "in checkout");
        HttpSession session = request.getSession();
        Map basket = (TreeMap) session.getAttribute("basket");
        User user = (User) request.getSession().getAttribute("user");
        if  (user == null) {
            session.setAttribute("message", "You need to login to add the order");
            return "checkout.jsp";
        } else {
            session.setAttribute("message", "");
            user = (User) request.getSession().getAttribute("user");
            int keys = UUIDUtilty.INSTANCE.getUUID();
            //TODO validate parameter from page
            Order order = new Order();
            order.initFromRequest(request, user);
            order.setOrderId(keys);
            OrderDataBean.getInstance().add(order);
            Iterator entries = basket.entrySet().iterator();
            while (entries.hasNext()) {
                Entry thisEntry = (Entry) entries.next();
                OrderItem i = (OrderItem) thisEntry.getValue();
                i.setFkOrderId(keys);
                OrderDataBean.getInstance().add(i);
                Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, "processing " + i.getName());
            }
            //TODO error handling of OrderDataBean
        }
        return page;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
