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

package dev.jaaj.fx.terminal.models.shell.cmd;

import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;

public class CmdShellConfig extends LocalShellConfig {

    public CmdShellConfig() {
        super("cmd");
        setShellIcon(getClass().getResource("cmd.png").toExternalForm());
    }

    @Override
    public String getCommandLine() {
        String commandLine = this.getExecutable();
        if (getWorkingDirectory() != null && !getWorkingDirectory().isBlank()) {
            commandLine += " /k cd /d " + getWorkingDirectory();
            if (getCommandToExecute() != null && !getCommandToExecute().isBlank()) {
                commandLine += " & " + getCommandToExecute();
            }
        } else {
            if (getCommandToExecute() != null && !getCommandToExecute().isBlank()) {
                commandLine += " /k " + getCommandToExecute();
            }
        }
        return commandLine + " " + getExtraArgs();
    }

    @Override
    public String getTitle() {
        return "CMD";
    }
}
