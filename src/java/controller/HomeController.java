/*
* Copyright(C) 2021,  vunthe141507.
* J3.L.P0004
* Java Web
*
* Record of change:
* DATE                Version           AUTHOR          DESCRIPTION
* 17/05/2021           1.0               VuNT       fix comment, fix bug
* 19/05/2021           2.0               VuNT       fix comment, fix bug
*/
package controller;


import dao.DigitalDAO;
import dao.impl.DigitalDAOImpl;
import entity.Digital;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * To load data to HomePage.JSP
 * To page redirects
 * When system is error or an unannounced error, 
   the class will redirect to an error page   
 * Load top 1 and top 5 most recent news to JSP
 * <p>Bugs: None
 * 
 * @author Nguyen Tuan Vu
 */
public class HomeController extends HttpServlet {

    /**
     * Load data through DigitalDAOImpl.java to HomePage.JSP
     * When system is error or an unannounced error, 
       the class will redirect to an error page with error message show on that pages
     * 
     * @param request provides important information about a client request to a servlet. 
     * It is a <code>javax.servlet.http.HttpServletRequest</code> object
     * @param response response respond to an HTTP Request to the browser. 
     * It is a <code>javax.servlet.http.HttpServletResponse</code> object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            DigitalDAO dd = new DigitalDAOImpl();
            // get list top 6 Digital
            ArrayList<Digital> listDigital = dd.getTopDigital(6);
            // get Digital has most recent
            Digital digital = listDigital.get(0);
            // formate date
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - hh:mm:");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("a");
            String date = dateFormat1.format(digital.getTimePost()) + 
                    dateFormat2.format(digital.getTimePost()).toLowerCase();
            // set value of date after convert
            request.setAttribute("dateConvert", date);
            // remove first Digital in list
            listDigital.remove(0);
            // new list after removing ;
            ArrayList<Digital> list = listDigital;
            // set the most recent news
            request.setAttribute("news", digital);
            // set top 5 recent news
            request.setAttribute("listRecentNews", list);
            // Forward requests from a servlet to JSP on server
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            // when system is errors or unknown errors, Error page will be display     
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request provides important information about a client request to a servlet. 
     * It is a <code>javax.servlet.http.HttpServletRequest</code> object
     * @param response response respond to an HTTP Request to the browser. 
     * It is a <code>javax.servlet.http.HttpServletResponse</code> object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request provides important information about a client request to a servlet. 
     * It is a <code>javax.servlet.http.HttpServletRequest</code> object
     * @param response response respond to an HTTP Request to the browser. 
     * It is a <code>javax.servlet.http.HttpServletResponse</code> object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
