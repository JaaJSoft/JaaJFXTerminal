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

package dev.jaaj.fx.terminal.config.profile;

import dev.jaaj.fx.terminal.config.factory.theme.DefaultJMetroLightTerminalThemeFactory;
import dev.jaaj.fx.terminal.config.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.config.shell.DefaultShellConfig;
import dev.jaaj.fx.terminal.config.shell.LocalShellConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/**
 * a profile is composed of a ShellConfig and and a ThemeConfig
 */
public class Profile {
    private final StringProperty profileName = new SimpleStringProperty();
    private final ObjectProperty<AbstractShellConfig> shellConfig = new SimpleObjectProperty<>();
    private final ObjectProperty<TerminalThemeConfig> terminalThemeConfig = new SimpleObjectProperty<>();

    public Profile(String profileName) {
        this(profileName, new DefaultShellConfig(), new DefaultJMetroLightTerminalThemeFactory().build());
    }

    public Profile(String profileName, AbstractShellConfig shellConfig, TerminalThemeConfig themeConfig) {
        this.profileName.set(profileName);
        this.shellConfig.set(shellConfig);
        this.terminalThemeConfig.set(themeConfig);
    }

    public Profile(AbstractShellConfig shellConfig, TerminalThemeConfig themeConfig) {
        this(shellConfig.getTitle(), shellConfig, themeConfig);
    }

    public Profile(AbstractShellConfig shellConfig) {
        this(shellConfig, new DefaultJMetroLightTerminalThemeFactory().build());
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

    public String getProfileName() {
        return profileName.get();
    }

    public StringProperty profileNameProperty() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName.set(profileName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(getProfileName(), profile.getProfileName()) && Objects.equals(getShellConfig(), profile.getShellConfig()) && Objects.equals(getTerminalThemeConfig(), profile.getTerminalThemeConfig());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProfileName(), getShellConfig(), getTerminalThemeConfig());
    }
}
