/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChuDe;
import model.Sach;

/**
 *
 * @author Admin
 */
public class SachDao extends DBContext {

    public ArrayList<Sach> getAllBook() {
        ArrayList<Sach> lstBook = new ArrayList<>();
        try {
            String sql = "Select s.*, c.TenChuDe from Sach s, ChuDe c where s.MaChuDe = c.MaChuDe";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Sach s = null;
            while (rs.next()) {
                s = new Sach(
                        rs.getString("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("MoTa"),
                        rs.getString("AnhSach"),
                        rs.getFloat("GiaSach"),
                        rs.getString("TacGia"),
                        rs.getDate("NgayTao"),
                        rs.getInt("SoLuong"));
                s.setChuDe(new ChuDe(rs.getString("MaChuDe"), rs.getString("TenChuDe")));
                lstBook.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstBook;
    }

    public boolean addBook(Sach e) {
        try {
            String sql = "insert into Sach(MaSach, TenSach, MoTa, AnhSach, GiaSach, TacGia, NgayTao, MaChuDe, SoLuong)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getMaSach());
            stm.setString(2, e.getTenSach());
            stm.setString(3, e.getMoTa());
            stm.setString(4, e.getAnhSach());
            stm.setFloat(5, e.getGiaSach());
            stm.setString(6, e.getTacGia());
            stm.setDate(7, (Date)e.getNgayTao());
            stm.setString(8, e.getChuDe().getMaChuDe());
            stm.setInt(9, e.getSoLuong());

            return stm.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Sach getBookById(String maSach) {
        Sach s = null;
        try {
            String sql = "select s.*, c.TenChuDe from Sach s, ChuDe c where s.MaChuDe = c.MaChuDe"
                    + " AND s.MaSach = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, maSach);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                s = new Sach(
                        rs.getString("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("MoTa"),
                        rs.getString("AnhSach"),
                        rs.getFloat("GiaSach"),
                        rs.getString("TacGia"),
                        rs.getDate("NgayTao"),
                        rs.getInt("SoLuong"));
                s.setChuDe(new ChuDe(rs.getString("MaChuDe"), rs.getString("TenChuDe")));
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public boolean updateBook(Sach s) {
        try {
            String sql = "update Sach set MaSach=?, TenSach=?, MoTa=?, AnhSach=?, GiaSach=?, TacGia=?, NgayTao=?,"
                    + " MaChuDe=?, SoLuong = ? where MaSach=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getMaSach());
            stm.setString(2, s.getTenSach());
            stm.setString(3, s.getMoTa());
            stm.setString(4, s.getAnhSach());
            stm.setFloat(5, s.getGiaSach());
            stm.setString(6, s.getTacGia());
            stm.setDate(7, (Date) s.getNgayTao());
            stm.setString(8, s.getChuDe().getMaChuDe());
            stm.setInt(9, s.getSoLuong());
            stm.setString(10, s.getMaSach());

            return stm.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteBook(String maSach) {
        try {
            String sql = "delete from Sach where MaSach=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, maSach);

            return stm.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Sach> searchBooks(String maChuDe) {
        ArrayList<Sach> lstBook = new ArrayList<>();
        try {
            String sql = "Select s.*, c.TenChuDe from Sach s, ChuDe c where s.MaChuDe = c.MaChuDe";
            if (!maChuDe.isEmpty()) {
                sql += " AND s.MaChuDe = '" + maChuDe + "'";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Sach s = null;
            while (rs.next()) {
                s = new Sach(
                        rs.getString("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("MoTa"),
                        rs.getString("AnhSach"),
                        rs.getFloat("GiaSach"),
                        rs.getString("TacGia"),
                        rs.getDate("NgayTao"),
                        rs.getInt("SoLuong"));
                s.setChuDe(new ChuDe(rs.getString("MaChuDe"), rs.getString("TenChuDe")));
                lstBook.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstBook;
    }

    public static void main(String[] args) {
        SachDao dao = new SachDao();
        ChuDeDao dao1 = new ChuDeDao();
         ArrayList<Sach> list = dao.searchBooks("TH");
         for (Sach s : list) {
             System.out.println(s.getTenSach());
         }
//ChuDe objChuDe = dao1.layChuDeTheoMa("TH");
//       Sach objSach = new Sach("OOP2", "Lập trình OOP", "Sách nền tảng về OOP", "java_beginner.jpg", 15000, "HN", Date.valueOf("2022-11-22"));
//       objSach.setChuDe(objChuDe);
//        System.out.print(objSach.getTenSach());
//        System.out.print(objSach.getAnhSach());
//        System.out.print(objSach.getNgayTao());
//               System.out.print(objSach.getChuDe().getMaChuDe()); 
//            dao.updateBook(objSach);
//        Sach objSach = dao.getBookById("LTJV");
//        System.out.print(objSach.getTenSach());
    }
}
