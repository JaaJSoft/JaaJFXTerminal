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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Skin;

public class Profiles extends Control {
    private final ObservableList<Profile> profiles;
    private final ObjectProperty<SelectionModel<Profile>> profileSelectionModel = new SimpleObjectProperty<>();

    public Profiles(ObservableList<Profile> profiles) {
        this.profiles = profiles;
        profileSelectionModel.addListener((observable, oldValue, newValue) -> {
            newValue.select(0);
        });
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ProfilesSkin(this);
    }

    public ObservableList<Profile> getProfiles() {
        return profiles;
    }

    public SelectionModel<Profile> getProfileSelectionModel() {
        return profileSelectionModel.get();
    }

    public ObjectProperty<SelectionModel<Profile>> profileSelectionModelProperty() {
        return profileSelectionModel;
    }

    public void setProfileSelectionModel(SelectionModel<Profile> profileSelectionModel) {
        this.profileSelectionModel.set(profileSelectionModel);
    }
}
