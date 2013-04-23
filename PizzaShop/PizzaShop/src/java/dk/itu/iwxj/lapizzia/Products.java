/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import dk.itu.iwxj.lapizzia.data.ProductDataBean;
import dk.itu.iwxj.lapizzia.data.UserDataBean;
import dk.itu.iwxj.lapizzia.model.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author stephen
 */
public class Products extends HttpServlet {

    // ProductDataBean productDataBean = new ProductDataBean();
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

        PrintWriter out = response.getWriter();
        Product pizza = null;
        try {
            if (request.getPathInfo() != null) {
                // We skip the first slash of the path information, makes the elements be arranged nicely
                String path = request.getPathInfo().substring(1);

                String[] elements = path.split("/");
                if (elements.length > 0) {
                    pizza = ProductDataBean.getInstance().get(Integer.parseInt(elements[0]));
                }
            }

            if (pizza != null) {
                response.setContentType("text/html;charset=UTF-8");
                out.println(pizza.toHtml());
            } else {
                response.sendError(404);
            }



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

        request.getSession().setAttribute("message", "");

        if ("Add".compareToIgnoreCase(request.getParameter("action")) == 0) {
            Product product = new Product();
            product.initFromRequest(request);

            boolean result = ProductDataBean.getInstance().add(product);
            if (result) {
                request.getSession().setAttribute("message", "Pizza was added!");
            } else {
                request.getSession().setAttribute("message", "Error adding Pizza!");
            }
        }


        if ("Delete".compareToIgnoreCase(request.getParameter("action")) == 0) {
            String id = request.getParameter("id");
            Logger.getLogger(UserDataBean.class.getName()).log(Level.SEVERE, "Deleting Pizza:" + id);
            if (id != null) {
                if (ProductDataBean.getInstance().del(Integer.parseInt(id))) {
                    request.getSession().setAttribute("message", "Pizza was deleted!");
                } else {
                    request.getSession().setAttribute("message", "Unable to delete Piza.");
                }
            }
        }

        response.sendRedirect("admin.jsp");
    }
    

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Product pizza = null;
        try {
            InputStream body = request.getInputStream();

            if (request.getPathInfo() != null) {
                // We skip the first slash of the path information, makes the elements be arranged nicely
                String path = request.getPathInfo().substring(1);

                String[] elements = path.split("/");
                if (elements.length > 0) {
                    pizza = ProductDataBean.getInstance().get(Integer.parseInt(elements[0]));
                }
            }

            if (pizza != null) {
                Product updatedPizza = pizza.initFromXml(pizza, body);
                if (updatedPizza != null) {

                    ProductDataBean.getInstance().update(updatedPizza);
                }
                response.setContentType("application/xml;charset=UTF-8");
                out.println("<pizza>" + pizza.toXml() + "</pizza>");
            } else {
                response.sendError(404);
            }



        } finally {
            out.close();
        }

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
