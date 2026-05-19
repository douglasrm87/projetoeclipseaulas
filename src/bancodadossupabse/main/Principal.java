package bancodadossupabse.main;

import javax.swing.SwingUtilities;
import bancodadossupabse.gui.TelaPrincipal;

/*
@douglasrm87 ➜ /workspaces/projetoeclipseaulas/src (main) $ 
        javac -d .. $(find . -name "*.java")

*/
public class Principal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
