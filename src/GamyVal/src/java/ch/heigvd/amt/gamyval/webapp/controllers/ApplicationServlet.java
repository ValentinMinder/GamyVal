package ch.heigvd.amt.gamyval.webapp.controllers;

import ch.heigvd.amt.gamyval.models.entities.ApiKey;
import ch.heigvd.amt.gamyval.models.entities.Application;
import ch.heigvd.amt.gamyval.service.daos.ApplicationDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application servlet provides a the controller to create an app.
 *
 * @author Valentin Minder
 */
public class ApplicationServlet extends HttpServlet {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;

    @EJB
    ApplicationDAOLocal applicationDAO;

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
        request.setAttribute("title", "New application");
        request.getRequestDispatcher("/WEB-INF/pages/application.jsp").forward(request, response);
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

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String descr = request.getParameter("descr");

        Application application = new Application();
        application.setName(name);
        application.setDescr(descr);

        ApiKey apiKey = applicationDAO.generateAPIKEY();
        application.setApiKey(apiKey);
        applicationDAO.bindToAccount(email, application);
        applicationDAO.create(application);

        //request.setAttribute("apikey", apiKey.getApiKey());
        request.setAttribute("title", "Created: New application");
        request.setAttribute("message", "Created application " + name + ", apikey: " + apiKey.getApiKey());
        // redirect on the app creation page
        request.getRequestDispatcher("/WEB-INF/pages/application.jsp").forward(request, response);
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
