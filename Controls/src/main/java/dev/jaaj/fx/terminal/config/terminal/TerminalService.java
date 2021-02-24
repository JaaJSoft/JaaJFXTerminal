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

package dev.jaaj.fx.terminal.config.terminal;

import dev.jaaj.fx.terminal.controls.Terminal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TabPane;

public class TerminalService {
    private final ObservableList<Terminal> terminals = FXCollections.emptyObservableList();
    private final TabPane tabPane;
    private final ObjectProperty<SelectionModel<Terminal>> selectionModel = new SimpleObjectProperty<>();
    public TerminalService(TabPane tabPane) {
        this.tabPane = tabPane;

    }
}
