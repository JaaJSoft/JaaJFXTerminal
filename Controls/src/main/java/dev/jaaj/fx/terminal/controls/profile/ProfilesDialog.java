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
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.ResourceBundle;

public class ProfilesDialog extends Dialog<Void> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(ProfileForm.class.getPackageName() + ".Profile");

    public ProfilesDialog(ObservableList<Profile> profiles) {
        this.setTitle(BUNDLE.getString("profiles"));
        this.setHeaderText(BUNDLE.getString("manage_profiles"));
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        this.getDialogPane().setContent(new Profiles(profiles));
    }
}
