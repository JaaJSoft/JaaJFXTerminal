/*
 * Copyright 2020 JaaJSoft
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

package dev.jaaj.fx.terminal.config.factory.theme;

import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.scene.paint.Color;

public class DefaultJMetroDarkTerminalThemeFactory implements IFactoryTerminalThemeConfig {
    @Override
    public TerminalThemeConfig build() {
        TerminalThemeConfig config = new TerminalThemeConfig();
        config.setForegroundColor(Color.WHITE);
        config.setBackgroundColor(Color.web("#1E1E1E"));
        config.setCursorColor(Color.WHITE);
        return config;
    }
}
