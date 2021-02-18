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

package dev.jaaj.fx.terminal.config.terminal;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TerminalThemeConfig {
    private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.BLACK);
    private final ObjectProperty<Color> foregroundColor = new SimpleObjectProperty<>(Color.WHITE);
    private final ObjectProperty<Color> cursorColor = new SimpleObjectProperty<>(Color.WHITE);
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(null);

    public TerminalThemeConfig() {
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
}
