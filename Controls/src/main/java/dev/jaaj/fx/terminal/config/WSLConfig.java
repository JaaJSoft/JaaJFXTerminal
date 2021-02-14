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

package dev.jaaj.fx.terminal.config;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WSLConfig  extends AbstractShellConfig {
    private final StringProperty distribution = new SimpleStringProperty("");
    private final StringProperty user = new SimpleStringProperty("");

    public String getDistribution() {
        return distribution.get();
    }

    public StringProperty distributionProperty() {
        return distribution;
    }

    public void setDistribution(String distribution) {
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
    public String getStartCommand() {
        String command = "wsl";
        if (!getDistribution().isBlank()) {
            command += " -d " + distribution;
        }
        if (!getUser().isBlank()) {
            command += " -u " + user;
        }
        return command;
    }
}
