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

import dev.jaaj.fx.core.form.AbstractForm;
import dev.jaaj.fx.terminal.models.profile.Profile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Skin;

import java.util.function.Function;

public class Profiles extends Control {
    private final ObservableList<Profile> profilesList;
    private final ObjectProperty<SelectionModel<Profile>> profileSelectionModel = new SimpleObjectProperty<>();
    private final ObjectProperty<AbstractForm<Profile>> profileForm = new SimpleObjectProperty<>();
    private final ObservableMap<Profile, AbstractForm<Profile>> mapProfileForm = FXCollections.observableHashMap();
    private Function<Profile, AbstractForm<Profile>> newForm = ProfileForm::new;

    public Profiles(ObservableList<Profile> profiles) {
        this.profilesList = profiles;
        profileSelectionModel.addListener((observable, oldValue, newValue) -> {
            newValue.selectedItemProperty().addListener((observableItem, oldItem, newItem) -> {
                AbstractForm<Profile> form = mapProfileForm.computeIfAbsent(newItem, newForm);
                profileForm.set(form);
                form.setVisible(newItem != null);
            });
            newValue.select(0);
        });
        profilesList.addListener((ListChangeListener<? super Profile>) c -> {
            c.next();
            c.getRemoved().forEach(mapProfileForm::remove);
        });
    }

    public void apply() {
        mapProfileForm.values().forEach(AbstractForm::apply);
    }

    public boolean validate() {
        return mapProfileForm.values().stream().allMatch(AbstractForm::validate);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ProfilesSkin(this);
    }

    public ObservableList<Profile> getProfiles() {
        return profilesList;
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

    public void setProfileForm(ProfileForm profileForm) {
        this.profileForm.set(profileForm);
    }

    public AbstractForm<Profile> getProfileForm() {
        return profileForm.get();
    }

    public ObjectProperty<AbstractForm<Profile>> profileFormProperty() {
        return profileForm;
    }

    public void setProfileForm(AbstractForm<Profile> profileForm) {
        this.profileForm.set(profileForm);
    }

    public Function<Profile, AbstractForm<Profile>> getNewForm() {
        return newForm;
    }

    public void setNewForm(Function<Profile, AbstractForm<Profile>> newForm) {
        this.newForm = newForm;
    }
}
