/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ChuDeDao;
import dal.NguoiDungDao;
import dal.OrderDao;
import dal.SachDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.ChuDe;
import model.Item;
import model.NguoiDung;
import model.Sach;

/**
 *
 * @author Admin
 */
public class OrderServlet extends HttpServlet {

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
        ChuDeDao dao = new ChuDeDao();
        HttpSession session = request.getSession();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        NguoiDung objNguoiDung = (NguoiDung)session.getAttribute("user");
        
        Cart cart = null;
        cart = (Cart)session.getAttribute("cart");       
        ArrayList<ChuDe> lstChuDe = dao.layDanhSachChuDe();
        request.setAttribute("cart", cart);
        request.setAttribute("lstChuDe", lstChuDe);
        request.setAttribute("user", objNguoiDung);
        request.setAttribute("date", date);
        request.getRequestDispatcher("user/order.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        NguoiDungDao dao = new NguoiDungDao();
        SachDao dao2 = new SachDao();
        OrderDao dao1 = new OrderDao();
        String userName = "";
        boolean kq = false;
        Cart cart = null;
        NguoiDung objNguoiDung = null;
        userName = request.getParameter("hUserName");
        cart = (Cart)session.getAttribute("cart");
        objNguoiDung = dao.getUserById(userName);
        if (cart != null && objNguoiDung != null) {
           kq = dao1.addOrder(objNguoiDung, cart);
        }
        if (kq) {
            for (Item i : cart.getItems()) {
                Sach objSach = i.getSach();
                objSach.setSoLuong(objSach.getSoLuong() - i.getSoLuong());
                dao2.updateBook(objSach);
            }
            session.setAttribute("cart", null);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Thanh toán thành công!');");
            out.println("location='home';");
            out.println("</script>");        
        } else {
            request.setAttribute("mess", "Thanh toán thất bại");
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
