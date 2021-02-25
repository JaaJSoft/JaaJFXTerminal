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
import dev.jaaj.fx.terminal.controls.TerminalTab;
import javafx.collections.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TerminalService {
    private final TabPane tabPane;
    private final ObservableSet<TerminalTab> terminalTabs = FXCollections.observableSet();

    public TerminalService(TabPane tabPane) {
        this.tabPane = tabPane;
        tabPane.getTabs().addListener(this::onTabsChanged);
        terminalTabs.addListener(this::onTerminalTabsChanged);
    }

    private void onTabsChanged(ListChangeListener.Change<? extends Tab> c) {
        c.next();
        List<? extends Tab> removed = c.getRemoved();
        removed.stream().filter(TerminalTab.class::isInstance).map(TerminalTab.class::cast).forEach(terminalTabs::remove);
        List<? extends Tab> added = c.getAddedSubList();
        added.stream().filter(TerminalTab.class::isInstance).map(TerminalTab.class::cast).forEach(terminalTabs::add);
    }

    private void onTerminalTabsChanged(SetChangeListener.Change<? extends TerminalTab> c) {
        if (c.wasAdded()) {
            if (!tabPane.getTabs().contains(c.getElementAdded())) {
                tabPane.getTabs().add(c.getElementAdded());
            }
        } else if (c.wasRemoved()) {
            if (tabPane.getTabs().contains(c.getElementRemoved())) {
                tabPane.getTabs().remove(c.getElementRemoved());
            }
        }
    }

    public void addTerminal(Terminal terminal) {
        TerminalTab newTab = new TerminalTab(terminal);
        terminalTabs.add(newTab);
        tabPane.getSelectionModel().select(newTab);
    }

    public boolean removeTerminal(Terminal terminal) {
        return false;
    }

    public boolean removeTerminal(TerminalTab tab) {
        return terminalTabs.remove(tab);
    }

    public Optional<Terminal> getTerminal(Tab tab) {
        if (tab instanceof TerminalTab) {
            return Optional.of(((TerminalTab) tab).getTerminal());
        }
        return Optional.empty();
    }

    public Optional<TerminalTab> getTab(Terminal terminal) {
        return this.terminalTabs.stream().filter(terminalTab1 -> Objects.equals(terminalTab1.getTerminal(), terminal)).findAny();
    }

    public ObservableSet<TerminalTab> getTerminalTabs() {
        return terminalTabs;
    }

    public Optional<Terminal> getFocusedTerminal() {
        return getTerminal(tabPane.getSelectionModel().getSelectedItem());
    }
}
