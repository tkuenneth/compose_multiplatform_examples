import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SwingDemo extends JFrame {

    public static final String[] numbers = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    private final JLabel label;

    public SwingDemo() {
        super("SwingDemo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JComponent sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        SliderWithLabel slider = new SliderWithLabel();
        sliderPanel.add(slider);
        label = new JLabel();
        label.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        slider.addPropertyChangeListener(SliderWithLabel.CUSTOM_PROPERTY, (event) -> updateLabel((int) event.getNewValue()));
        updateLabel(slider.getCustomProperty());
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(sliderPanel, BorderLayout.CENTER);
        contentPanel.add(label, BorderLayout.SOUTH);
        setContentPane(contentPanel);
        pack();
    }

    private void updateLabel(int value) {
        label.setText(numbers[value - 1]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingDemo main = new SwingDemo();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
