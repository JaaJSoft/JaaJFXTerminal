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

package dev.jaaj.fx.terminal.config;

import dev.jaaj.fx.terminal.config.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * a profile is composed of a ShellConfig and and a ThemeConfig
 */
public class Profile {
    private final ObjectProperty<AbstractShellConfig> shellConfig = new SimpleObjectProperty<>();
    private final ObjectProperty<TerminalThemeConfig> terminalThemeConfig = new SimpleObjectProperty<>();

    public Profile(AbstractShellConfig shellConfig, TerminalThemeConfig themeConfig) {
        this.shellConfig.set(shellConfig);
        this.terminalThemeConfig.set(themeConfig);
    }

    public AbstractShellConfig getShellConfig() {
        return shellConfig.get();
    }

    public ObjectProperty<AbstractShellConfig> shellConfigProperty() {
        return shellConfig;
    }

    public void setShellConfig(AbstractShellConfig shellConfig) {
        this.shellConfig.set(shellConfig);
    }

    public TerminalThemeConfig getTerminalThemeConfig() {
        return terminalThemeConfig.get();
    }

    public ObjectProperty<TerminalThemeConfig> terminalThemeConfigProperty() {
        return terminalThemeConfig;
    }

    public void setTerminalThemeConfig(TerminalThemeConfig terminalThemeConfig) {
        this.terminalThemeConfig.set(terminalThemeConfig);
    }
}
