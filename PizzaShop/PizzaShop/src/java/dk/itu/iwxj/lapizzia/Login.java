/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.iwxj.lapizzia;

import dk.itu.iwxj.lapizzia.data.UserDataBean;
import dk.itu.iwxj.lapizzia.model.User;
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
 * @author chwu
 */
public class Login extends HttpServlet {

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
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Login</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
//    }

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
        //processRequest(request, response);
        request.getSession().setAttribute("flash", "");
        request.getSession().setAttribute("user", null);                
        response.sendRedirect("login.jsp");
    }

    
    boolean handleLogin(HttpServletRequest request, HttpServletResponse response) {        
        User user = UserDataBean.getInstance().getUser((String) request.getParameter("name"), (String) request.getParameter("password"));

        request.getSession().setAttribute("flash", "");
        request.getSession().setAttribute("user", null);
        
        try {
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("flash", "Login failed - retry!");
                response.sendRedirect("login.jsp");
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    
    boolean handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        boolean validateResult = user.initFromRequest(request);
        if (validateResult == false) {
            response.sendRedirect("register.jsp");
            return false;
        }
        
        boolean result = UserDataBean.getInstance().add(user);
        try {

            if (result == true) {
                request.getSession().setAttribute("flash", "User was added!");
            } else {
                request.getSession().setAttribute("flash", "Error adding user!");
            }

            response.sendRedirect("login.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
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
        // processRequest(request, response);
        request.getSession().invalidate();
        if ("Register".compareToIgnoreCase(request.getParameter("action")) == 0) {
            handleRegister(request, response);
        }

        if ("Login".compareToIgnoreCase(request.getParameter("action")) == 0) {
            handleLogin(request, response);
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
