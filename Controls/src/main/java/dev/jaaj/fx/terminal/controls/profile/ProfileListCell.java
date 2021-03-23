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
import dev.jaaj.fx.terminal.models.profile.ProfilesController;
import javafx.scene.control.*;

import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileListCell extends ListCell<Profile> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(ProfileForm.class.getPackageName() + ".Profile");

    @Override
    protected void updateItem(Profile item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            this.setDisable(false);
            this.textProperty().bind(item.profileNameProperty());
            ContextMenu contextMenu = new ContextMenu();
            String delete = BUNDLE.getString("delete");
            MenuItem deleteMenuItem = new MenuItem(delete);
            deleteMenuItem.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(this.getScene().getWindow());
                alert.setTitle(delete);
                alert.setHeaderText(delete);
                alert.setContentText(BUNDLE.getString("delete_prompt"));
                Optional<ButtonType> buttonType = alert.showAndWait();
                buttonType.ifPresent(buttonType1 -> {
                    if (buttonType1 == ButtonType.OK) {
                        this.getListView().getItems().remove(item);
                    }
                });
            });
            MenuItem rename = new MenuItem(BUNDLE.getString("rename"));
            rename.setOnAction(event -> {
                TextInputDialog dialogRename = new ProfileNameDialog(item);
                dialogRename.initOwner(this.getScene().getWindow());
                Optional<String> optional = dialogRename.showAndWait();
                optional.ifPresent(item::setProfileName);
            });
            MenuItem duplicate = new MenuItem(BUNDLE.getString("duplicate"));
            duplicate.setOnAction(event -> {
                Profile copy = ProfilesController.copy(item);
                getListView().getItems().add(copy);
            });

            contextMenu.getItems().addAll(rename, duplicate, deleteMenuItem);
            this.setContextMenu(contextMenu);
        } else {
            this.setDisable(true);
            this.textProperty().unbind();
            this.setText(null);
            this.setContextMenu(null);
            this.setGraphic(null);
        }
    }
}
