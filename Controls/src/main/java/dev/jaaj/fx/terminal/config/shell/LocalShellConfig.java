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

package dev.jaaj.fx.terminal.config.shell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class LocalShellConfig extends AbstractShellConfig {
    private final StringProperty executable = new SimpleStringProperty();
    private final StringProperty workingDirectory = new SimpleStringProperty();
    private final StringProperty commandToExecute = new SimpleStringProperty();

    public LocalShellConfig(String executable) {
        this.executable.set(executable);
    }

    public String getExecutable() {
        return executable.get();
    }

    public StringProperty executableProperty() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable.set(executable);
    }

    public String getWorkingDirectory() {
        return workingDirectory.get();
    }

    public StringProperty workingDirectoryProperty() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory.set(workingDirectory);
    }

    public String getCommandToExecute() {
        return commandToExecute.get();
    }

    public StringProperty commandToExecuteProperty() {
        return commandToExecute;
    }

    public void setCommandToExecute(String commandToExecute) {
        this.commandToExecute.set(commandToExecute);
    }
}
