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

import dev.jaaj.fx.terminal.config.profile.Profile;
import dev.jaaj.fx.terminal.config.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import dev.jaaj.fx.terminal.config.factory.theme.DefaultJMetroLightTerminalThemeFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class Terminal extends Control {
    protected final StringProperty lastCommand = new SimpleStringProperty("");
    private final ObjectProperty<AbstractShellConfig> terminalConfig;
    private final ObjectProperty<TerminalThemeConfig> terminalThemeConfig;

    public Terminal(AbstractShellConfig terminalConfig) {
        this(terminalConfig, new DefaultJMetroLightTerminalThemeFactory().build());// todo auto-theme
    }

    public Terminal(AbstractShellConfig terminalConfig, TerminalThemeConfig themeConfig) {
        this.terminalConfig = new SimpleObjectProperty<>(terminalConfig);
        this.terminalThemeConfig = new SimpleObjectProperty<>(themeConfig);
    }

    public Terminal(Profile profile) {
        this(profile.getShellConfig(), profile.getTerminalThemeConfig());
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
        return terminalThemeConfig.get();
    }

    public ObjectProperty<TerminalThemeConfig> terminalThemeConfigProperty() {
        return terminalThemeConfig;
    }

    public void setTerminalThemeConfig(TerminalThemeConfig terminalThemeConfig) {
        this.terminalThemeConfig.set(terminalThemeConfig);
    }

    public AbstractShellConfig getTerminalConfig() {
        return terminalConfig.get();
    }

    public ObjectProperty<AbstractShellConfig> terminalConfigProperty() {
        return terminalConfig;
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }

}
