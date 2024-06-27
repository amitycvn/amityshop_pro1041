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
public class PhuongThucThanhToanChiTiet {
    private UUID idPTTTChiTiet;
    private UUID idHD;
    private UUID idPTTT;
    private Float soTienmat;
    private Float soTienChuyenKhoan;
    private Float tongTien;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;

    public PhuongThucThanhToanChiTiet() {
    }

    public PhuongThucThanhToanChiTiet(UUID idPTTTChiTiet, UUID idHD, UUID idPTTT, Float soTienmat, Float soTienChuyenKhoan, Float tongTien, Date ngayTao, Date ngaySua, String trangThai, int delete) {
        this.idPTTTChiTiet = idPTTTChiTiet;
        this.idHD = idHD;
        this.idPTTT = idPTTT;
        this.soTienmat = soTienmat;
        this.soTienChuyenKhoan = soTienChuyenKhoan;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdPTTTChiTiet() {
        return idPTTTChiTiet;
    }

    public void setIdPTTTChiTiet(UUID idPTTTChiTiet) {
        this.idPTTTChiTiet = idPTTTChiTiet;
    }

    public UUID getIdHD() {
        return idHD;
    }

    public void setIdHD(UUID idHD) {
        this.idHD = idHD;
    }

    public UUID getIdPTTT() {
        return idPTTT;
    }

    public void setIdPTTT(UUID idPTTT) {
        this.idPTTT = idPTTT;
    }

    public Float getSoTienmat() {
        return soTienmat;
    }

    public void setSoTienmat(Float soTienmat) {
        this.soTienmat = soTienmat;
    }

    public Float getSoTienChuyenKhoan() {
        return soTienChuyenKhoan;
    }

    public void setSoTienChuyenKhoan(Float soTienChuyenKhoan) {
        this.soTienChuyenKhoan = soTienChuyenKhoan;
    }

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
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
