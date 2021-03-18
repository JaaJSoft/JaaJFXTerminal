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

package dev.jaaj.fx.terminal.models.shell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public abstract class LocalShellConfig extends AbstractShellConfig {
    private final StringProperty executable = new SimpleStringProperty();
    private final StringProperty workingDirectory = new SimpleStringProperty("");
    private final StringProperty commandToExecute = new SimpleStringProperty("");
    private final StringProperty extraArgs = new SimpleStringProperty("");

    public LocalShellConfig(String executable) {
        this.executable.set(executable);
    }

    public String getExecutable() {
        return executable.get();
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

    public String getExtraArgs() {
        return extraArgs.get();
    }

    public StringProperty extraArgsProperty() {
        return extraArgs;
    }

    public void setExtraArgs(String extraArgs) {
        this.extraArgs.set(extraArgs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalShellConfig that = (LocalShellConfig) o;
        return Objects.equals(getExecutable(), that.getExecutable()) && Objects.equals(getWorkingDirectory(), that.getWorkingDirectory()) && Objects.equals(getCommandToExecute(), that.getCommandToExecute()) && Objects.equals(getExtraArgs(), that.getExtraArgs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExecutable(), getWorkingDirectory(), getCommandToExecute(), getExtraArgs());
    }
}
