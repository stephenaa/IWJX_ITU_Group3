/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import dk.itu.iwxj.lapizzia.data.ProductDataBean;
import dk.itu.iwxj.lapizzia.data.UserDataBean;
import dk.itu.iwxj.lapizzia.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stephen
 */
public class Products extends HttpServlet {

    ProductDataBean productDataBean = new ProductDataBean();
    
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
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Product</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Product at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        //processRequest(request, response);
        request.getSession().setAttribute("message", "");
        
        if ("Add".compareToIgnoreCase(request.getParameter("action")) == 0) {
            Product product = new Product();
            product.initFromRequest(request);
            
            boolean result = productDataBean.add(product);
            if (result) {
                request.getSession().setAttribute("message", "Pizza was added!");
            } else {
                request.getSession().setAttribute("message", "Error adding Pizza!");
            }                    
        }
        
                
        if ("Delete".compareToIgnoreCase(request.getParameter("action")) == 0) {
         String id = request.getParameter("id");
         Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, "Deleting Pizza:" + id);
         if(id != null) {
            if (productDataBean.del(Integer.parseInt(id))) {
                request.getSession().setAttribute("message", "Pizza was deleted!");
            } else
            {
                request.getSession().setAttribute("message", "Unable to delete Piza.");
            }      
         }
        }

        response.sendRedirect("admin.jsp");            
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
