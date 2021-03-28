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

package dev.jaaj.fx.terminal.controls.theme;

import dev.jaaj.fx.core.form.AbstractForm;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ResourceBundle;

public class TerminalThemeForm extends AbstractForm<TerminalThemeConfig> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(TerminalThemeForm.class.getPackageName() + ".TerminalTheme");

    private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>();
    private final ObjectProperty<Color> textColor = new SimpleObjectProperty<>();
    private final ObjectProperty<Color> cursorColor = new SimpleObjectProperty<>();
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>();

    public TerminalThemeForm() {
        itemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                backgroundColor.set(newValue.getBackgroundColor());
                textColor.set(newValue.getForegroundColor());
                cursorColor.set(newValue.getCursorColor());
                font.set(newValue.getFont());
            }
        });
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new TerminalThemeFormSkin(this);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public TerminalThemeConfig apply() {
        TerminalThemeConfig item = getItem();
        if (item != null) {
            item.setBackgroundColor(getBackgroundColor());
            item.setForegroundColor(getTextColor());
            item.setCursorColor(getCursorColor());
            item.setFont(getFont());
        }
        return item;
    }

    public Color getBackgroundColor() {
        return backgroundColor.get();
    }

    public ObjectProperty<Color> backgroundColorProperty() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor.set(backgroundColor);
    }

    public Color getTextColor() {
        return textColor.get();
    }

    public ObjectProperty<Color> textColorProperty() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor.set(textColor);
    }

    public Color getCursorColor() {
        return cursorColor.get();
    }

    public ObjectProperty<Color> cursorColorProperty() {
        return cursorColor;
    }

    public void setCursorColor(Color cursorColor) {
        this.cursorColor.set(cursorColor);
    }

    public Font getFont() {
        return font.get();
    }

    public ObjectProperty<Font> fontProperty() {
        return font;
    }

    public void setFont(Font font) {
        this.font.set(font);
    }
}
