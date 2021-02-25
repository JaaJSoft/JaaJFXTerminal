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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;

import java.util.Objects;

public class TerminalTab extends Tab {
    private final ObjectProperty<Terminal> terminal = new SimpleObjectProperty<>();

    public TerminalTab(Terminal terminal) {
        super(terminal.getTerminalConfig().getTitle(), terminal);
        this.terminal.set(terminal);
        this.contentProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                if (newValue instanceof Terminal) {
                    this.setTerminal((Terminal) newValue);
                }
            }
        });
        this.terminalProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                this.setContent(newValue);
            }
        });
    }

    public void setTerminal(Terminal terminal) {
        this.terminal.set(terminal);
        this.setContent(terminal);
    }

    public Terminal getTerminal() {
        return terminal.get();
    }

    public ObjectProperty<Terminal> terminalProperty() {
        return terminal;
    }

    @Override
    public String toString() {
        return "TerminalTab{" +
                "terminal=" + terminal.get() +
                '}';
    }
}
