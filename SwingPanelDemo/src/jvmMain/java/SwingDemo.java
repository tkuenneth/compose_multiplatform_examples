import javax.swing.*;
import java.awt.*;

import static java.awt.Component.TOP_ALIGNMENT;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SwingDemo {

    private static String [] numbers = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("SwingDemo");
            f.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JComponent sliderPanel =  new JPanel(new FlowLayout(FlowLayout.LEADING));
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
            f.setContentPane(contentPanel);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
