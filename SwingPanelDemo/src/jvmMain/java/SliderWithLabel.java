import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SliderWithLabel extends JPanel {

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public static final String VALUE = "value";
    private Integer value = null;

    public SliderWithLabel() {
        super(new FlowLayout(FlowLayout.LEADING, 8, 8));
        setAlignmentY(TOP_ALIGNMENT);
        JSlider slider = new JSlider();
        JLabel label = new JLabel();
        slider.addChangeListener((event) -> {
            Integer newValue = slider.getModel().getValue();
            label.setText(String.format("%d", newValue));
            setValue(newValue);
        });
        slider.setMinimum(1);
        slider.setMaximum(10);
        addPropertyChangeListener(VALUE, (event) -> {
            slider.setValue((int) event.getNewValue());
        });
        slider.setValue(4);
        add(slider);
        add(label);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer newValue) {
        Integer oldValue = getValue();
        value = newValue;
        pcs.firePropertyChange(VALUE, oldValue, newValue);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }
}
