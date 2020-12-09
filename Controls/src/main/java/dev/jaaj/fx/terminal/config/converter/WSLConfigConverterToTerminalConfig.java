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

package dev.jaaj.fx.terminal.config.converter;

import dev.jaaj.fx.terminal.config.TerminalConfig;
import dev.jaaj.fx.terminal.config.WSLConfig;

public class WSLConfigConverterToTerminalConfig implements IConverterTerminalConfig {
    private final WSLConfig wslConfig;

    public WSLConfigConverterToTerminalConfig(WSLConfig wslConfig) {
        this.wslConfig = wslConfig;
    }

    @Override
    public TerminalConfig convert() {
        TerminalConfig terminalConfig = new TerminalConfig();
        String command = "wsl";
        if (!wslConfig.getDistrib().isBlank()) {
            command += " -d " + wslConfig.getDistrib();
        }
        if (!wslConfig.getUser().isBlank()) {
            command += " -u " + wslConfig.getUser();
        }
        terminalConfig.setStartCommand(command);
        return terminalConfig;
    }
}
