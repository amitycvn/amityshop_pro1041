/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.HoaDonService;
import service.SAVENHANVIEN;

/**
 *
 * @author Asus
 */
public class HoaDon extends javax.swing.JPanel implements Runnable, ThreadFactory {
//    private boolean webcamreunning = false;

    private WebcamPanel panel = null;
    private Webcam webCam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private volatile boolean isRunning = true;
    private HoaDonService hds = new HoaDonService();
    private ArrayList<model.HoaDon> lstHD;
    private int index = 0;
    private model.HoaDon hd;
    private DefaultTableModel model;
    private model.KhachHang kh;
    public HoaDon() throws Exception {
        initComponents();
        innitWebcam();
        model = (DefaultTableModel) tblHoaDon.getModel();
        LoadDataHoaDonToTable();
//        txtQRCode.setText("     ");
    }

    private void innitWebcam() {
        try {
            Dimension size = WebcamResolution.VGA.getSize();
        webCam = Webcam.getWebcams().get(0);
        webCam.setViewSize(size);
        panel = new WebcamPanel(webCam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panel.setFPSLimit(30);
        PN3.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 256, 235));
        executor.execute(this);
        } finally {
            webCam.close();
        }
    }

    @Override
    public void run() {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
            }
            Result rs = null;
            BufferedImage image = null;
            if (webCam.isOpen()) {
                if ((image = webCam.getImage()) == null) {
                    webCam.close();
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                rs = new MultiFormatReader().decode(bitmap);

            } catch (Exception e) {
                Logger.getLogger(HoaDon.getClass().getName()).log(Level.SEVERE, null, e);

            }
            if (rs != null) {
                txtQRCode.setText(rs.getText());
                webCam.close();
            }

    }
