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

package dev.jaaj.fx.terminal.models.shell.wsl;

import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;
import java.util.Optional;

public class WSLConfig extends LocalShellConfig {
    private final ObjectProperty<Distribution> distribution = new SimpleObjectProperty<>();
    private final StringProperty user = new SimpleStringProperty("");

    public WSLConfig() {
        super("wsl");
        Distribution.getDefaultDistribution().ifPresent(distribution::set);
        setShellIcon(getClass().getResource("wsl.png").toExternalForm());
    }

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
        if (!getCommandToExecute().isBlank()) {
            startCommand += " -e " + getCommandToExecute();
        }
        if (!getExtraArgs().isBlank()) {
            startCommand += " " + getExtraArgs();
        }
        return startCommand;
    }

    @Override
    public String getTitle() {
        String distrib = getDistribution().getName();
        String u = getUser();
        if (!u.isBlank()) {
            u += "@";
        }
        if (distrib.isBlank()) {
            Optional<Distribution> optionalDistribution = Distribution.getDefaultDistribution();
            if (optionalDistribution.isPresent()) {
                distrib = optionalDistribution.get().getName();
            }
        }
        return u + distrib;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WSLConfig wslConfig = (WSLConfig) o;
        return Objects.equals(getDistribution(), wslConfig.getDistribution()) && Objects.equals(getUser(), wslConfig.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDistribution(), getUser());
    }
}
