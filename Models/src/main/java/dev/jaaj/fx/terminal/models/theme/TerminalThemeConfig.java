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

package dev.jaaj.fx.terminal.models.theme;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;

public class TerminalThemeConfig {
    private final StringProperty themeName = new SimpleStringProperty();
    private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.BLACK);
    private final ObjectProperty<Color> foregroundColor = new SimpleObjectProperty<>(Color.WHITE);
    private final ObjectProperty<Color> cursorColor = new SimpleObjectProperty<>(Color.WHITE);
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(null);
    //cursorBlinking
    //scrollSpeed

    public Color getBackgroundColor() {
        return backgroundColor.get();
    }

    public ObjectProperty<Color> backgroundColorProperty() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor.set(backgroundColor);
    }

    public Color getForegroundColor() {
        return foregroundColor.get();
    }

    public ObjectProperty<Color> foregroundColorProperty() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor.set(foregroundColor);
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

    public String getThemeName() {
        return themeName.get();
    }

    public StringProperty themeNameProperty() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName.set(themeName);
    }

    @Override
    public String toString() {
        return themeName.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminalThemeConfig that = (TerminalThemeConfig) o;
        return Objects.equals(getThemeName(), that.getThemeName()) && Objects.equals(getBackgroundColor(), that.getBackgroundColor()) && Objects.equals(getForegroundColor(), that.getForegroundColor()) && Objects.equals(getCursorColor(), that.getCursorColor()) && Objects.equals(getFont(), that.getFont());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getThemeName(), getBackgroundColor(), getForegroundColor(), getCursorColor(), getFont());
    }
}
