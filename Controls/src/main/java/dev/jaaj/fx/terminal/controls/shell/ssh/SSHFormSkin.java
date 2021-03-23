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

package dev.jaaj.fx.terminal.controls.shell.ssh;

import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.controls.util.IntegerField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

import java.util.ResourceBundle;

public class SSHFormSkin extends SkinFXML<SSHForm> {
    @FXML
    TextField addressField;
    @FXML
    TextField userField;
    @FXML
    IntegerField portField;
    @FXML
    TextField commandField;
    @FXML
    TitledPane advancedPane;

    public SSHFormSkin(SSHForm control) {
        super(control,
                SSHFormSkin.class.getResource("SSHForm.fxml"),
                ResourceBundle.getBundle(SSHFormSkin.class.getPackageName() + ".SSHForm")
        );
        bind(control);
        control.formStateProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READONLY:
                    addressField.setEditable(false);
                    userField.setEditable(false);
                    portField.setEditable(false);
                    commandField.setEditable(false);
                    break;
                case EDITABLE:
                    addressField.setEditable(true);
                    userField.setEditable(true);
                    portField.setEditable(true);
                    commandField.setEditable(true);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + newValue);
            }
        });
        advancedPane.setOnMouseClicked(event -> advancedPane.getScene().getWindow().sizeToScene());
    }

    public void unbind() {
        addressField.textProperty().unbind();
        userField.textProperty().unbind();
        portField.valueProperty().unbind();
        commandField.textProperty().unbind();
    }

    public void bind(SSHForm sshForm) {
        addressField.textProperty().bindBidirectional(sshForm.inetAddressStrProperty());
        userField.textProperty().bindBidirectional(sshForm.userProperty());
        portField.valueProperty().bindBidirectional(sshForm.portProperty());
        commandField.textProperty().bindBidirectional(sshForm.commandProperty());
    }
}
