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

import dev.jaaj.fx.terminal.models.shell.wsl.Distribution;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WSLConfig extends AbstractShellConfig {
    private final ObjectProperty<Distribution> distribution = new SimpleObjectProperty<>();
    private final StringProperty user = new SimpleStringProperty("");
    private final StringProperty workingDirectory = new SimpleStringProperty("");
    private final StringProperty command = new SimpleStringProperty("");

    public Distribution getDistribution() {
        return distribution.get();
    }

    public ObjectProperty<Distribution> distributionProperty() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution.set(distribution);
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
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

    public String getCommand() {
        return command.get();
    }

    public StringProperty commandProperty() {
        return command;
    }

    public void setCommand(String command) {
        this.command.set(command);
    }

    @Override
    public String getCommandLine() {
        String startCommand = "wsl";
        if (distribution.isNotNull().get() && !getDistribution().getName().isBlank()) {
            startCommand += " -d " + getDistribution();
        }
        if (!getUser().isBlank()) {
            startCommand += " -u " + getUser();
        }

        if (!getWorkingDirectory().isBlank()) {
            startCommand += " --cd " + getWorkingDirectory();
        }

        if (!getCommand().isBlank()) {
            startCommand += " -e " + getCommand();
        }
        return startCommand;
    }

    @Override
    public String getTitle() {
        String distrib = getDistribution().getName();
        String user = getUser();
        if (!user.isBlank()) {
            user += "@";
        }
        if (distrib.isBlank()) {
            distrib = "Default"; //TODO: resource bundle
        }
        return user + distrib;
    }
}
