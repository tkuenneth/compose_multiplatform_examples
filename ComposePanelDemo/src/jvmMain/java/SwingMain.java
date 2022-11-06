import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SwingMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame main = new JFrame("ComposePanelDemo");
            main.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JPanel contentPanel = new JPanel(new BorderLayout());
            main.setContentPane(contentPanel);
            main.pack();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
