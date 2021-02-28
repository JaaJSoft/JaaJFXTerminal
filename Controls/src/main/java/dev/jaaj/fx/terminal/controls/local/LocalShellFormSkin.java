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

package dev.jaaj.fx.terminal.controls.local;

import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.controls.wsl.Distribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ResourceBundle;

public class LocalShellFormSkin extends SkinFXML<LocalShellForm> {
    @FXML
    ComboBox<Distribution> distributionComboBox;
    @FXML
    TextField userField;
    @FXML
    TextField workingDirectoryField;
    @FXML
    TextField commandField;
    @FXML
    Button chooseDirBtn;

    public LocalShellFormSkin(LocalShellForm control) {
        super(control,
                LocalShellFormSkin.class.getResource("WSLForm.fxml"),
                ResourceBundle.getBundle(LocalShellFormSkin.class.getPackageName() + ".LocalShellForm")
        );
        //distributionComboBox.setItems(control.getPossibleDistributions());
        bind(control);
        control.formStateProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READONLY:
                    distributionComboBox.setEditable(false);
                    userField.setEditable(false);
                    workingDirectoryField.setEditable(false);
                    commandField.setEditable(false);
                    chooseDirBtn.setDisable(true);
                    break;
                case EDITABLE:
                    distributionComboBox.setEditable(true);
                    userField.setEditable(true);
                    workingDirectoryField.setEditable(true);
                    commandField.setEditable(true);
                    chooseDirBtn.setDisable(false);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + newValue);
            }

        });
        chooseDirBtn.setOnAction(this::openFolderPicker);
    }

    public void openFolderPicker(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(this.getNode().getScene().getWindow());
        if (file != null) {
            workingDirectoryField.textProperty().set(file.toPath().toAbsolutePath().toString());
        }
    }


    public void unbind() {
        distributionComboBox.valueProperty().unbind();
        userField.textProperty().unbind();
        workingDirectoryField.textProperty().unbind();
        commandField.textProperty().unbind();
    }

    public void bind(LocalShellForm localShellForm) {
     /*   distributionComboBox.valueProperty().bindBidirectional(localShellForm.distributionProperty());
        userField.textProperty().bindBidirectional(localShellForm.userProperty());
        workingDirectoryField.textProperty().bindBidirectional(localShellForm.workingDirectoryProperty());
        commandField.textProperty().bindBidirectional(localShellForm.commandProperty());*/
    }
}
