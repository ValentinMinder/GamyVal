package ch.heigvd.amt.gamyval.webapp.controllers;

import ch.heigvd.amt.gamyval.models.entities.Account;
import ch.heigvd.amt.gamyval.service.daos.AccountDAOLocal;
import ch.heigvd.amt.gamyval.service.daos.BusinessDomainEntityNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Account servlet provides a the controller to create a new and modify an
 * existing account, depending on the current connection state.
 *
 * @author Valentin Minder
 */
public class AccountServlet extends HttpServlet {

    @EJB
    AccountDAOLocal accountDAO;

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

    }

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
        response.setContentType("text/html;charset=UTF-8");

        boolean logged = false;
        if (request.getSession().getAttribute("principal") != null) {
            Account account = accountDAO
                    .getFromEmail((String) request
                            .getSession()
                            .getAttribute("principal"));
            if (null != account) {
                logged = true;
                request.setAttribute("fName", account.getfName());
                request.setAttribute("email", account.getEmail());
                request.setAttribute("lName", account.getlName());

                request.setAttribute("title", "Modify your account");
                request.getRequestDispatcher("/WEB-INF/pages/account.jsp").forward(request, response);
            }
        }

        if (!logged) {
            request.setAttribute("title", "Registration");
            request.getRequestDispatcher("/WEB-INF/pages/account.jsp").forward(request, response);
        }
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
        response.setContentType("text/html;charset=UTF-8");

        boolean connected = request.getSession().getAttribute("principal") != null;

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String email = request.getParameter("email");

        String pwd = request.getParameter("password");
        String cpwd = request.getParameter("confirm");

        String message = "";
        boolean pwdCheck = false;

        // password check
        if (!pwd.equals(cpwd)) {
            message += "Pwd and confirmation are not the same. ";
        } else if (pwd.length() < 10) {
            message += "Password must be at least 10 chars. ";
        } else {
            boolean up = false;
            boolean low = false;
            boolean num = false;
            boolean other = false;

            for (char c : pwd.toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    low = true;
                } else if (c >= 'A' && c <= 'Z') {
                    up = true;
                } else if (c >= '0' && c <= '9') {
                    num = true;
                } else {
                    other = true;
                }
            }

            if (up && low && num && other) {
                pwdCheck = true;
            } else {
                message += "Rules for pwd: one lower case, one uppercase, one digit, one other. ";
            }
        }

        boolean success = false;

        if (pwdCheck) {
            if (connected) {
                Account acc = accountDAO.getFromEmail(email);
                acc.setPassword(pwd);
                acc.setfName(fName);
                acc.setlName(lName);
                try {
                    //update account
                    accountDAO.update(acc);
                    message = "Your information have been updated.";
                } catch (BusinessDomainEntityNotFoundException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    message = "Error. Your information have not been updated.";
                }
            } else {

                // check the existence of the account with this email
                Account a = accountDAO.getFromEmail(email);

                if (a != null) {
                    message = "Not registered. Email already in use by other account.";
                } else {
                    success = true;
                    accountDAO.create(new Account(email, fName, lName, pwd));
                    message = "Account created! Welcome to GamyVal " + fName + " " + lName
                            + ". You are now logged in.";
                }
            }
        }

        request.setAttribute("message", message);

        if (success) {
            //auto connect the session
            request.getSession().setAttribute("principal", email);
            request.setAttribute("title", "Home");
            request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
        } else {
            // not refill everything, only the password.
            request.setAttribute("email", email);
            request.setAttribute("fName", fName);
            request.setAttribute("lName", lName);
            request.setAttribute("title", "Account");
            request.getRequestDispatcher("/WEB-INF/pages/account.jsp").forward(request, response);
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
    }

}
