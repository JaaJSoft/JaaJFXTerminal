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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class ProfilesSkin extends SkinFXML<Profiles> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(ProfileForm.class.getPackageName() + ".Profile");

    @FXML
    SplitPane root;
    @FXML
    VBox right;
    @FXML
    ProfileListView listProfiles;
    @FXML
    ProfileForm profileForm;
    @FXML
    HBox buttons;
    @FXML
    Button btn1;
    @FXML
    Button btn2;

    public ProfilesSkin(Profiles control) {
        super(control, ProfileFormSkin.class.getResource("Profiles.fxml"), BUNDLE);
        listProfiles.setItems(control.getProfiles());
        listProfiles.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            profileForm.setVisible(newValue != null);
            profileForm.setProfile(newValue);
        });
        control.profileSelectionModelProperty().bind(listProfiles.selectionModelProperty());
        btn1.prefWidthProperty().bind(buttons.widthProperty().divide(buttons.getChildren().size()));
        btn2.prefWidthProperty().bind(buttons.widthProperty().divide(buttons.getChildren().size()));
        profileForm.prefWidthProperty().bind(right.widthProperty());
    }
}
