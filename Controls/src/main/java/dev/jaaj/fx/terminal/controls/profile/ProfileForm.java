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
import dev.jaaj.fx.terminal.controls.shell.ShellForm;
import dev.jaaj.fx.terminal.controls.shell.ShellFormFactory;
import dev.jaaj.fx.terminal.controls.shell.local.LocalShellFormFactory;
import dev.jaaj.fx.terminal.controls.shell.ssh.SSHFormFactory;
import dev.jaaj.fx.terminal.controls.shell.wsl.WSLFormFactory;
import dev.jaaj.fx.terminal.controls.util.FormFactoryVisitor;
import dev.jaaj.fx.terminal.models.profile.Profile;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroDarkTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroLightTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Skin;

import java.util.Optional;

public class ProfileForm extends AbstractForm<Profile> {

    private final ObjectProperty<Profile> profile = new SimpleObjectProperty<>();
    private final ObservableList<TerminalThemeConfig> terminalThemeConfigs = FXCollections.observableArrayList();
    private final ObjectProperty<SingleSelectionModel<TerminalThemeConfig>> terminalThemeSelectionModel = new SimpleObjectProperty<>();
    private final ObjectProperty<ShellForm<? extends AbstractShellConfig>> shellForm = new SimpleObjectProperty<>();
    //private final BooleanProperty nativeTheme = new SimpleBooleanProperty(true);

    public ProfileForm(Profile profile) {
        this();
        this.profile.set(profile);
    }

    public ProfileForm() {
        FormFactoryVisitor<ShellFormFactory<?>> formFactoryVisitor = new FormFactoryVisitor<ShellFormFactory<?>>()
                .register(new LocalShellFormFactory())
                .register(new SSHFormFactory())
                .register(new WSLFormFactory());
        terminalThemeConfigs.add(new DefaultJMetroLightTerminalThemeFactory().build());
        terminalThemeConfigs.add(new DefaultJMetroDarkTerminalThemeFactory().build());
        profileProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (terminalThemeSelectionModel.isNotNull().get()) {
                    getTerminalThemeSelectionModel().select(newValue.getTerminalThemeConfig());
                }
                Optional<ShellFormFactory<?>> visit = formFactoryVisitor.visit(newValue.getShellConfig());
                visit.ifPresent(shellFormFactory -> shellForm.set(shellFormFactory.build(newValue.getShellConfig())));
            }
        });
        terminalThemeSelectionModel.addListener((observable2, oldValue2, newValue2) -> {
            if (profile.isNotNull().get()) {
                getTerminalThemeSelectionModel().select(getProfile().getTerminalThemeConfig());
            }
        });
    }

    @Override
    public boolean validate() {
        if (shellForm.isNull().get()) {
            return false;
        }
        return shellForm.get().validate();
    }

    @Override
    public Profile apply() {
        Profile profile = this.profile.get();
        profile.setShellConfig(shellForm.get().apply());
        profile.setTerminalThemeConfig(getTerminalThemeSelectionModel().getSelectedItem());
        return profile;
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

    public ShellForm<? extends AbstractShellConfig> getShellForm() {
        return shellForm.get();
    }

    public ObjectProperty<ShellForm<? extends AbstractShellConfig>> shellFormProperty() {
        return shellForm;
    }

    public void setShellForm(ShellForm<? extends AbstractShellConfig> shellForm) {
        this.shellForm.set(shellForm);
    }
}
