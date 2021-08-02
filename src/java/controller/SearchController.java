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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * To load data to Search.JSP
 * To page redirects
 * Pagination depending on the number of news results
 * When system is error or an unannounced error, 
   the class will redirect to an error page
 * Distribution the number of news in result page and number the pages 
 * <p>Bugs: None
 * 
 * @author Nguyen Tuan Vu
 */
public class SearchController extends HttpServlet {

    /**
     * Load data and page redirects to Search.JSP
     * When system is error or an unannounced error, 
       the class will redirect to an error page with error message on that page
     * 
     * @param request provides important information about a client request to a servlet. 
     * It is a <code>javax.servlet.http.HttpServletRequest</code> object
     * @param response response respond to an HTTP Request to the browser. 
     * It is a <code>javax.servlet.http.HttpServletResponse</code> object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // begin of get value of parameter from JSP
            String searchContent = request.getParameter("searchContent").trim();
            String indexString = request.getParameter("index");
            // When there is no news, the default position of the page is 1
            if (indexString == null) {
                indexString = "1";
            }
            // convert string value to integer value
            int pageIndex = Integer.parseInt(indexString);             
            DigitalDAO ddi = new DigitalDAOImpl();
            // get number of news result after searching
            int count = ddi.countNumberOfResult(searchContent);
            int pageSize = 3;
            int numberOfPage = 0;
            numberOfPage = count / pageSize;
            // when the number of pages is not round, increase 1 more page
            if (count % pageSize != 0) {
                numberOfPage++;
            }
            // get list Digital after searching
            ArrayList<Digital> listSearch = ddi.getSearchResult(searchContent, pageIndex, pageSize);
            // set number of page
            request.setAttribute("maxPage", numberOfPage);
            // set list Digital after searching
            request.setAttribute("list", listSearch);
            // set searched content which user enter in search box
            request.setAttribute("content", searchContent.trim());
            // set index of page
            request.setAttribute("index", pageIndex);
            // get list top 6 Digital
            ArrayList<Digital> listDigital = ddi.getTopDigital(6);
            // get Digital has most recent
            Digital digital = listDigital.get(0);
            // remove first Digital in list
            listDigital.remove(0);
            // new list after removing 
            ArrayList<Digital> list = listDigital;
            // set the most recent news
            request.setAttribute("news", digital);
            // set top 5 recent news
            request.setAttribute("listRecentNews", list);  
            // Forward requests from a servlet to JSP on server
            request.getRequestDispatcher("Search.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
