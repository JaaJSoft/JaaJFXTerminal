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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public abstract class AbstractTerminal extends JaaJControl {
    protected final StringProperty lastCommand = new SimpleStringProperty("");
    private final String startCommand;

    public AbstractTerminal(String startCommand) {
        this.startCommand = startCommand;
    }

    public void execute(String command) {
        lastCommand.set(command);
    }

    public String getStartCommand() {
        return startCommand;
    }

    public String getLastCommand() {
        return lastCommand.get();
    }

    public StringProperty lastCommandProperty() {
        return lastCommand;
    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }
}
