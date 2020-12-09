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

package dev.jaaj.fx.terminal.app;


import dev.jaaj.fx.terminal.controls.AbstractTerminal;
import dev.jaaj.fx.terminal.controls.LocalTerminal;
import dev.jaaj.fx.terminal.controls.SSHTerminal;
import dev.jaaj.fx.terminal.controls.WSLTerminal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.StatusBar;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public BorderPane root;
    public MenuBar menuBar;
    public TabPane tabPane;
    public StatusBar statusBar;
    public MenuItem newTerminal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //root.setCenter(new SSHTerminal("root", InetAddress.getByName("vps741987.ovh.net")));
        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);
        newTerminal.fire();
    }

    @FXML
    private void openOptions(ActionEvent event) {

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void addTerminal(AbstractTerminal terminal) {
        Tab newTab = new Tab();
        newTab.setContent(terminal);
        newTab.setText(terminal.getTitle());
        tabPane.getTabs().add(newTab);
        tabPane.getSelectionModel().select(newTab);
    }

    public void openTerminal(ActionEvent actionEvent) {
        LocalTerminal terminal = new LocalTerminal();
        addTerminal(terminal);
    }

    public void openTerminalSSH(ActionEvent actionEvent) {
        AbstractTerminal terminal = null;
        terminal = new WSLTerminal();
        addTerminal(terminal);
    }
}
