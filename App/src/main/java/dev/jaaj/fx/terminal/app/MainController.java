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

package dev.jaaj.fx.terminal.app;


import dev.jaaj.fx.terminal.config.shell.LocalShellConfig;
import dev.jaaj.fx.terminal.config.shell.SSHConfig;
import dev.jaaj.fx.terminal.config.shell.WSLConfig;
import dev.jaaj.fx.terminal.controls.Terminal;
import dev.jaaj.fx.terminal.controls.about.AboutDialog;
import dev.jaaj.fx.terminal.controls.form.ssh.SSHFormDialog;
import dev.jaaj.fx.terminal.controls.form.wsl.WSLFormDialog;
import dev.jaaj.fx.terminal.controls.options.OptionsDialog;
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

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    BorderPane root;
    @FXML
    MenuBar menuBar;
    @FXML
    TabPane tabPane;
    @FXML
    StatusBar statusBar;
    @FXML
    MenuItem newTerminal;
    @FXML
    MenuItem newSSHTerminal;
    @FXML
    MenuItem newWSLTerminal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //root.setCenter(new SSHTerminal("root", InetAddress.getByName("vps741987.ovh.net")));
        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);
        newTerminal.fire();
    }


    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void addTerminal(Terminal terminal) {
        Tab newTab = new Tab();
        newTab.setContent(terminal);
        newTab.setText(terminal.getTerminalConfig().getTitle());
        tabPane.getTabs().add(newTab);
        tabPane.getSelectionModel().select(newTab);
    }

    public void openTerminal(ActionEvent actionEvent) {
        Terminal terminal = new Terminal(new LocalShellConfig());
        addTerminal(terminal);
    }

    public void openTerminalSSH(ActionEvent actionEvent) {
        SSHFormDialog sshTerminalDialog = new SSHFormDialog();
        sshTerminalDialog.initOwner(root.getCenter().getScene().getWindow());
        Optional<SSHConfig> optional = sshTerminalDialog.showAndWait();
        optional.ifPresent(sshConfig -> {
            Terminal terminal = new Terminal(sshTerminalDialog.getResult());
            addTerminal(terminal);
        });
    }

    public void openTerminalWSL(ActionEvent actionEvent) {
        WSLFormDialog WSLFormDialog = new WSLFormDialog();
        WSLFormDialog.initOwner(root.getCenter().getScene().getWindow());
        Optional<WSLConfig> optional = WSLFormDialog.showAndWait();
        optional.ifPresent(sshConfig -> {
            Terminal terminal = new Terminal(WSLFormDialog.getResult());
            addTerminal(terminal);
        });
    }

    public void openAbout(ActionEvent actionEvent) {
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.initOwner(root.getCenter().getScene().getWindow());
        aboutDialog.show();
    }

    public void openAboutJaaJFX(ActionEvent actionEvent) {
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.initOwner(root.getCenter().getScene().getWindow());
        aboutDialog.show();
    }

    public void openSettings(ActionEvent event) {
        OptionsDialog optionsDialog = new OptionsDialog();
        optionsDialog.initOwner(root.getCenter().getScene().getWindow());
        optionsDialog.show();
    }


}
