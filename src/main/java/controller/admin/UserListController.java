/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
public class UserListController extends HttpServlet {
private static final String VIEW = "user_list.jsp";
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserListController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try{
            int pageSize = 2;
            String keyword = request.getParameter("keyword");
            String pageIndexRaw = request.getParameter("page");
            
            String status = request.getParameter("status");
            
            String gender = request.getParameter("gender");
            
            
            int pageIndex = 1;
            try{
                pageIndex = Integer.parseInt(pageIndexRaw);
            }catch(Exception e){
                
            }
            UsersDAO dao = new UsersDAO();
            List<User> data = dao.getUserList(pageIndex, pageSize, keyword,status,gender);
            
            int total = dao.countUser(keyword, gender, status);
            
            request.setAttribute("status", status);
            
            request.setAttribute("gender", gender);
            request.setAttribute("totalPage", getPages(total, pageSize));
            request.setAttribute("keyword", keyword);
            request.setAttribute("page", pageIndex);
            request.setAttribute("data", data);
            request.getRequestDispatcher("../"+ VIEW).forward(request, response);
        }catch(SQLException ex){
        Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private List<Integer> getPages(int total, int pageSize){
         List<Integer> page = new ArrayList<>();
        for(int i = 1; i<= total / pageSize ; i++){
            page.add(i);
        }
        if(total % pageSize != 0){
            page.add(page.size() + 1);
        }
        return page;
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
