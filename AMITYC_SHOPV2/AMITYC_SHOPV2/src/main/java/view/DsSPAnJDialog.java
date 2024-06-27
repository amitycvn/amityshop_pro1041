/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.SanPham;
import reponsitory.SanPhamRepository;
import util.MsgHelper;

/**
 *
 * @author Admin
 */
public class DsSPAnJDialog extends javax.swing.JDialog {

    /**
     * Creates new form UnRemoveJDialog
     */
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    List<SanPham> listSanPham = new ArrayList<>();
//    
//    int pageSP = 1;
//    int limitSP = 7;

    public DsSPAnJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Danh sách ẩn");
        setLocationRelativeTo(null);
        listSanPham = sanPhamRepository.getListRemove();
        showData(listSanPham);
        select();
        setTable();

    }
    
    void setTable(){
        tbSanPham.getColumnModel().getColumn(4).setMinWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setMaxWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setPreferredWidth(0);

        tbSanPham.getColumnModel().getColumn(0).setMinWidth(30);
        tbSanPham.getColumnModel().getColumn(0).setMaxWidth(30);
        tbSanPham.getColumnModel().getColumn(0).setPreferredWidth(30);

        tbSanPham.getColumnModel().getColumn(1).setMinWidth(50);
        tbSanPham.getColumnModel().getColumn(1).setMaxWidth(100);
        tbSanPham.getColumnModel().getColumn(1).setPreferredWidth(50);

        tbSanPham.getColumnModel().getColumn(3).setMinWidth(110);
        tbSanPham.getColumnModel().getColumn(3).setMaxWidth(150);
        tbSanPham.getColumnModel().getColumn(3).setPreferredWidth(150);

        tbSanPham.getColumnModel().getColumn(4).setMinWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setMaxWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setPreferredWidth(0);
    }

    void searchSPByProperties() {
        try {
            String value = tfTimKiemSP.getText();
            listSanPham = sanPhamRepository.getByPropertiesUnremove(value);
            showData(listSanPham);
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException(e);
        }
    }

    void select() {
        if (tbSanPham.getRowCount() > 0) {
            tbSanPham.setRowSelectionInterval(0, 0);
        }
    }

    void exit() {
        if (MsgHelper.confirm(this, "Đóng tab này nhá?")) {
            this.dispose();
        }
    }

    void unRemoveSP() {
        if (MsgHelper.confirm(this, "Hoàn tác sản phẩm này nhớ?")) {
            try {
                String idSP = listSanPham.get(tbSanPham.getSelectedRow()).getId();
                sanPhamRepository.unremove(idSP);
                MsgHelper.alert(this, "Hoàn tác sản phẩm thành công");
                listSanPham = sanPhamRepository.getListRemove();
                showData(listSanPham);
            } catch (Exception e) {
                MsgHelper.alert(this, "Xuất hiện lỗi");
                throw new RuntimeException(e);
            }
        }
    }

    void showData(List<SanPham> list) {
        int index = 1;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbSanPham.getModel();
        model.setRowCount(0);
        for (SanPham sanPham : list) {
            model.addRow(new Object[]{
                index++,
                sanPham.getMa(),
                sanPham.getTen(),
                sanPham.getTrangThai(),
                sanPham.getId()
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        tfTimKiemSP = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnHoanTac = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnXuatFileSP = new javax.swing.JButton();
        btnDocFileSP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Trạng thái", "ID"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPham);

        tfTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfTimKiemSPKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemSPKeyTyped(evt);
            }
        });

        btnHoanTac.setText("Hoàn tác");
        btnHoanTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTacActionPerformed(evt);
            }
        });

        jLabel5.setText("Tìm kiếm ");

        btnXuatFileSP.setText("Xuất file");

        btnDocFileSP.setText("Đọc file");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTimKiemSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXuatFileSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDocFileSP))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnHoanTac, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnXuatFileSP)
                    .addComponent(btnDocFileSP))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHoanTac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
//        showDetailSP(getSPByRowinTable());
//        this.statusEdit();
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void tfTimKiemSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSPKeyPressed

    }//GEN-LAST:event_tfTimKiemSPKeyPressed

    private void tfTimKiemSPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSPKeyTyped
        // TODO add your handling code here:
        searchSPByProperties();
    }//GEN-LAST:event_tfTimKiemSPKeyTyped


    private void btnHoanTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanTacActionPerformed
        // TODO add your handling code here:
        unRemoveSP();
    }//GEN-LAST:event_btnHoanTacActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDocFileSP;
    private javax.swing.JButton btnHoanTac;
    private javax.swing.JButton btnXuatFileSP;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField tfTimKiemSP;
    // End of variables declaration//GEN-END:variables
}
