/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Asus
 */
public class GioHangChiTiet {

    private UUID idGHCT;
    private UUID idCTSP;
    private UUID idGH;
    private int soLuong;
    private Float gia;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;

    public GioHangChiTiet() {
    }

    public GioHangChiTiet(UUID idGHCT, UUID idCTSP, UUID idGH, int soLuong, Float gia, Date ngayTao, Date ngaySua, String trangThai, int delete) {
        this.idGHCT = idGHCT;
        this.idCTSP = idCTSP;
        this.idGH = idGH;
        this.soLuong = soLuong;
        this.gia = gia;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdGHCT() {
        return idGHCT;
    }

    public void setIdGHCT(UUID idGHCT) {
        this.idGHCT = idGHCT;
    }

    public UUID getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(UUID idCTSP) {
        this.idCTSP = idCTSP;
    }

    public UUID getIdGH() {
        return idGH;
    }

    public void setIdGH(UUID idGH) {
        this.idGH = idGH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
    
}
