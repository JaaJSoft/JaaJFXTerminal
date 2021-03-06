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

package dev.jaaj.fx.terminal.models.shell.powershell;

import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;

public class PowerShellConfig extends LocalShellConfig {

    public PowerShellConfig() {
        super("powershell");
        setShellIcon(getClass().getResource("powershell_96px.png").toExternalForm());
    }

    @Override
    public String getCommandLine() {
        return PowerShellUtil.getCommandLine(this);
    }

    @Override
    public String getTitle() {
        return "Powershell";
    }
}
