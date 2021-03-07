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


import dev.jaaj.fx.terminal.controls.Terminal;
import dev.jaaj.fx.terminal.controls.about.AboutDialog;
import dev.jaaj.fx.terminal.controls.about.data.AppInfo;
import dev.jaaj.fx.terminal.controls.about.data.AppInfoBuilder;
import dev.jaaj.fx.terminal.controls.about.data.Person;
import dev.jaaj.fx.terminal.controls.about.data.PersonBuilder;
import dev.jaaj.fx.terminal.controls.local.LocalShellFormDialog;
import dev.jaaj.fx.terminal.controls.options.OptionsDialog;
import dev.jaaj.fx.terminal.controls.ssh.SSHFormDialog;
import dev.jaaj.fx.terminal.controls.wsl.WSLFormDialog;
import dev.jaaj.fx.terminal.models.profile.Profile;
import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;
import dev.jaaj.fx.terminal.models.shell.cmd.CmdShellConfig;
import dev.jaaj.fx.terminal.models.shell.powershell.PowerShellConfig;
import dev.jaaj.fx.terminal.models.shell.powershell.PwshConfig;
import dev.jaaj.fx.terminal.models.shell.ssh.SSHConfig;
import dev.jaaj.fx.terminal.models.shell.wsl.WSLConfig;
import dev.jaaj.fx.terminal.services.profile.ProfilesService;
import dev.jaaj.fx.terminal.services.terminal.TerminalService;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.StatusBar;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //TODO dynamic
    public static final Person PIERRE_CHOPINET = new PersonBuilder().setName("Pierre Chopinet").setTitle("Main Developer").setEmail("pierre.chopinet@jaaj.dev").setWebsite("https://jaaj.dev").createPerson();
    public static final Person JAAJSOFT = new PersonBuilder().setName("JaaJSoft").setEmail("contact@jaaj.dev").setWebsite("https://jaaj.dev").createPerson();
    public static final Person REMI_LECOUILLARD = new PersonBuilder().setName("Rémi Lecouillard").setTitle("A good friend").setEmail("remi.lecouillard@jaaj.dev").setWebsite("https://jaaj.dev").createPerson();
    public static final Person JULIEN_CHEVRON = new PersonBuilder().setName("Julien Chevron").setTitle("Noobard").setEmail("julien.chevron@jaaj.dev").setWebsite("https://jaaj.dev").createPerson();


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
    @FXML
    MenuItem newCMDShellTerminal;
    @FXML
    MenuItem newPowerShellTerminal;
    @FXML
    MenuItem newPWSHTerminal;

    private ResourceBundle bundle;

    private final ProfilesService profilesService = new ProfilesService();
    private TerminalService terminalService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        terminalService = new TerminalService(tabPane);
        profilesService.getProfiles().addListener((ListChangeListener<? super Profile>) c -> {
            c.next();
            ObservableList<MenuItem> profileMenuItems = profileMenu.getItems();
            if (c.wasAdded()) {
                Profile profile = c.getAddedSubList().get(0);
                MenuItem e = new MenuItem(profile.getProfileName());
                e.setOnAction(event -> {
                    terminalService.addTerminal(new Terminal(profile));
                });
                profileMenuItems.add(e);
            } else if (c.wasRemoved()) {
                Optional<MenuItem> optionalMenuItem = profileMenuItems.stream().filter(menuItem -> menuItem.getText().equals(c.getRemoved().get(0).getProfileName())).findAny();
                optionalMenuItem.ifPresent(profileMenuItems::remove);
            }
        });
        newCMDShellTerminal.setOnAction(event -> openTerminalLocalShell(new CmdShellConfig()));
        newPowerShellTerminal.setOnAction(event -> openTerminalLocalShell(new PowerShellConfig()));
        newPWSHTerminal.setOnAction(event -> openTerminalLocalShell(new PwshConfig()));
        newTerminal.fire();
    }


    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void openTerminal(ActionEvent actionEvent) {
        Terminal terminal = new Terminal(profilesService.getDefaultProfile());
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

    public void openTerminalLocalShell(LocalShellConfig shellConfig) {
        LocalShellFormDialog shellFormDialog = new LocalShellFormDialog(shellConfig);
        shellFormDialog.initOwner(root.getCenter().getScene().getWindow());
        Optional<LocalShellConfig> optionalLocalShellConfig = shellFormDialog.showAndWait();
        optionalLocalShellConfig.ifPresent(localShellConfig -> {
            Terminal terminal = new Terminal(shellFormDialog.getResult());
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
                .setDevelopers(List.of(
                        PIERRE_CHOPINET
                )).setTranslators(List.of(
                        new PersonBuilder().setName("Pierre Chopinet").setTitle("French Translator").setEmail("pierre.chopinet@jaaj.dev").setWebsite("https://jaaj.dev").createPerson()
                )).setThanks(List.of(
                        JAAJSOFT, REMI_LECOUILLARD
                ))
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
                .setDevelopers(List.of(
                        PIERRE_CHOPINET, JULIEN_CHEVRON
                )).setThanks(List.of(
                        JAAJSOFT, REMI_LECOUILLARD
                ))
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
            profilesService.saveProfile(terminal.getProfile());
        });

    }

    public void manageProfile(ActionEvent actionEvent) {

    }


}
