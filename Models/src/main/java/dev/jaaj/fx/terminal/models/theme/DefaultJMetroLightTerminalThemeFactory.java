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

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DefaultJMetroLightTerminalThemeFactory implements IFactoryTerminalThemeConfig {
    @Override
    public TerminalThemeConfig build() {
        TerminalThemeConfig config = new TerminalThemeConfig();
        config.setThemeName("Light JMetro");
        config.setForegroundColor(Color.BLACK);
        config.setBackgroundColor(Color.web("#F8F8F8"));
        config.setCursorColor(Color.BLACK);
        config.setFont(new Font("Consolas", 14));
        return config;
    }
}
