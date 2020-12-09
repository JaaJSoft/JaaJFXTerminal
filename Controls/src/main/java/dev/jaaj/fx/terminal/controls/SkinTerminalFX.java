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

import com.kodedu.terminalfx.Terminal;
import com.kodedu.terminalfx.config.TerminalConfig;
import dev.jaaj.fx.terminal.config.AbstractTerminalConfig;
import dev.jaaj.fx.terminal.config.TerminalThemeConfig;
import javafx.scene.control.SkinBase;


public class SkinTerminalFX extends SkinBase<AbstractTerminal> {
    private final Terminal terminal;

    protected SkinTerminalFX(AbstractTerminal control) {
        super(control);
        TerminalConfig config = new TerminalConfig();
        AbstractTerminalConfig terminalConfig = control.getTerminalConfig();
        SyncTheme(config, control.getTerminalThemeConfig());

        String startCommand = terminalConfig.getStartCommand();
        if (!startCommand.isEmpty()) {
            config.setUnixTerminalStarter(startCommand);
            config.setWindowsTerminalStarter(startCommand);
        }
        terminal = new Terminal(config, null);
        control.lastCommand.addListener((observable, oldValue, newValue) -> {
            terminal.command(newValue);
        });
        this.getChildren().clear();
        this.getChildren().add(terminal);
    }

    private void SyncTheme(TerminalConfig config, TerminalThemeConfig themeConfig) {
        config.setForegroundColor(themeConfig.getForegroundColor());
        config.setBackgroundColor(themeConfig.getBackgroundColor());
        config.setCursorColor(themeConfig.getCursorColor());
        //config.setFontFamily(themeConfig.getFont().getFamily());
        //config.setFontSize((int) themeConfig.getFont().getSize());
    }
}
