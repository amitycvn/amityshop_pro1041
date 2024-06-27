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
public class PhuongThucThanhToan {
    private UUID idPTTT;
    private String tenPhuongThucTT;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;

    public PhuongThucThanhToan() {
    }

    public PhuongThucThanhToan(UUID idPTTT, String tenPhuongThucTT, Date ngayTao, Date ngaySua, String trangThai, int delete) {
        this.idPTTT = idPTTT;
        this.tenPhuongThucTT = tenPhuongThucTT;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdPTTT() {
        return idPTTT;
    }

    public void setIdPTTT(UUID idPTTT) {
        this.idPTTT = idPTTT;
    }

    public String getTenPhuongThucTT() {
        return tenPhuongThucTT;
    }

    public void setTenPhuongThucTT(String tenPhuongThucTT) {
        this.tenPhuongThucTT = tenPhuongThucTT;
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