//    @Override
//    public void run() {
//        while (isRunning) {
//            long currentTime = System.currentTimeMillis();
//            if (currentTime - lastCaptureTime >= captureInterval) {
//                lastCaptureTime = currentTime;
//
//                // Lấy hình ảnh từ webcam và xử lý
//                BufferedImage image = webCam.getImage();
//                if (image != null) {
//                    // Xử lý hình ảnh
//                    LuminanceSource source = new BufferedImageLuminanceSource(image);
//                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//
//                    try {
//                        Result rs = new MultiFormatReader().decode(bitmap);
//                        if (rs != null) {
//                            System.out.println("" + rs.getText());
//                            txtQRCode.setText(rs.getText());
//                            stopWebcam();  // Dừng vòng lặp khi mã vạch được đọc thành công
//                        }
//                    } catch (NotFoundException e) {
//                        Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
//                    } catch (Exception e) {
//                        Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
//                    }
//                }
//            }
//
//            try {
//                Thread.sleep(10); // Ngủ để giảm áp lực lên CPU
//            } catch (InterruptedException e) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
//    @Override
//    public void run() {
//        while (isRunning) {  // Thay đổi từ do-while sang while với điều kiện kiểm soát
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
//                Thread.currentThread().interrupt();  // Bắt lại ngoại lệ và đánh dấu lại interrupt
//            }
//
//            Result rs = null;
//            BufferedImage image = null;
//            if (webCam.isOpen()) {
//                if ((image = webCam.getImage()) == null) {
//                    continue;
//                }
//            }
//
//            // Xử lý hình ảnh
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            try {
//                rs = new MultiFormatReader().decode(bitmap);
//            } catch (NotFoundException e) {
//                // Xử lý NotFoundException
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
//            } catch (Exception e) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
//            }
//
//            if (rs != null) {
//                System.out.println("" + rs.ge

//                txtQRCode.setText(rs.getText());
//                stopWebcam();  // Dừng vòng lặp khi mã vạch được đọc thành công
//            }
//        }
//    }
//
//    public void stopWebcam() {
//        isRunning = false;  // Dừng vòng lặp
//        webCam.close();     // Giải phóng tài nguyên webcam
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        PANELALL = new javax.swing.JPanel();
        PN1 = new javax.swing.JPanel();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        rdoChoThanhToan = new javax.swing.JRadioButton();
        rdoBihuy = new javax.swing.JRadioButton();
        PN2 = new javax.swing.JPanel();
        HoaDon = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        PN3 = new javax.swing.JPanel();
        PN4 = new javax.swing.JPanel();
        GioHang = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        PN5 = new javax.swing.JPanel();
        SanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        PN6 = new javax.swing.JPanel();
        txtTenKH = new javax.swing.JTextField();
        lblTenKH = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        PN7 = new javax.swing.JPanel();
        lblMaHD = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        lblGiamGia = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        lblThanhToan = new javax.swing.JLabel();
        txtTienThanhToan = new javax.swing.JTextField();
        lblTienKhachDua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        lblPhuongThucTT = new javax.swing.JLabel();
        cboPhuongThucTT = new javax.swing.JComboBox<>();
        lblVND = new javax.swing.JLabel();
        lblVND1 = new javax.swing.JLabel();
        lblVND2 = new javax.swing.JLabel();
        lblVND4 = new javax.swing.JLabel();
        lblVND3 = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtQRCode = new javax.swing.JTextField();

        setLayout(new javax.swing.OverlayLayout(this));

        PN1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRẠNG THÁI HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDaThanhToan.setForeground(new java.awt.Color(0, 153, 51));
        rdoDaThanhToan.setText("Đã thanh toán");

        buttonGroup1.add(rdoChoThanhToan);
        rdoChoThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChoThanhToan.setText("Chờ thanh toán");
        rdoChoThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChoThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoBihuy);
        rdoBihuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoBihuy.setForeground(new java.awt.Color(255, 153, 0));
        rdoBihuy.setText("Bị hủy");

        javax.swing.GroupLayout PN1Layout = new javax.swing.GroupLayout(PN1);
        PN1.setLayout(PN1Layout);
        PN1Layout.setHorizontalGroup(
            PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN1Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(rdoBihuy, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(rdoChoThanhToan)
                .addGap(70, 70, 70)
                .addComponent(rdoDaThanhToan)
                .addGap(23, 23, 23))
        );
        PN1Layout.setVerticalGroup(
            PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(rdoChoThanhToan)
                    .addComponent(rdoBihuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PN2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ HÓA ĐƠN", "TÊN NV", "TÊN KH", "NGÀY TẠO", "TRẠNG THÁI"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        HoaDon.setViewportView(tblHoaDon);

        javax.swing.GroupLayout PN2Layout = new javax.swing.GroupLayout(PN2);
        PN2.setLayout(PN2Layout);
        PN2Layout.setHorizontalGroup(
            PN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HoaDon)
                .addContainerGap())
        );
        PN2Layout.setVerticalGroup(
            PN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN2Layout.createSequentialGroup()
                .addComponent(HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        PN3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PN3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PN3MouseClicked(evt);
            }
        });
        PN3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PN4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GIỎ HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        GioHang.setViewportView(tblGioHang);

        jButton4.setText("XÓA");

        javax.swing.GroupLayout PN4Layout = new javax.swing.GroupLayout(PN4);
        PN4.setLayout(PN4Layout);
        PN4Layout.setHorizontalGroup(
            PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GioHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PN4Layout.setVerticalGroup(
            PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN4Layout.createSequentialGroup()
                .addGroup(PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PN4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(GioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PN4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jButton4)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        PN5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        SanPham.setViewportView(tblSanPham);

        jLabel1.setText("TÌM KIẾM  : ");

        jButton5.setText("Tìm ");

        javax.swing.GroupLayout PN5Layout = new javax.swing.GroupLayout(PN5);
        PN5.setLayout(PN5Layout);
        PN5Layout.setHorizontalGroup(
            PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SanPham)
                .addContainerGap())
            .addGroup(PN5Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton5)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        PN5Layout.setVerticalGroup(
            PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN5Layout.createSequentialGroup()
                .addGroup(PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        PN6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTenKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTenKH.setBorder(null);

        lblTenKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTenKH.setText("Tên Khách Hàng : ");

        lblMaKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaKH.setText("Số Điện Thoại : ");

        txtMaKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaKH.setBorder(null);

        javax.swing.GroupLayout PN6Layout = new javax.swing.GroupLayout(PN6);
        PN6.setLayout(PN6Layout);
        PN6Layout.setHorizontalGroup(
            PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKH)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(14, 14, 14))
        );
        PN6Layout.setVerticalGroup(
            PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKH)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PN7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblMaHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaHD.setText("Mã hóa đơn :");

        txtMaHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaHD.setBorder(null);

        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTongTien.setText("Tổng tiền :");

        txtTongTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTongTien.setBorder(null);

        lblGiamGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGiamGia.setText("Giảm giá :");

        txtGiamGia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGiamGia.setBorder(null);

        lblThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThanhToan.setText("Thanh toán :");

        txtTienThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTienThanhToan.setBorder(null);

        lblTienKhachDua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTienKhachDua.setText("Tiền khách đưa :");

        txtTienKhachDua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTienKhachDua.setBorder(null);

        lblTienThua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTienThua.setText("Tiền thừa :");

        txtTienThua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTienThua.setBorder(null);

        lblPhuongThucTT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPhuongThucTT.setText("Phương thức thanh toán :");

        cboPhuongThucTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pay Cash", "Credit Card", "VNQR" }));

        lblVND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND.setText("VNĐ");

        lblVND1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND1.setText("VNĐ");

        lblVND2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND2.setText("VNĐ");

        lblVND4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND4.setText("VNĐ");

        lblVND3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND3.setText("VNĐ");

        btnTaoHoaDon.setText("TẠO HÓA ĐƠN");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jButton2.setText("HỦY HÓA ĐƠN");

        jButton3.setText("THANH TOÁN");

        javax.swing.GroupLayout PN7Layout = new javax.swing.GroupLayout(PN7);
        PN7.setLayout(PN7Layout);
        PN7Layout.setHorizontalGroup(
            PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addComponent(lblThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVND2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVND1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PN7Layout.createSequentialGroup()
                                .addComponent(lblTienThua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTienThua))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PN7Layout.createSequentialGroup()
                                .addComponent(lblTienKhachDua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVND3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PN7Layout.createSequentialGroup()
                                .addComponent(lblVND4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 11, Short.MAX_VALUE))))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addComponent(lblPhuongThucTT)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PN7Layout.createSequentialGroup()
                                .addComponent(lblMaHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaHD))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PN7Layout.createSequentialGroup()
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                        .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon)
                        .addGap(107, 107, 107))))
        );
        PN7Layout.setVerticalGroup(
            PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVND))
                .addGap(26, 26, 26)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGia)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVND1))
                .addGap(35, 35, 35)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThanhToan)
                    .addComponent(txtTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVND2))
                .addGap(36, 36, 36)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienKhachDua)
                    .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVND4)))
                .addGap(30, 30, 30)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienThua)
                    .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVND3)))
                .addGap(18, 18, 18)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhuongThucTT)
                    .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnTaoHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(86, 86, 86))
        );

        txtQRCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQRCode.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout PANELALLLayout = new javax.swing.GroupLayout(PANELALL);
        PANELALL.setLayout(PANELALLLayout);
        PANELALLLayout.setHorizontalGroup(
            PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELALLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PANELALLLayout.createSequentialGroup()
                        .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PANELALLLayout.createSequentialGroup()
                                .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(PN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQRCode, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                    .addGroup(PANELALLLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PN6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PANELALLLayout.setVerticalGroup(
            PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELALLLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANELALLLayout.createSequentialGroup()
                        .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PANELALLLayout.createSequentialGroup()
                                .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(PN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PANELALLLayout.createSequentialGroup()
                                .addComponent(PN3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(txtQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PANELALLLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(PN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        add(PANELALL);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        try {
            kh = new model.KhachHang();
            hd = new model.HoaDon();
            kh.setNguoiTao(SAVENHANVIEN.luuNguoiDung.getHoTen());
            hd.setIdNguoiDung(SAVENHANVIEN.luuNguoiDung.getIdND());

            if (hds.themHoaDon(kh, hd)) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại !");
            }
            LoadDataHoaDonToTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void rdoChoThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChoThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChoThanhToanActionPerformed

    private void PN3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PN3MouseClicked

    }//GEN-LAST:event_PN3MouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        index = tblHoaDon.getSelectedRow();
        if (index >= 0 || lstHD.size() > 0) {
            ShowDetail();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity:");
    }//GEN-LAST:event_tblSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane GioHang;
    private javax.swing.JScrollPane HoaDon;
    private javax.swing.JPanel PANELALL;
    private javax.swing.JPanel PN1;
    private javax.swing.JPanel PN2;
    private javax.swing.JPanel PN3;
    private javax.swing.JPanel PN4;
    private javax.swing.JPanel PN5;
    private javax.swing.JPanel PN6;
    private javax.swing.JPanel PN7;
    private javax.swing.JScrollPane SanPham;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboPhuongThucTT;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblPhuongThucTT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblVND;
    private javax.swing.JLabel lblVND1;
    private javax.swing.JLabel lblVND2;
    private javax.swing.JLabel lblVND3;
    private javax.swing.JLabel lblVND4;
    private javax.swing.JRadioButton rdoBihuy;
    private javax.swing.JRadioButton rdoChoThanhToan;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtQRCode;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThanhToan;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    private void LoadDataHoaDonToTable() throws Exception {
        lstHD = hds.getAllHoaDon();
        int dong = 1;
        model.setRowCount(0);
        for (model.HoaDon hoaDon : lstHD) {
            if (hoaDon.getDelete() == 0) {
                model.addRow(new Object[]{
                    dong++, hoaDon.getIdHD(), hoaDon.getTenNhanVien(), hoaDon.getTenKhachHang(), hoaDon.getNgayTao(), hoaDon.getTrangThai()
                });
            }
        }

    }

    private void ShowDetail() {
        hd = lstHD.get(index);
        String id = String.valueOf(hd.getIdHD());
        txtMaHD.setText(id);
        txtGiamGia.setText(String.valueOf(hd.getGiamGia()));
        txtTongTien.setText(String.valueOf(hd.getGiaTien()));

    }
}
