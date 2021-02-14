package dev.jaaj.fx.terminal.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.Validator;

public class IntegerField extends TextField {

    private final IntegerProperty value = new SimpleIntegerProperty();
    private final IntegerProperty minValue = new SimpleIntegerProperty();
    private final IntegerProperty maxValue = new SimpleIntegerProperty();

    public IntegerField() {
        this(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerField(Integer value) {
        this(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerField(Integer integer, int minValue, int maxValue) {
        super(integer.toString());

        this.minValue.set(minValue);
        this.maxValue.set(maxValue);

        value.set(integer);

        value.addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                setText(newValue.toString());
            }
        });
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue) && !newValue.isBlank() && !newValue.equals("-")) {
                setValue(Integer.parseInt(newValue));
            }
        });

        setTextFormatter(new TextFormatter<String>(change -> {
            String allText = this.getText();
            String text = change.getText();

            if ((allText + text).equals("-")) {
                return change;
            }
            if (text.matches("[0-9]*")) {
                try {
                    int v = Integer.parseInt(allText + text);
                    if (v >= this.minValue.getValue() && v <= this.maxValue.getValue()) {
                        return change;
                    }
                    return null;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            if (allText.isEmpty() && !text.isBlank()) {
                return change;
            }
            return null;
        }));
    }

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public int getMinValue() {
        return minValue.get();
    }

    public IntegerProperty minValueProperty() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue.set(minValue);
    }

    public int getMaxValue() {
        return maxValue.get();
    }

    public IntegerProperty maxValueProperty() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue.set(maxValue);
    }
}
