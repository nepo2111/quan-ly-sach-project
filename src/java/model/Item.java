/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Item {
    private Sach sach;
    private int soLuong;
    private float giaSach;

    public Item() {
    }

    public Item(Sach sach, int soLuong, float giaSach) {
        this.sach = sach;
        this.soLuong = soLuong;
        this.giaSach = giaSach;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(float giaSach) {
        this.giaSach = giaSach;
    }
    
    
}
