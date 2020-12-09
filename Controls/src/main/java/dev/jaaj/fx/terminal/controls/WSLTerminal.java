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

import dev.jaaj.fx.terminal.config.WSLConfig;
import dev.jaaj.fx.terminal.config.converter.WSLConfigConverterToTerminalConfig;

public class WSLTerminal extends AbstractTerminal {

    private final WSLConfig wslConfig;

    public WSLTerminal(WSLConfig wslConfig) {
        super(new WSLConfigConverterToTerminalConfig(wslConfig).convert());
        this.wslConfig = wslConfig;
    }

    public WSLTerminal() {
        super(new WSLConfigConverterToTerminalConfig(new WSLConfig()).convert());
        wslConfig = new WSLConfig();
    }

    @Override
    public String getTitle() {
        return getUser() + " " + getDistrib();
    }


    public String getDistrib() {
        return wslConfig.getDistrib();
    }

    public String getUser() {
        return wslConfig.getUser();
    }
}
