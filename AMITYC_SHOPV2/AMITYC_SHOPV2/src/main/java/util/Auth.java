package util;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package util;
//
//import model.NguoiDung;
//
///**
// *
// * @author Admin
// */
//public class Auth {
//    public static NguoiDung user = null;
//    
//    public static boolean isLogin(){
//        return Auth.user != null;
//    }
//    
//    public static boolean isManager(){
//        return Auth.isLogin() && Auth.user.isVaiTro();
//    }
//    
//    public static void clear(){
//        Auth.user = null ;
//    }
//    
//    public static void login(){
//        NhanVienRepository repository = new NhanVienRepository();
//        Auth.user = repository.selectById("TeoNV");
//    }
//}
