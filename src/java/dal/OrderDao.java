/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Item;
import model.NguoiDung;
import model.Order;
import model.Sach;

/**
 *
 * @author Admin
 */
public class OrderDao extends DBContext{
    public boolean addOrder(NguoiDung objNguoiDung, Cart objCart) {
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        try {
            
            String sql = "insert into [Order](UserName, NgayTao, TotalMoney)"
                    + " values (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, objNguoiDung.getUserName());
            stm.setDate(2, date);
            stm.setFloat(3, objCart.getTotalMoney());
            stm.executeUpdate();
            String sql1 = "select top 1 OrderID from [Order] order by OrderID desc";
            PreparedStatement ps = connection.prepareStatement(sql1);
            ResultSet rs = ps.executeQuery();
            //Thêm vào bảng order_details
            if (rs.next()) {
                int oid = rs.getInt("OrderID");
                for (Item i : objCart.getItems()) {
                    String xSql = "insert into Order_Details (OrderId, MaSach, DonGia,"
                            + "soLuong) values (?, ?, ?, ?)";
                    PreparedStatement ps1 = connection.prepareStatement(xSql);
                    ps1.setInt(1, oid);
                    ps1.setString(2, i.getSach().getMaSach());
                    ps1.setFloat(3, i.getGiaSach());
                    ps1.setInt(4, i.getSoLuong());
                    return ps1.executeUpdate() > 0;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int getQuantityOrder() {
        int q = 0;
        try {
            String sql = "Select COUNT(*) from [Order]";
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
    
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> lstOrder = new ArrayList<>();
        try {
            String sql = "Select * from [Order]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Order s = null;
            while (rs.next()) {
                s = new Order(
                        rs.getString("OrderID"),
                        rs.getString("UserName"),
                        rs.getDate("NgayTao"),
                        rs.getFloat("TotalMoney")
                        );
                lstOrder.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstOrder;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.print("I LOVE YOU <3" + "\n");
        }
    }
    
}
