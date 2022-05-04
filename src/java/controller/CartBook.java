/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ChuDeDao;
import dal.SachDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.ChuDe;
import model.Item;
import model.Sach;

/**
 *
 * @author Admin
 */
public class CartBook extends HttpServlet {
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
        request.setCharacterEncoding("utf-8");
//        SachDao dao = new SachDao();
//        ChuDeDao dao1 = new ChuDeDao();
//        ArrayList<ChuDe> lstChuDe = dao1.layDanhSachChuDe();
//        ArrayList<Sach> lstSach = dao.getAllBook();
//        request.setAttribute("lstChuDe", lstChuDe);
//        request.setAttribute("lstSach", lstSach);
//        request.getRequestDispatcher("user/body.jsp").forward(request, response);
            response.sendRedirect("home");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        SachDao dao = new SachDao();
        HttpSession session = request.getSession();
        String maSach = "", strSoLuong = "";
        Sach objSach = null;
        int soLuong = 0;
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart)o;
        } else {
            cart = new Cart();
        }
        maSach = request.getParameter("ma");
        strSoLuong = request.getParameter("txtSoLuong");
        try {
            soLuong = Integer.parseInt(strSoLuong);
            objSach = dao.getBookById(maSach);
            float giaSach = objSach.getGiaSach();
            Item item = new Item(objSach, soLuong, giaSach);
            cart.addItem(item);
            
        } catch (NumberFormatException e) {
            soLuong = 1;
        }
        ArrayList<Item> lst = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", lst.size());
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
