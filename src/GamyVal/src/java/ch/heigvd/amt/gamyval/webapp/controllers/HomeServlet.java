package ch.heigvd.amt.gamyval.webapp.controllers;

import ch.heigvd.amt.gamyval.service.daos.AccountDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.ApplicationDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.EndUserDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Home servlet is the entry point of the app and display information about the
 * system. Though it is protected and requires login, given the security filter
 * configuration.
 *
 * @author Valentin Minder
 */
public class HomeServlet extends HttpServlet {

    @EJB
    ApplicationDAOLocal applicationDAO;

    @EJB
    AccountDAOLocal accountDAO;

    @EJB
    EndUserDAOLocal endUserDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("title", "Home GamyVal");
        request.setAttribute("newUserLast30days", endUserDAO.countLast30Days());
        request.setAttribute("nbOfApplications", applicationDAO.count());
        request.setAttribute("nbOfAccounts", accountDAO.count());
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
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
