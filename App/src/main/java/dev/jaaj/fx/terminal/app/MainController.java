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


import dev.jaaj.fx.terminal.config.profile.Profile;
import dev.jaaj.fx.terminal.config.profile.ProfilesService;
import dev.jaaj.fx.terminal.config.shell.LocalShellConfig;
import dev.jaaj.fx.terminal.config.shell.SSHConfig;
import dev.jaaj.fx.terminal.config.shell.WSLConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalService;
import dev.jaaj.fx.terminal.controls.Terminal;
import dev.jaaj.fx.terminal.controls.about.AboutDialog;
import dev.jaaj.fx.terminal.controls.about.data.AppInfo;
import dev.jaaj.fx.terminal.controls.about.data.AppInfoBuilder;
import dev.jaaj.fx.terminal.controls.form.ssh.SSHFormDialog;
import dev.jaaj.fx.terminal.controls.form.wsl.WSLFormDialog;
import dev.jaaj.fx.terminal.controls.options.OptionsDialog;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.StatusBar;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Menu profileMenu;
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
    private ResourceBundle bundle;

    private ProfilesService profilesService = new ProfilesService();
    private TerminalService terminalService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        terminalService = new TerminalService(tabPane);
        newTerminal.fire();
        profilesService.getProfiles().addListener((ListChangeListener<? super Profile>) c -> {
            c.next();
            ObservableList<MenuItem> profileMenuItems = profileMenu.getItems();
            if (c.wasAdded()) {
                Profile profile = c.getAddedSubList().get(0);
                MenuItem e = new MenuItem(profile.getProfileName());
                e.setOnAction(event -> {
                    terminalService.addTerminal(new Terminal(profile.getShellConfig(), profile.getTerminalThemeConfig()));
                });
                profileMenuItems.add(e);
            } else if (c.wasRemoved()) {
                Optional<MenuItem> optionalMenuItem = profileMenuItems.stream().filter(menuItem -> menuItem.getText().equals(c.getRemoved().get(0).getProfileName())).findAny();
                optionalMenuItem.ifPresent(profileMenuItems::remove);
            }
        });
    }


    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void openTerminal(ActionEvent actionEvent) {
        Terminal terminal = new Terminal(new LocalShellConfig());
        terminalService.addTerminal(terminal);
    }

    public void openTerminalSSH(ActionEvent actionEvent) {
        SSHFormDialog sshTerminalDialog = new SSHFormDialog();
        sshTerminalDialog.initOwner(root.getCenter().getScene().getWindow());
        Optional<SSHConfig> optional = sshTerminalDialog.showAndWait();
        optional.ifPresent(sshConfig -> {
            Terminal terminal = new Terminal(sshTerminalDialog.getResult());
            terminalService.addTerminal(terminal);
        });
    }

    public void openTerminalWSL(ActionEvent actionEvent) {
        WSLFormDialog WSLFormDialog = new WSLFormDialog();
        WSLFormDialog.initOwner(root.getCenter().getScene().getWindow());
        Optional<WSLConfig> optional = WSLFormDialog.showAndWait();
        optional.ifPresent(sshConfig -> {
            Terminal terminal = new Terminal(WSLFormDialog.getResult());
            terminalService.addTerminal(terminal);
        });
    }

    public void openAbout(ActionEvent actionEvent) {
        InputStream inputStream = getClass().getResourceAsStream("img/icon.png");
        Image image = new Image(inputStream);
        AppInfo JaaJFXTerminalApp = new AppInfoBuilder()
                .setAppName("JaaJFXTerminal")
                .setIcon(image)
                .setVersion("0.1")// todo get app version from gradle
                .setAboutText(bundle.getString("ABOUT_TEXT_JAAJFXTERM"))
                .createAppInfo();
        AboutDialog aboutDialog = new AboutDialog(JaaJFXTerminalApp);
        aboutDialog.initOwner(root.getCenter().getScene().getWindow());
        aboutDialog.show();
    }

    public void openAboutJaaJFX(ActionEvent actionEvent) {
        AppInfo JaaJFX = new AppInfoBuilder()
                .setAppName("JaaJFX")
                .setVersion("0.2.1")// todo get app version from gradle
                .setAboutText(bundle.getString("ABOUT_TEXT_JAAJFX"))
                .createAppInfo();
        AboutDialog aboutDialog = new AboutDialog(JaaJFX);
        aboutDialog.initOwner(root.getCenter().getScene().getWindow());
        aboutDialog.show();
    }

    public void openSettings(ActionEvent event) {
        OptionsDialog optionsDialog = new OptionsDialog();
        optionsDialog.initOwner(root.getCenter().getScene().getWindow());
        optionsDialog.show();
    }

    public void saveProfile(ActionEvent event) {
        terminalService.getFocusedTerminal().ifPresent(terminal -> {
            Profile profile = new Profile(terminal.getTerminalConfig().getTitle());
            profile.setShellConfig(terminal.getTerminalConfig());
            profile.setTerminalThemeConfig(terminal.getTerminalThemeConfig());
            profilesService.saveProfile(profile);
        });

    }
}
