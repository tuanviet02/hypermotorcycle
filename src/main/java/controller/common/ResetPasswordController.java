/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.service.SendMail;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset_password"})
public class ResetPasswordController extends HttpServlet {

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
        String page = "reset_password.jsp";
        request.getRequestDispatcher(page).forward(request, response);
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
        try {
            String recipient = request.getParameter("email");
            String subject = "Your Password has been reset";
            CustomerServices customerServices = new CustomerServices();
            String newPassword = customerServices.resetCustomerPassword(recipient);
            String content = "Hi, this is your new password: " + newPassword;
            content += "\nNote: for security reason, "
                    + "you must change your password after logging in.";

            String message = "";
            if (newPassword.equalsIgnoreCase("EmailNotFound")) {
                request.setAttribute("message", "Email not exist in system!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
            } else {
                try {
                    SendMail sendmail = new SendMail();
                    sendmail.send(recipient, subject, content);
                    message = "Your password has been reset. Please check your e-mail.";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    message = "There were an error: " + ex.getMessage();
                } finally {
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("message.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
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
