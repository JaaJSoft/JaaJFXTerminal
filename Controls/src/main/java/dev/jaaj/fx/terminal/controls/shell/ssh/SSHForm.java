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

package dev.jaaj.fx.terminal.controls.shell.ssh;

import dev.jaaj.fx.terminal.controls.shell.ShellForm;
import dev.jaaj.fx.terminal.models.shell.ssh.SSHConfig;
import javafx.beans.property.*;
import javafx.scene.control.Skin;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SSHForm extends ShellForm<SSHConfig> {

    private final StringProperty inetAddressStr = new SimpleStringProperty();
    private final StringProperty user = new SimpleStringProperty("root");
    private final IntegerProperty port = new SimpleIntegerProperty(22);
    private final StringProperty command = new SimpleStringProperty("");

    public SSHForm() {
        this(new SSHConfig());
    }

    public SSHForm(SSHConfig config) {
        this.itemProperty().addListener((observable, oldValue, newValue) -> {
            inetAddressStr.set(newValue.getInetAddressStr());
            user.set(newValue.getUser());
            port.set(newValue.getPort());
            command.set(newValue.getCommand());
        });
        this.itemProperty().set(config);
    }

    @Override
    public boolean validate() {// TODO find a way to show error
        if (user.isNull().get() || user.getValue().isBlank()) {
            return false;
        }
        if (port.getValue() < 0 || port.getValue() > 65535) {
            return false;
        }
        if (inetAddressStr.isNull().get() || inetAddressStr.getValue().isBlank()) {
            return false;
        }
        try {
            InetAddress ignored = InetAddress.getByName(inetAddressStr.getValue());
        } catch (UnknownHostException e) { //todo print error
            return false;
        }
        return true;
    }

    @Override
    public SSHConfig apply() {
        SSHConfig config = itemProperty().getValue();
        if (config != null) {
            try {
                config.setInetAddress(InetAddress.getByName(inetAddressStr.get()));
            } catch (UnknownHostException ignored) {
            }
            config.setUser(user.get());
            config.setPort(port.getValue());
            config.setCommand(command.getValue());
        }
        return config;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SSHFormSkin(this);
    }

    public SSHConfig getSshConfig() {
        return itemProperty().get();
    }

    public ObjectProperty<SSHConfig> sshConfigProperty() {
        return itemProperty();
    }

    public void setSshConfig(SSHConfig sshConfig) {
        this.itemProperty().set(sshConfig);
    }

    public StringProperty inetAddressStrProperty() {
        return inetAddressStr;
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

    public String getInetAddressStr() {
        return inetAddressStr.get();
    }

    public String getUser() {
        return user.get();
    }

    public int getPort() {
        return port.get();
    }

    public String getCommand() {
        return command.get();
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setPort(int port) {
        this.port.set(port);
    }

    public void setCommand(String command) {
        this.command.set(command);
    }
}
