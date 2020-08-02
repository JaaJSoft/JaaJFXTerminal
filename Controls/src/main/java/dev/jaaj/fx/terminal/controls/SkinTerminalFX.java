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
import javafx.scene.control.SkinBase;


public class SkinTerminalFX extends SkinBase<AbstractTerminal> {
    private final Terminal terminal;

    protected SkinTerminalFX(AbstractTerminal control) {
        super(control);
        TerminalConfig config = new TerminalConfig();
        if (!control.getStartCommand().isEmpty()) {
            config.setWindowsTerminalStarter(control.getStartCommand());
        }
        terminal = new Terminal(config, null);
        control.lastCommand.addListener((observable, oldValue, newValue) -> {
            terminal.command(newValue);
        });
        this.getChildren().add(terminal);
    }
}
