/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Sach {
    private String maSach;
    private String tenSach;
    private String moTa;
    private String anhSach;
    private float giaSach;
    private String tacGia;
    private Date ngayTao;
    private ChuDe chuDe;
    private int soLuong;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String moTa, String anhSach, float giaSach, String tacGia, Date ngayTao, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.moTa = moTa;
        this.anhSach = anhSach;
        this.giaSach = giaSach;
        this.tacGia = tacGia;
        this.ngayTao = ngayTao;
        this.soLuong = soLuong;
    }

    

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnhSach() {
        return anhSach;
    }

    public void setAnhSach(String anhSach) {
        this.anhSach = anhSach;
    }

    public float getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(float giaSach) {
        this.giaSach = giaSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public ChuDe getChuDe() {
        return chuDe;
    }

    public void setChuDe(ChuDe chuDe) {
        this.chuDe = chuDe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    

    
}
