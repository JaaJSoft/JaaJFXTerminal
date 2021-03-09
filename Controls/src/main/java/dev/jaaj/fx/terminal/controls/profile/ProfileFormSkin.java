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

package dev.jaaj.fx.terminal.controls.profile;

import dev.jaaj.fx.core.form.AbstractForm;
import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.controls.local.LocalShellFormFactory;
import dev.jaaj.fx.terminal.controls.ssh.SSHFormFactory;
import dev.jaaj.fx.terminal.controls.util.FormFactoryVisitor;
import dev.jaaj.fx.terminal.controls.wsl.WSLFormFactory;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;

import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileFormSkin extends SkinFXML<ProfileForm> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(ProfileForm.class.getPackageName() + ".Profile");

    @FXML
    ChoiceBox<TerminalThemeConfig> builtInThemes;
    @FXML
    ScrollPane shellFormBox;
    @FXML
    TabPane tabPane;

    public ProfileFormSkin(ProfileForm control) {
        super(control, ProfileFormSkin.class.getResource("Profile.fxml"), BUNDLE);
        builtInThemes.itemsProperty().set(control.getTerminalThemeConfigs());
        builtInThemes.prefWidthProperty().bind(control.widthProperty());
        control.terminalThemeSelectionModelProperty().bind(builtInThemes.selectionModelProperty());
        FormFactoryVisitor formFactoryVisitor = new FormFactoryVisitor()//
                .register(new LocalShellFormFactory())
                .register(new SSHFormFactory())
                .register(new WSLFormFactory());
        AbstractShellConfig shellConfig = control.getProfile().getShellConfig();
        Optional<AbstractForm<?>> visit = formFactoryVisitor.visit(shellConfig);
        visit.ifPresent(abstractForm -> {
            shellFormBox.setContent(abstractForm);
        });
    }


}
