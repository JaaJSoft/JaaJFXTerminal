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

package dev.jaaj.fx.terminal.controls.shell.wsl;

import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.models.shell.wsl.Distribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ResourceBundle;

public class WSLFormSkin extends SkinFXML<WSLForm> {
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
    @FXML
    TitledPane advancedPane;

    public WSLFormSkin(WSLForm control) {
        super(control,
                WSLFormSkin.class.getResource("WSLForm.fxml"),
                ResourceBundle.getBundle(WSLFormSkin.class.getPackageName() + ".WSLForm")
        );
        distributionComboBox.setItems(control.getPossibleDistributions());
        distributionComboBox.prefWidthProperty().bind(control.widthProperty());

        bind(control);
        selectDefaultDistribution(control);
        control.formStateProperty().addListener((observable, oldValue, newValue) -> {

        });
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
        advancedPane.setOnMouseClicked(event -> advancedPane.getScene().getWindow().sizeToScene());
    }

    public void openFolderPicker(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(this.getNode().getScene().getWindow());
        if (file != null) {
            workingDirectoryField.textProperty().set(file.toPath().toAbsolutePath().toString());
        }
    }

    private void selectDefaultDistribution(WSLForm control) {
        control.getPossibleDistributions()
                .stream()
                .filter(Distribution::isDefault)
                .findAny()
                .ifPresent(value -> distributionComboBox.getSelectionModel().select(value));
    }

    public void unbind() {
        distributionComboBox.valueProperty().unbind();
        userField.textProperty().unbind();
        workingDirectoryField.textProperty().unbind();
        commandField.textProperty().unbind();
    }

    public void bind(WSLForm wslForm) {
        distributionComboBox.valueProperty().bindBidirectional(wslForm.distributionProperty());
        userField.textProperty().bindBidirectional(wslForm.userProperty());
        workingDirectoryField.textProperty().bindBidirectional(wslForm.workingDirectoryProperty());
        commandField.textProperty().bindBidirectional(wslForm.commandProperty());
    }
}
