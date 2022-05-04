/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChuDe;

/**
 *
 * @author Admin
 */
public class ChuDeDao extends DBContext{
    public ArrayList<ChuDe> layDanhSachChuDe() {
        ArrayList<ChuDe> lstChuDe = new ArrayList<>();
        try {
            String sql = "Select * from ChuDe";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            ChuDe c = null;
            while (rs.next()) {
                c = new ChuDe(
                        rs.getString("MaChuDe"),
                        rs.getString("TenChuDe")
                        );
                lstChuDe.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstChuDe;
    }
    
    public ChuDe layChuDeTheoMa(String maChuDe) {
        ChuDe s = null;
        try {
            String sql = "select * from ChuDe where MaChuDe = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, maChuDe);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                s = new ChuDe(
                        rs.getString("MaChuDe"),
                        rs.getString("TenChuDe")
                        );
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static void main(String[] args) {
        ChuDeDao dao = new ChuDeDao();
         ArrayList<ChuDe> list = dao.layDanhSachChuDe();
//         for (ChuDe s : list) {
//             System.out.println(s.getTenChuDe());
//         }
        ChuDe objChuDe = dao.layChuDeTheoMa("TH");
        System.out.println(objChuDe.getTenChuDe());
    }
}
