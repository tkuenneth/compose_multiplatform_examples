import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.FlowLayout;

public class SliderWithLabel extends JPanel {

    public static final String CUSTOM_PROPERTY = "customProperty";
    private Integer customProperty = null;

    public SliderWithLabel() {
        super(new FlowLayout(FlowLayout.LEADING, 8, 8));
        setAlignmentY(TOP_ALIGNMENT);
        JSlider slider = new JSlider();
        JLabel label = new JLabel();
        slider.addChangeListener((event) -> {
            setCustomProperty(slider.getModel().getValue());
        });
        slider.setMinimum(1);
        slider.setMaximum(10);
        addPropertyChangeListener(CUSTOM_PROPERTY, (event) -> {
            int newValue = (int) event.getNewValue();
            slider.setValue(newValue);
            label.setText(String.format("%d", newValue));
        });
        setCustomProperty((slider.getMaximum() - slider.getMinimum()) / 2 + slider.getMinimum());
        add(slider);
        add(label);
    }

    public Integer getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(Integer newValue) {
        Integer oldValue = getCustomProperty();
        customProperty = newValue;
        firePropertyChange(CUSTOM_PROPERTY, oldValue, newValue);
    }
}
