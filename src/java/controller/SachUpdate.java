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
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ChuDe;
import model.Sach;

/**
 *
 * @author Admin
 */
public class SachUpdate extends HttpServlet {

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
        ArrayList<ChuDe> lstChuDe = dao.layDanhSachChuDe();
        request.setAttribute("lstChuDe", lstChuDe);
        request.getRequestDispatcher("/admin/editBook.jsp").forward(request, response);
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
        SachDao dao = new SachDao();
        String maSach = "";
        boolean kq = false;
        Sach objSach = null;
        maSach = request.getParameter("ma");
        if (maSach != "") {
            objSach = dao.getBookById(maSach);
            request.setAttribute("maSach", objSach.getMaSach());
            request.setAttribute("tenSach", objSach.getTenSach());
            request.setAttribute("moTa", objSach.getMoTa());
            request.setAttribute("giaSach", objSach.getGiaSach());
            request.setAttribute("tacGia", objSach.getTacGia());
            request.setAttribute("maChuDe", objSach.getChuDe().getMaChuDe());
            request.setAttribute("soLuong", objSach.getSoLuong());
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        SachDao dao = new SachDao();
        ChuDeDao dao1 = new ChuDeDao();
        Sach objSach = null;
        Sach objSachOld = null;
        ChuDe objChuDe = null;
        String tenSach = "", moTa = "", anhSach = "", tacGia = "", maSach = "", maChuDe = "", strSoLuong = "";
        float giaSach = 0;
        int soLuong = 0;
        boolean kq = false;
        maSach = request.getParameter("hMaSach");
        tenSach = request.getParameter("txtTenSach");
        moTa = request.getParameter("txtMoTa");
        anhSach = request.getParameter("txtAnhSach");
        tacGia = request.getParameter("txtTacGia");
        giaSach = Float.parseFloat(request.getParameter("txtGiaSach"));
        maChuDe = request.getParameter("cboChuDe");
        strSoLuong = request.getParameter("txtSoLuong");
        soLuong = Integer.parseInt(strSoLuong);
        objSachOld = dao.getBookById(maSach);
        objChuDe = dao1.layChuDeTheoMa(maChuDe);
        if (maSach != "" && objChuDe != null) {
            objSach = new Sach(maSach, tenSach, moTa, "java_program.jpg", giaSach, tacGia, objSachOld.getNgayTao(), soLuong);
            objSach.setChuDe(objChuDe);
            kq = dao.updateBook(objSach);
        }
        if (kq) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Book update successful!');");
            out.println("location='homeAdmin';");
            out.println("</script>");
        } else {
            request.setAttribute("mess", "Update failed, please try again!");
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
