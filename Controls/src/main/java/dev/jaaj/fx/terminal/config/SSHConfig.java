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

import javafx.beans.property.*;

import java.net.InetAddress;

public class SSHConfig extends AbstractTerminalConfig {
    private final ObjectProperty<InetAddress> address = new SimpleObjectProperty<>();
    private final StringProperty user = new SimpleStringProperty();
    private final IntegerProperty port = new SimpleIntegerProperty(22);
    private final StringProperty command = new SimpleStringProperty("");

    public InetAddress getAddress() {
        return address.getValue();
    }

    public void setAddress(InetAddress address) {
        this.address.setValue(address);
    }

    public String getUser() {
        return user.getValue();
    }

    public void setUser(String user) {
        this.user.setValue(user);
    }

    public int getPort() {
        return port.getValue();
    }

    public void setPort(int port) {
        this.port.setValue(port);
    }

    public String getCommand() {
        return command.getValue();
    }

    public void setCommand(String command) {
        this.command.setValue(command);
    }

    public StringProperty userProperty() {
        return user;
    }

    public IntegerProperty portProperty() {
        return port;
    }

    public StringProperty commandProperty() {
        return command;
    }

    public ObjectProperty<InetAddress> addressProperty() {
        return address;
    }

    @Override
    public String getStartCommand() {
        String startCommand = "ssh "
                + user
                + "@"
                + address.getValue().getHostName()
                + " -p "
                + port;
        if (!command.getValue().isBlank()) {
            startCommand += " -t " + command;
        }
        return startCommand;
    }
}
