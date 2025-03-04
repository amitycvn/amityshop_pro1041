/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ChiTietSanPham;
import model.SanPham;
import reponsitory.ChiTietSanPhamRepository;
import reponsitory.SanPhamRepository;
import util.MsgHelper;

/**
 *
 * @author Admin
 */
public class DsCTSPAnJDialog extends javax.swing.JDialog {

    /**
     * Creates new form DsCTSPAnJDialog
     */
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    List<ChiTietSanPham> listCTSP = new ArrayList<>();

    int row = -1;
//    List<SanPham> listSanPham = new ArrayList<>();

    public DsCTSPAnJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Danh sách ẩn");
        setLocationRelativeTo(null);
        listCTSP = chiTietSanPhamRepository.getListRemove();
        showDataCTSP(listCTSP);
        select();
    }

    void unRemove() {
        if (tbCTSP.getSelectedRow() < 0) {
            MsgHelper.alert(this, "Chưa chọn dữ liệu");
            return;
        }
        if (MsgHelper.confirm(this, "Hoàn tác chi tiết sản phẩm này nhớ?")) {
            try {
                String idSP = listCTSP.get(tbCTSP.getSelectedRow()).getId();
                chiTietSanPhamRepository.unremove(idSP);
                MsgHelper.alert(this, "Hoàn tác chi tiết sản phẩm thành công");
                listCTSP = chiTietSanPhamRepository.getListRemove();
                showDataCTSP(listCTSP);
            } catch (Exception e) {
                MsgHelper.alert(this, "Xuất hiện lỗi");
                throw new RuntimeException(e);
            }
        }
    }

    void select() {
        if (tbCTSP.getRowCount() > 0) {
            row = 0;
            tbCTSP.setRowSelectionInterval(row, row);
        }
    }

    void searchCTSPByTen() {
        try {
            String tenCTSP = tfTimCTSPByTen.getText();
            listCTSP = chiTietSanPhamRepository.getByTen(tenCTSP);
            this.showDataCTSP(listCTSP);
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException(e);
        }
    }

    void showDataCTSP(List<ChiTietSanPham> list) {
        int intdex = 1;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbCTSP.getModel();
        model.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            SanPham getSanPham = sanPhamRepository.getByID(chiTietSanPham.getIdSanPham());
            model.addRow(new Object[]{
                intdex++,
                "# " + chiTietSanPham.getMaCTSP(),
                getSanPham.getMa(),
                getSanPham.getTen(),
                chiTietSanPham.getSoLuong(),
                String.format("%.1f", chiTietSanPham.getGiaBan()),
                chiTietSanPham.getMoTa(),
                chiTietSanPham.getTrangThai()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tfTimCTSPByTen = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnXuatFileSP = new javax.swing.JButton();
        btnDocFileSP = new javax.swing.JButton();
        btnHoanTac = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTSP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách chi tiết sản phẩm đã ẩn"));

        tfTimCTSPByTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimCTSPByTenKeyTyped(evt);
            }
        });

        btnXuatFileSP.setText("Xuất file");

        btnDocFileSP.setText("Đọc file");

        btnHoanTac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHoanTac.setText("Hoàn tác");
        btnHoanTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTacActionPerformed(evt);
            }
        });

        jLabel4.setText("Tìm theo tên");

        tbCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã CTSP", "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Mô tả", "Trạng thái"
            }
        ));
        tbCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTSP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTimCTSPByTen, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnDocFileSP)
                                .addGap(18, 18, 18)
                                .addComponent(btnXuatFileSP)
                                .addGap(23, 23, 23))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHoanTac, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimCTSPByTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnDocFileSP)
                    .addComponent(btnXuatFileSP))
                .addGap(46, 46, 46)
                .addComponent(btnHoanTac)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHoanTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanTacActionPerformed
        // TODO add your handling code here:
        unRemove();
    }//GEN-LAST:event_btnHoanTacActionPerformed

    private void tbCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTSPMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbCTSPMouseClicked

    private void tfTimCTSPByTenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimCTSPByTenKeyTyped
        // TODO add your handling code here:
        searchCTSPByTen();
    }//GEN-LAST:event_tfTimCTSPByTenKeyTyped

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDocFileSP;
    private javax.swing.JButton btnHoanTac;
    private javax.swing.JButton btnXuatFileSP;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbCTSP;
    private javax.swing.JTextField tfTimCTSPByTen;
    // End of variables declaration//GEN-END:variables
}
