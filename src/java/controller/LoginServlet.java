/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.NguoiDungDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.NguoiDung;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        if (session.getAttribute("check") != null) {
            NguoiDung n = (NguoiDung) session.getAttribute("check");
            request.setAttribute("userName", n.getUserName());
            request.setAttribute("passWord", n.getPassWord());
            request.setAttribute("checked", "checked");
        }
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String check = "";       
        check = request.getParameter("cboSigned");
        String name = request.getParameter("txtName");
        String pass = request.getParameter("txtPass");
        NguoiDungDao dao = new NguoiDungDao();
        NguoiDung obj = null;
        obj = dao.getUserById(name, pass);
        
        if (obj != null && check != ""  && check != null) {
            session.setAttribute("check", obj);
        } else {
            session.setAttribute("check", null);
        }  
//        if (obj != null && check.equals("")) {
//            session.setAttribute("check", "");
//        }
        if (obj != null && obj.isIsAdmin() == false && session.getAttribute("cart") != null) {
            session.setAttribute("user", obj);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Logged in successfully!');");
            out.println("location='order';");
            out.println("</script>");
        } else if (obj != null && obj.isIsAdmin() == false && !(session.getAttribute("cart") != null)) {
            session.setAttribute("user", obj);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Logged in successfully!');");
            out.println("location='home';");
            out.println("</script>");
        } else if (obj != null && obj.isIsAdmin() == true) {
            session.setAttribute("admin", obj);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Logged in successfully!');");
            out.println("location='homeAdmin';");
            out.println("</script>");
        }
        else {
            request.setAttribute("mess", "Login failed!");
            processRequest(request, response);
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
