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

import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;

import java.util.ResourceBundle;

public class ProfileFormSkin extends SkinFXML<ProfileForm> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(ProfileForm.class.getPackageName() + ".Profile");

    @FXML
    ComboBox<TerminalThemeConfig> builtInThemes;
    @FXML
    ScrollPane shellFormBox;
    @FXML
    TabPane tabPane;

    public ProfileFormSkin(ProfileForm control) {
        super(control, ProfileFormSkin.class.getResource("Profile.fxml"), BUNDLE);
        builtInThemes.itemsProperty().set(control.getTerminalThemeConfigs());
        builtInThemes.prefWidthProperty().bind(control.widthProperty());
        control.terminalThemeSelectionModelProperty().bind(builtInThemes.selectionModelProperty());

        control.shellFormProperty().addListener((observable, oldValue, newValue) -> shellFormBox.setContent(newValue));
        if (control.shellFormProperty().isNotNull().get()) {
            shellFormBox.setContent(control.getShellForm());
        }
    }
}
