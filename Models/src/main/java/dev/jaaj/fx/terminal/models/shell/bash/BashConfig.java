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

package dev.jaaj.fx.terminal.models.shell.bash;

import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;

public class BashConfig extends LocalShellConfig {

    public BashConfig() {
        super("bash");
    }

    @Override
    public String getCommandLine() {
        String commandLine = getExecutable();
        String init = "";
        if (getWorkingDirectory() != null && !getWorkingDirectory().isBlank()) {
            init += "cd " + getWorkingDirectory() + " && ";
        }
        if (getCommandToExecute() != null && !getCommandToExecute().isBlank()) {
            init += " " + getCommandToExecute() + " && ";
        }
        if (!init.isBlank()) {
            commandLine += " -c \"" + init + "exec ${BASH}\"";
        }
        return commandLine + " " + getExtraArgs();
    }

    @Override
    public String getTitle() {
        return "Bash";
    }
}
