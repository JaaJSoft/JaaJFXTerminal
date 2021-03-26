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

package dev.jaaj.fx.terminal.controls.theme;

import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroDarkTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import org.controlsfx.dialog.FontSelectorDialog;

import java.util.ResourceBundle;

public class TerminalThemeFormSkin extends SkinFXML<TerminalThemeForm> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(TerminalThemeFormSkin.class.getPackageName() + ".TerminalTheme");
    @FXML
    ColorPicker textColorPicker;
    @FXML
    VBox textColorPickerBox;
    @FXML
    ColorPicker backgroundColorPicker;
    @FXML
    VBox backgroundColorPickerBox;
    @FXML
    ColorPicker cursorColorPicker;
    @FXML
    VBox cursorColorPickerBox;
    @FXML
    Button fontPickerBtn;

    public TerminalThemeFormSkin(TerminalThemeForm control) {
        super(control, TerminalThemeFormSkin.class.getResource("TerminalTheme.fxml"), BUNDLE);
        textColorPicker.prefWidthProperty().bind(textColorPickerBox.widthProperty());
        backgroundColorPicker.prefWidthProperty().bind(backgroundColorPickerBox.widthProperty());
        cursorColorPicker.prefWidthProperty().bind(cursorColorPickerBox.widthProperty());
        fontPickerBtn.setOnAction(this::fontPickerAction);
    }

    private void fontPickerAction(ActionEvent event) {
        //TerminalThemeConfig terminalThemeConfig = getSkinnable().getItem();
        TerminalThemeConfig terminalThemeConfig = new DefaultJMetroDarkTerminalThemeFactory().build();
        FontSelectorDialog fontSelectorDialog = new FontSelectorDialog(terminalThemeConfig.getFont());
        fontSelectorDialog.initOwner(getNode().getScene().getWindow());
        fontSelectorDialog.showAndWait();
    }
}
