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

import dev.jaaj.fx.terminal.models.profile.Profile;
import javafx.scene.control.*;

import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileListView extends ListView<Profile> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(ProfileForm.class.getPackageName() + ".Profile");

    public ProfileListView() {
        this.setCellFactory(param -> {
            ListCell<Profile> profileCell = new ListCell<>();
            profileCell.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    profileCell.textProperty().bind(newValue.profileNameProperty());
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem delete = new MenuItem(BUNDLE.getString("delete"));
                    delete.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.initOwner(this.getScene().getWindow());
                        alert.setTitle(BUNDLE.getString("delete"));
                        alert.setHeaderText(BUNDLE.getString("delete"));
                        alert.setContentText(BUNDLE.getString("delete_prompt"));
                        Optional<ButtonType> buttonType = alert.showAndWait();
                        buttonType.ifPresent(buttonType1 -> {
                            if (buttonType1 == ButtonType.OK) {
                                this.getItems().remove(newValue);
                            }
                        });
                    });
                    MenuItem rename = new MenuItem(BUNDLE.getString("rename"));
                    rename.setOnAction(event -> {
                        TextInputDialog dialogRename = new TextInputDialog(newValue.getProfileName());
                        dialogRename.initOwner(this.getScene().getWindow());
                        dialogRename.setTitle(BUNDLE.getString("rename"));
                        dialogRename.setHeaderText(BUNDLE.getString("rename"));
                        dialogRename.setContentText(BUNDLE.getString("profile_name"));
                        Optional<String> optional = dialogRename.showAndWait();
                        optional.ifPresent(newValue::setProfileName);
                    });

                    contextMenu.getItems().addAll(delete, rename);
                    profileCell.setContextMenu(contextMenu);
                } else {
                    profileCell.setGraphic(null);
                }
            });
            return profileCell;
        });

    }
}
