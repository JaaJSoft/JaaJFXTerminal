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

import dev.jaaj.fx.terminal.config.SSHConfig;
import dev.jaaj.fx.terminal.config.converter.SSHConfigConverterToTerminalConfig;

import java.net.InetAddress;

public class SSHTerminal extends AbstractTerminal {
    private final SSHConfig sshConfig;

    public SSHTerminal(SSHConfig sshConfig) {
        super(new SSHConfigConverterToTerminalConfig(sshConfig).convert());
        this.sshConfig = sshConfig;
    }

    @Override
    public String getTitle() {
        return getUser() + "@" + getAddress().getHostName();
    }

    public InetAddress getAddress() {
        return sshConfig.getAddress();
    }

    public String getUser() {
        return sshConfig.getUser();
    }

    public int getPort() {
        return sshConfig.getPort();
    }

    public SSHConfig getSshConfig() {
        return sshConfig;
    }
}
