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

package dev.jaaj.fx.terminal.controls;

import com.kodedu.terminalfx.config.TerminalConfig;
import dev.jaaj.fx.terminal.config.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.scene.control.SkinBase;


public class SkinTerminalFX extends SkinBase<Terminal> {
    private final com.kodedu.terminalfx.Terminal terminal;

    protected SkinTerminalFX(Terminal control) {
        super(control);
        TerminalConfig config = new TerminalConfig();
        AbstractShellConfig terminalConfig = control.getTerminalConfig();
        syncTheme(config, control.getTerminalThemeConfig());

        String startCommand = terminalConfig.getStartCommand();
        if (!startCommand.isEmpty()) {
            config.setUnixTerminalStarter(startCommand);
            config.setWindowsTerminalStarter(startCommand);
        }
        terminal = new com.kodedu.terminalfx.Terminal(config, null);
        control.lastCommand.addListener((observable, oldValue, newValue) -> {
            terminal.command(newValue);
        });
        this.getChildren().clear();
        this.getChildren().add(terminal);
    }

    private void syncTheme(TerminalConfig config, TerminalThemeConfig themeConfig) {
        config.setForegroundColor(themeConfig.getForegroundColor());
        config.setBackgroundColor(themeConfig.getBackgroundColor());
        config.setCursorColor(themeConfig.getCursorColor());
        if (themeConfig.getFont() != null) {
            config.setFontFamily(themeConfig.getFont().getFamily());
            config.setFontSize((int) themeConfig.getFont().getSize());
        }
    }
}
