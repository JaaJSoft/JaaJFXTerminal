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

package dev.jaaj.fx.terminal.controls;

import dev.jaaj.fx.terminal.config.TerminalConfig;
import dev.jaaj.fx.terminal.config.factory.DefaultJMetroDarkTerminalThemeFactory;
import javafx.beans.property.StringProperty;

import java.net.InetAddress;

public class SSHTerminal extends AbstractTerminal {
    private final InetAddress address;
    private final String user;
    private int port = 22;

    public SSHTerminal(String user, InetAddress address) {
        super(new TerminalConfig("ssh " + user + "@" + address.getHostName() + " -p " + 22));
        this.address = address;
        this.user = user;
    }

    public SSHTerminal(String user, InetAddress address, int port) {
        this(user, address);
        this.port = port;
    }

    @Override
    public String getTitle() {
        return user + "@" + address.getHostName();
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    public int getPort() {
        return port;
    }
}
