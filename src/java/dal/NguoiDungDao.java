/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import model.NguoiDung;

/**
 *
 * @author Admin
 */
public class NguoiDungDao extends DBContext{
    public NguoiDung getUserById(String userName, String password) {
        NguoiDung s = null;
        try {
            String sql = "select n.* from NguoiDung n where n.userName = ? AND n.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                s = new NguoiDung(rs.getString("UserName"),
                        rs.getString("PassWord"), 
                        rs.getString("HoTen"), 
                        rs.getString("DienThoai"), 
                        rs.getString("Email"), 
                        rs.getString("DiaChi"), 
                        rs.getBoolean("IsAdmin"));
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public int getQuantityUser() {
        int q = 0;
        try {
            String sql = "Select COUNT(*) from NguoiDung where IsAdmin = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                q = rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
    
    public NguoiDung getUserById(String userName) {
        NguoiDung s = null;
        try {
            String sql = "select n.* from NguoiDung n where n.userName = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                s = new NguoiDung(rs.getString("UserName"),
                        rs.getString("PassWord"), 
                        rs.getString("HoTen"), 
                        rs.getString("DienThoai"), 
                        rs.getString("Email"), 
                        rs.getString("DiaChi"), 
                        rs.getBoolean("IsAdmin"));
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public NguoiDung findByUserNameAndEmail(String email) {
     NguoiDung s = null;
        try {
            String sql = "Select * from NguoiDung where Email = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s = new NguoiDung(rs.getString("UserName"),
                        rs.getString("PassWord"), 
                        rs.getString("HoTen"), 
                        rs.getString("DienThoai"), 
                        rs.getString("Email"), 
                        rs.getString("DiaChi"), 
                        rs.getBoolean("IsAdmin"));
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static void main(String[] args) {
        NguoiDungDao dao = new NguoiDungDao();
//        NguoiDung objNguoiDung = dao.findByUserNameAndEmail("hieunv", "nguyenhieuskynett@gmail.com");
       
//        System.out.println(objNguoiDung.getUserName());
    }
}
