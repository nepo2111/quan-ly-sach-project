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

        //X??? l?? upload ???nh
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
                    // N???u l?? th??? form
                    if (item.isFormField()) {
                        // L???y t??n c???a th??? form
                        String inputName = (String) item.getFieldName();

                        if (inputName.equalsIgnoreCase("txtMaSach")) {

                            maSach = (String) item.getString();
                        }

                        if (inputName.equalsIgnoreCase("txtTenSach")) {
                            // L???y th??ng tin d???ng unicode qua item
                            tenSach = (String) item.getString("utf-8");

                            System.out.println("T??n s??ch:" + tenSach);
                        }

                        if (inputName.equalsIgnoreCase("txtSoLuong")) {
                            // L???y th??ng tin d???ng unicode qua item
                            String strSoLuong = (String) item.getString("utf-8");
                            if (strSoLuong != null && !strSoLuong.isEmpty()) {
                                System.out.println(strSoLuong);
                                soLuong = Integer.parseInt(strSoLuong);
                            }
                        }

                        if (inputName.equalsIgnoreCase("txtMoTa")) {
                            moTa = (String) item.getString("utf-8");

                            System.out.println("M?? t???:" + moTa);
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

                            System.out.println("Gi?? s??ch:" + strGiaSach);
                        }

                        if (inputName.equalsIgnoreCase("cboChuDe")) {

                            maChuDe = (String) item.getString();

                            System.out.println("Ch??? ????? Id:" + maChuDe);
                        }

                        // L???y ???nh t??? hidden field n???u c??===================================================
                        if (inputName.equalsIgnoreCase("hAnh")) {
                            // L???y th??ng tin d???ng unicode qua item

                            String fileName = (String) item.getString();
                            if (fileName != null && fileName.length() > 0) {
                                // G??n t??n ???nh v??o ?????i t?????ng book
                                anhSach = fileName;
                                System.out.println("T??n ???nh:" + fileName);
                            }
                        }
                    } // end if(item.isFormField())

                    //N???u kh??ng ph???i th??? form
                    if (!item.isFormField()) {

                        String fileName = extractFileName(item.getName());
                        // N???u c?? t??n file th?? m???i x??? l?? upload ???nh
                        if (fileName != null && fileName.length() > 0) {
                            // Lay dia chi upload tu cau hinh web.xml
                            String filePath = request.getServletContext().getInitParameter("file-upload");

                            File uploadedFile = new File(filePath + "/" + fileName);

                            System.out.println(uploadedFile.getAbsolutePath());

                            item.write(uploadedFile);

                            // G??n t??n ???nh v??o ?????i t?????ng book
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

        // Khai b??o 1 ?????i t?????ng s??ch
        Sach objSach = null;

        // Khai b??o 1 ?????i t?????ng
        SachDao sachImpl = new SachDao();

        // N???u s??ch id c?? th??ng tin th?? l???y t??? db xem c?? kh??ng
//        if (sachId.length() > 0) {
//            objSach = sachImpl.getBookById(Integer.parseInt(sachId));
//        }
//
//        if (objSach != null)// TH S???a
//        {
//            isInsert = false;
//        } else// TH th??m m???i
//        {
//            objSach = new Sach();
//        }

        //G??n gi?? tr??? cho c??c thu???c t??nh
        objChuDe = dao1.layChuDeTheoMa(maChuDe);
        objSach = new Sach(maSach, tenSach, moTa, anhSach, giaSach, tacGia, date, soLuong);
        objSach.setChuDe(objChuDe);
        kq = dao.addBook(objSach);

        

//        if (isInsert) {
//            //Th???c hi???n th??m m???i
//            kq = sachImpl.addBook(objSach);
//        } else//TH s???a
//        {
//            kq = sachImpl.updateBook(objSach);
//        }

        //N???u th??m m???i th??nh c??ng
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
     * H??m l???y t??n ???nh
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
            System.out.println("C?? l???i x???y ra. Chi ti???t: " + ex.getDescription());
        }
        return fullPathFile;
    }

}
