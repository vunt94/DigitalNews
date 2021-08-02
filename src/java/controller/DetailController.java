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
 * Load news which user selected on Detail.JSP Keep information of top 1 and top
 * 5 most recent news after user selected 1 in 5 most recent news When system is
 * error or unknown error, Error page will be delivered
 * <p>Bugs: None
 * 
 * @author Nguyen Tuan Vu
 */
public class DetailController extends HttpServlet {

    /**
     * Load news which user selected and page direct to Detail.JSP When system
     * is error or unknown error, Error page will be delivered with error
     * message show on that pages
     *
     * @param request provides important information about a client request to a
     * servlet. It is a <code>javax.servlet.http.HttpServletRequest</code>
     * object
     * @param response response respond to an HTTP Request to the browser. It is
     * a <code>javax.servlet.http.HttpServletResponse</code> object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            response.setContentType("text/html;charset=UTF-8");
            // get value of parameter from JSP
            int id = Integer.parseInt(request.getParameter("id"));
            DigitalDAO dd = new DigitalDAOImpl();
            // get news which user selected
            Digital digital = dd.getSelectedNews(id);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - hh:mm:");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("a");
            String date = dateFormat1.format(digital.getTimePost())
                    + dateFormat2.format(digital.getTimePost()).toLowerCase();
            request.setAttribute("dateConvert", date);
            // set selected news
            request.setAttribute("selectedNews", digital);
            // get top 6 news has most recent
            ArrayList<Digital> listDigital = dd.getTopDigital(6);
            // get most recent news
            Digital digital1 = listDigital.get(0);
            // remove first news in list
            listDigital.remove(0);
            // get new list after removing
            ArrayList<Digital> list = listDigital;
            // set value for most recent news 
            request.setAttribute("news", digital1);
            // set value for top 5 most recent news
            request.setAttribute("listRecentNews", list);
            // Forward requests from a servlet to JSP on server
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            // when system is errors or unknown errors, Error page will be display
            DigitalDAOImpl dd = new DigitalDAOImpl();
            // get top digital
            ArrayList<Digital> listDigital = dd.getTopDigital(6);
            // get most recent news
            Digital digital = listDigital.get(0);
            listDigital.remove(0);
            ArrayList<Digital> list = listDigital;
            // set attribute
            request.setAttribute("news", digital);
            request.setAttribute("listRecentNews", list);
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
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
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
