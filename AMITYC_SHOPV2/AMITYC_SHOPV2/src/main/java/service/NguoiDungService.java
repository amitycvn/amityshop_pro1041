/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.NguoiDung;
import reponsitory.NguoiDungReponsitory;

/**
 *
 * @author Asus
 */
public class NguoiDungService {
    private NguoiDungReponsitory ndr = new NguoiDungReponsitory();
    public NguoiDung checkLogin( String manguoidung,String matkhau) throws Exception{
        return ndr.checkLogin(manguoidung, matkhau);
    }
}
