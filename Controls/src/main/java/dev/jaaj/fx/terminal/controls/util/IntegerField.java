/*
 * Copyright 2021 JaaJSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.jaaj.fx.terminal.controls.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

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

        value.addListener(this::integerValue2Text);
        textProperty().addListener(this::text2IntegerValueListener);
        setTextFormatter(new TextFormatter<String>(this::integerTextFormatter));
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

    private TextFormatter.Change integerTextFormatter(TextFormatter.Change change) {
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
    }

    private void text2IntegerValueListener(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!oldValue.equals(newValue) && !newValue.isBlank() && !newValue.equals("-")) {
            setValue(Integer.parseInt(newValue));
        }
    }

    private void integerValue2Text(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (!oldValue.equals(newValue)) {
            setText(newValue.toString());
        }
    }
}
