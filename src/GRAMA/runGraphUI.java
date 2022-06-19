package GRAMA;

import javax.swing.*;

/**
 * Permet d'executer l'application
 * @author Giani VERRELLI - G2S2
 * @version 1.0
 */
public class runGraphUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GraphUI graphInterface = new GraphUI();
                graphInterface.setVisible(true);
            }
        });
    }
}
