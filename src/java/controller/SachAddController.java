/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ChuDeDao;
import dal.SachDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import model.ChuDe;
import model.Sach;

/**
 *
 * @author Admin
 */
public class SachAddController extends HttpServlet {

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
        request.getRequestDispatcher("admin/addBook.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

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
        ChuDe objChuDe = null;
        String tenSach = "", moTa = "", anhSach = "", tacGia = "", maSach = "", maChuDe = "";
        long millis = System.currentTimeMillis();
        float giaSach = 0;
        boolean kq = false;
        int soLuong = 0;
        Date date = new Date(millis);

        //Xử lý upload ảnh
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iterator = items.iterator();

                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    // Nếu là thẻ form
                    if (item.isFormField()) {
                        // Lấy tên của thẻ form
                        String inputName = (String) item.getFieldName();

                        if (inputName.equalsIgnoreCase("txtMaSach")) {

                            maSach = (String) item.getString();
                        }

                        if (inputName.equalsIgnoreCase("txtTenSach")) {
                            // Lấy thông tin dạng unicode qua item
                            tenSach = (String) item.getString("utf-8");

                            System.out.println("Tên sách:" + tenSach);
                        }

                        if (inputName.equalsIgnoreCase("txtSoLuong")) {
                            // Lấy thông tin dạng unicode qua item
                            String strSoLuong = (String) item.getString("utf-8");
                            if (strSoLuong != null && !strSoLuong.isEmpty()) {
                                System.out.println(strSoLuong);
                                soLuong = Integer.parseInt(strSoLuong);
                            }
                        }

                        if (inputName.equalsIgnoreCase("txtMoTa")) {
                            moTa = (String) item.getString("utf-8");

                            System.out.println("Mô tả:" + moTa);
                        }

                        if (inputName.equalsIgnoreCase("txtTacGia")) {
                            tacGia = (String) item.getString("utf-8");

                            System.out.println("TacGia:" + tacGia);
                        }

                        if (inputName.equalsIgnoreCase("txtGiaSach")) {
                            String strGiaSach = (String) item.getString("utf-8");

                            if (strGiaSach != null && !strGiaSach.isEmpty()) {
                                System.out.println(strGiaSach);
                                giaSach = Float.parseFloat(strGiaSach);
                            }

                            System.out.println("Giá sách:" + strGiaSach);
                        }

                        if (inputName.equalsIgnoreCase("cboChuDe")) {

                            maChuDe = (String) item.getString();

                            System.out.println("Chủ đề Id:" + maChuDe);
                        }

                        // Lấy ảnh từ hidden field nếu có===================================================
                        if (inputName.equalsIgnoreCase("hAnh")) {
                            // Lấy thông tin dạng unicode qua item

                            String fileName = (String) item.getString();
                            if (fileName != null && fileName.length() > 0) {
                                // Gán tên ảnh vào đối tượng book
                                anhSach = fileName;
                                System.out.println("Tên ảnh:" + fileName);
                            }
                        }
                    } // end if(item.isFormField())

                    //Nếu không phải thẻ form
                    if (!item.isFormField()) {

                        String fileName = extractFileName(item.getName());
                        // Nếu có tên file thì mới xử lý upload ảnh
                        if (fileName != null && fileName.length() > 0) {
                            // Lay dia chi upload tu cau hinh web.xml
                            String filePath = request.getServletContext().getInitParameter("file-upload");

                            File uploadedFile = new File(filePath + "/" + fileName);

                            System.out.println(uploadedFile.getAbsolutePath());

                            item.write(uploadedFile);

                            // Gán tên ảnh vào đối tượng book
                            anhSach = fileName;
                        }
                    }

                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Khai báo 1 đối tượng sách
        Sach objSach = null;

        // Khai báo 1 đối tượng
        SachDao sachImpl = new SachDao();

        // Nếu sách id có thông tin thì lấy từ db xem có không
//        if (sachId.length() > 0) {
//            objSach = sachImpl.getBookById(Integer.parseInt(sachId));
//        }
//
//        if (objSach != null)// TH Sửa
//        {
//            isInsert = false;
//        } else// TH thêm mới
//        {
//            objSach = new Sach();
//        }

        //Gán giá trị cho các thuộc tính
        objChuDe = dao1.layChuDeTheoMa(maChuDe);
        objSach = new Sach(maSach, tenSach, moTa, anhSach, giaSach, tacGia, date, soLuong);
        objSach.setChuDe(objChuDe);
        kq = dao.addBook(objSach);

        

//        if (isInsert) {
//            //Thực hiện thêm mới
//            kq = sachImpl.addBook(objSach);
//        } else//TH sửa
//        {
//            kq = sachImpl.updateBook(objSach);
//        }

        //Nếu thêm mới thành công
        if (kq) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Successfully added new books!');");
            out.println("location='homeAdmin';");
            out.println("</script>");
        } else {
            request.setAttribute("mess", "Add book failed, please try again!");
            processRequest(request, response);
        }

//        } catch (Exception e) {
//            
//        }
    }

    /**
     * Hàm lấy tên ảnh
     *
     * @param fullPathFile
     * @return
     */
    public String extractFileName(String fullPathFile) {
        try {
            Pattern regex = Pattern.compile("([^\\\\/:*?\"<>|\r\n]+$)");
            Matcher regexMatcher = regex.matcher(fullPathFile);
            if (regexMatcher.find()) {
                return regexMatcher.group(1);
            }
        } catch (PatternSyntaxException ex) {
            System.out.println("Có lỗi xảy ra. Chi tiết: " + ex.getDescription());
        }
        return fullPathFile;
    }

}
