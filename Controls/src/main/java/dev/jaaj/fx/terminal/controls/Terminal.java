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

package dev.jaaj.fx.terminal.controls;

import dev.jaaj.fx.terminal.models.profile.Profile;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroDarkTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class Terminal extends Control {
    private final StringProperty lastCommand = new SimpleStringProperty("");
    private final Profile profile;
    private final BooleanProperty closed = new SimpleBooleanProperty(false);
    private final ObjectProperty<TerminalThemeConfig> defaultTerminalTheme = new SimpleObjectProperty<>(new DefaultJMetroDarkTerminalThemeFactory().build());

    public Terminal(AbstractShellConfig terminalConfig) {
        this(terminalConfig, null);
    }

    public Terminal(AbstractShellConfig terminalConfig, TerminalThemeConfig themeConfig) {
        profile = new Profile(terminalConfig.getTitle(), terminalConfig, themeConfig);
    }

    public Terminal(Profile profile) {
        this.profile = profile;
    }

    public void execute(String command) {
        lastCommand.set(command);
    }

    public String getLastCommand() {
        return lastCommand.get();
    }

    public StringProperty lastCommandProperty() {
        return lastCommand;
    }

    public TerminalThemeConfig getTerminalThemeConfig() {
        if (profile.getTerminalThemeConfig() == null) {
            return getDefaultTerminalTheme();
        }
        return profile.getTerminalThemeConfig();
    }

    public ObjectProperty<TerminalThemeConfig> terminalThemeConfigProperty() {
        return profile.terminalThemeConfigProperty();
    }

    public void setTerminalThemeConfig(TerminalThemeConfig terminalThemeConfig) {
        this.profile.setTerminalThemeConfig(terminalThemeConfig);
    }

    public AbstractShellConfig getTerminalConfig() {
        return profile.getShellConfig();
    }

    public ObjectProperty<AbstractShellConfig> terminalConfigProperty() {
        return profile.shellConfigProperty();
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }

    public boolean isClosed() {
        return closed.get();
    }

    public BooleanProperty closedProperty() {
        return closed;
    }

    public void close() {
        closed.set(true);
    }

    public Profile getProfile() {
        return profile;
    }

    public String getTitle() {
        return profile.getProfileName();
    }

    public TerminalThemeConfig getDefaultTerminalTheme() {
        return defaultTerminalTheme.get();
    }

    public ObjectProperty<TerminalThemeConfig> defaultTerminalThemeProperty() {
        return defaultTerminalTheme;
    }

    public void setDefaultTerminalTheme(TerminalThemeConfig defaultTerminalTheme) {
        this.defaultTerminalTheme.set(defaultTerminalTheme);
    }
}
