import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SwingDemo {

    private static final String[] numbers = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame main = new JFrame("SwingDemo");
            main.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JComponent sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            SliderWithLabel slider = new SliderWithLabel();
            sliderPanel.add(slider);
            JLabel label = new JLabel();
            label.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            slider.addPropertyChangeListener(SliderWithLabel.VALUE, (event) -> {
                label.setText(numbers[(int) event.getNewValue() - 1]);
            });
            slider.setValue(5);
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(sliderPanel, BorderLayout.CENTER);
            contentPanel.add(label, BorderLayout.SOUTH);
            main.setContentPane(contentPanel);
            main.pack();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
