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

package dev.jaaj.fx.terminal.models.shell.ssh;

import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import javafx.beans.property.*;

import java.net.InetAddress;
import java.util.Objects;

public class SSHConfig extends AbstractShellConfig {

    private final ObjectProperty<InetAddress> inetAddress = new SimpleObjectProperty<>();
    private final StringProperty inetAddressStr = new SimpleStringProperty();
    private final StringProperty user = new SimpleStringProperty("root");
    private final IntegerProperty port = new SimpleIntegerProperty(22);
    private final StringProperty command = new SimpleStringProperty("");

    public SSHConfig() {
        inetAddress.addListener((observable, oldValue, newValue) -> inetAddressStr.set(newValue.getHostAddress()));
        setShellIcon(getClass().getResource("ssh.png").toExternalForm());
    }

    public InetAddress getInetAddress() {
        return inetAddress.getValue();
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress.setValue(inetAddress);
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

    public ObjectProperty<InetAddress> inetAddressProperty() {
        return inetAddress;
    }

    public String getInetAddressStr() {
        return inetAddressStr.get();
    }

    public ReadOnlyStringProperty inetAddressStrProperty() {
        return inetAddressStr;
    }

    @Override
    public String getCommandLine() {
        String startCommand = "ssh "
                + user.getValue()
                + "@"
                + inetAddressStr.getValue()
                + " -p "
                + port.getValue();
        if (!command.getValue().isBlank()) {
            startCommand += " -t " + command.getValue();
        }
        return startCommand;
    }

    @Override
    public String getTitle() {
        return getUser() + "@" + getInetAddressStr();
    }

    @Override
    public String toString() {
        return "SSHConfig{" +
                "address=" + inetAddress.getValue().getHostName() +
                ", user=" + user.getValue() +
                ", port=" + port.getValue() +
                ", command=" + command.getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SSHConfig sshConfig = (SSHConfig) o;
        return Objects.equals(getInetAddress(), sshConfig.getInetAddress()) && Objects.equals(getUser(), sshConfig.getUser()) && Objects.equals(getPort(), sshConfig.getPort()) && Objects.equals(getCommand(), sshConfig.getCommand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInetAddress(), getUser(), getPort(), getCommand());
    }
}
