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

import dev.jaaj.fx.control.JaaJControl;
import dev.jaaj.fx.terminal.config.AbstractTerminalConfig;
import dev.jaaj.fx.terminal.config.TerminalThemeConfig;
import dev.jaaj.fx.terminal.config.factory.theme.DefaultJMetroDarkTerminalThemeFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public abstract class AbstractTerminal extends JaaJControl {
    protected final StringProperty lastCommand = new SimpleStringProperty("");
    private final ObjectProperty<AbstractTerminalConfig> terminalConfig;
    private final ObjectProperty<TerminalThemeConfig> terminalThemeConfig;

    public AbstractTerminal(AbstractTerminalConfig terminalConfig) {
        this.terminalConfig = new SimpleObjectProperty<>(terminalConfig);
        this.terminalThemeConfig = new SimpleObjectProperty<>(new DefaultJMetroDarkTerminalThemeFactory().build());
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

    public AbstractTerminalConfig getTerminalConfig() {
        return terminalConfig.get();
    }

    public ObjectProperty<AbstractTerminalConfig> terminalConfigProperty() {
        return terminalConfig;
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }

    public abstract String getTitle();


}
