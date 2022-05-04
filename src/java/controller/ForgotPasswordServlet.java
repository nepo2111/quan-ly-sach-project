/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.EmailUtils;
import dal.NguoiDungDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Email;
import model.NguoiDung;


/**
 *
 * @author Admin
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
           String emailAddress = request.getParameter("txtEmail");
//           String userName = request.getParameter("txtName");
           
            NguoiDungDao dao = new NguoiDungDao();
            NguoiDung objNguoiDung = dao.findByUserNameAndEmail(emailAddress);
            if (objNguoiDung == null) {
                request.setAttribute("error", "Username or email are incorrect");
            } else {
                Email email = new Email();
                email.setFrom("hieunvhe153769@fpt.edu.vn");
                email.setFromPassword("Skynet.com");
                email.setTo(emailAddress);
                email.setSubject("Forgot Password Function");
                StringBuilder sb = new StringBuilder();
                sb.append("Dear ").append(objNguoiDung.getUserName()).append("<br>");
                sb.append("You are used the forgot password function. <br> ");
                sb.append("Your password is <b>").append(objNguoiDung.getPassWord()).append("</b>");
                sb.append("regards<br>");
                sb.append("Administrator");
                
                email.setContent(sb.toString());
                EmailUtils.send(email);
                
                request.setAttribute("mess", "Email sent to the email Address. Please check and get your password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            request.setAttribute("error", e.getMessage());
        }
        response.sendRedirect("login");
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
