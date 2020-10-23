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

import javafx.scene.control.Skin;

import java.net.InetAddress;

public class SSHTerminal extends AbstractTerminal {
    private final InetAddress address;
    private final String user;

    public SSHTerminal(String user, InetAddress address) {
        super("ssh " + user + "@" + address.getHostName());
        this.address = address;
        this.user = user;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }
}
