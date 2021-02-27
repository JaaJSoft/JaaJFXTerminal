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
import dev.jaaj.fx.terminal.config.factory.theme.DefaultJMetroDarkTerminalThemeFactory;
import dev.jaaj.fx.terminal.config.factory.theme.DefaultJMetroLightTerminalThemeFactory;
import dev.jaaj.fx.terminal.config.profile.Profile;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Skin;

public class ProfileForm extends AbstractForm<Profile> {

    private final ObjectProperty<Profile> profile = new SimpleObjectProperty<>();
    private final ObservableList<TerminalThemeConfig> terminalThemeConfigs = FXCollections.observableArrayList();
    private final ObjectProperty<SingleSelectionModel<TerminalThemeConfig>> terminalThemeSelectionModel = new SimpleObjectProperty<>();

    public ProfileForm(Profile profile) {
        this.profile.set(profile);
        terminalThemeConfigs.add(new DefaultJMetroLightTerminalThemeFactory().build());
        terminalThemeConfigs.add(new DefaultJMetroDarkTerminalThemeFactory().build());
        getTerminalThemeSelectionModel().select(profile.getTerminalThemeConfig());
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public Profile apply() {
        return null;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ProfileFormSkin(this);
    }

    public Profile getProfile() {
        return profile.get();
    }

    public ObjectProperty<Profile> profileProperty() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile.set(profile);
    }

    public ObservableList<TerminalThemeConfig> getTerminalThemeConfigs() {
        return terminalThemeConfigs;
    }


    public SingleSelectionModel<TerminalThemeConfig> getTerminalThemeSelectionModel() {
        return terminalThemeSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<TerminalThemeConfig>> terminalThemeSelectionModelProperty() {
        return terminalThemeSelectionModel;
    }

    public void setTerminalThemeSelectionModel(SingleSelectionModel<TerminalThemeConfig> terminalThemeSelectionModel) {
        this.terminalThemeSelectionModel.set(terminalThemeSelectionModel);
    }
}
