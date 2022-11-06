import javax.swing.*;
import java.awt.BorderLayout;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SwingMain {

    private static final String[] numbers = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame main = new JFrame("ComposePanelDemo");
            main.setDefaultCloseOperation(EXIT_ON_CLOSE);
            Box box = Box.createVerticalBox();
            box.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            JLabel label = new JLabel();
            box.add(label);
            box.add(Box.createVerticalStrut(8));
            JButton button = new JButton("#1");
            box.add(button);
            JComponent composePanel = MainKt.createComposePanel();
            composePanel.addPropertyChangeListener(MainKt.MY_CUSTOM_PROPERTY, evt -> {
                label.setText(String.format("%s", numbers[(int) evt.getNewValue() - 1]));
            });
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(composePanel, BorderLayout.CENTER);
            contentPanel.add(box, BorderLayout.SOUTH);
            main.setContentPane(contentPanel);
            main.pack();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
