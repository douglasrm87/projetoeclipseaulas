package bancodadossupabse.main;

import javax.swing.SwingUtilities;
import bancodadossupabse.gui.TelaPrincipal;

public class Principal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
