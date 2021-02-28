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

package dev.jaaj.fx.terminal.controls;

import com.kodedu.terminalfx.config.TerminalConfig;
import com.kodedu.terminalfx.helper.IOHelper;
import com.kodedu.terminalfx.helper.ThreadHelper;
import dev.jaaj.fx.terminal.config.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.scene.control.SkinBase;

import java.util.Objects;


public class SkinTerminalFX extends SkinBase<Terminal> {
    private final com.kodedu.terminalfx.Terminal terminal;

    protected SkinTerminalFX(Terminal control) {
        super(control);
        TerminalConfig config = new TerminalConfig();
        AbstractShellConfig terminalConfig = control.getTerminalConfig();
        syncTheme(config, control.getTerminalThemeConfig());

        String startCommand = terminalConfig.getCommandLine();
        if (!startCommand.isEmpty()) {
            config.setUnixTerminalStarter(startCommand);
            config.setWindowsTerminalStarter(startCommand);
        }
        terminal = new com.kodedu.terminalfx.Terminal(config, null);

        control.lastCommandProperty().addListener((observable, oldValue, newValue) -> terminal.command(newValue));

        control.closedProperty().addListener((observable, oldValue, newValue) -> destroy());

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

    private void destroy() {
        ThreadHelper.start(() -> {
            while (Objects.isNull(terminal.getProcess())) {
                ThreadHelper.sleep(250);
            }
            terminal.getProcess().destroy();
            IOHelper.close(terminal.getInputReader(), terminal.getErrorReader(), terminal.getOutputWriter());
        });
    }

}
