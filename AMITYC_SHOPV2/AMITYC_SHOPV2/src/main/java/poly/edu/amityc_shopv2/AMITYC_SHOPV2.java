/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poly.edu.amityc_shopv2;

import com.formdev.flatlaf.FlatLightLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.AMITYC;

/**
 *
 * @author Asus
 */
public class AMITYC_SHOPV2 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            AMITYC AMI = new AMITYC();
            AMI.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AMITYC_SHOPV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
