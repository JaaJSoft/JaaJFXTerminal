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
import dev.jaaj.fx.core.form.FormFactoryVisitor;
import dev.jaaj.fx.terminal.controls.shell.ShellForm;
import dev.jaaj.fx.terminal.controls.shell.ShellFormFactory;
import dev.jaaj.fx.terminal.controls.shell.local.LocalShellFormFactory;
import dev.jaaj.fx.terminal.controls.shell.ssh.SSHFormFactory;
import dev.jaaj.fx.terminal.controls.shell.wsl.WSLFormFactory;
import dev.jaaj.fx.terminal.controls.theme.TerminalThemeForm;
import dev.jaaj.fx.terminal.models.Settings;
import dev.jaaj.fx.terminal.models.profile.Profile;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeProvider;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Skin;

import java.util.Optional;

public class ProfileForm extends AbstractForm<Profile> {

    private final ObjectProperty<SingleSelectionModel<TerminalThemeConfig>> terminalThemeSelectionModel = new SimpleObjectProperty<>();
    private final ObjectProperty<ShellForm<? extends AbstractShellConfig>> shellForm = new SimpleObjectProperty<>();
    private final ObjectProperty<AbstractForm<? extends TerminalThemeConfig>> terminalThemeForm = new SimpleObjectProperty<>(new TerminalThemeForm());
    private final BooleanProperty nativeTheme = new SimpleBooleanProperty(false);

    private final ObservableList<TerminalThemeConfig> terminalThemeConfigs = FXCollections.observableArrayList();
    private final ObjectProperty<TerminalThemeProvider> terminalThemeProvider = new SimpleObjectProperty<>();
    private final ObjectProperty<FormFactoryVisitor<ShellFormFactory<?>>> formFactoryVisitor = new SimpleObjectProperty<>();

    public ProfileForm(Profile profile) {
        this();
        this.setItem(profile);
    }

    public ProfileForm() {
        formFactoryVisitor.set(getDefaultShellFormFactory());
        terminalThemeProvider.addListener((observable, oldValue, newValue) -> terminalThemeConfigs.setAll(newValue.getTerminalThemes()));
        terminalThemeProvider.set(Settings.getInstance().getTerminalThemeProvider());
        profileProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (terminalThemeSelectionModel.isNotNull().get()) {
                    getTerminalThemeSelectionModel().select(newValue.getTerminalThemeConfig());
                }
                nativeTheme.set(newValue.getTerminalThemeConfig() == null);
                Optional<ShellFormFactory<?>> visit = formFactoryVisitor.get().visit(newValue.getShellConfig());
                visit.ifPresent(shellFormFactory -> shellForm.set(shellFormFactory.build(newValue.getShellConfig())));

            }
        });
        terminalThemeSelectionModel.addListener((observable2, oldValue2, newValue2) -> {
            if (itemProperty().isNotNull().get()) {
                getTerminalThemeSelectionModel().select(getProfile().getTerminalThemeConfig());
            }
        });

    }

    private FormFactoryVisitor<ShellFormFactory<?>> getDefaultShellFormFactory() {
        return new FormFactoryVisitor<ShellFormFactory<?>>()
                .register(new SSHFormFactory())
                .register(new WSLFormFactory())
                .register(new LocalShellFormFactory());
    }

    @Override
    public boolean validate() {
        if (shellForm.isNull().get()) {
            return false;
        }
        if (terminalThemeForm.isNull().get()) {
            return false;
        }
        return shellForm.get().validate() && terminalThemeForm.get().validate();
    }

    @Override
    public Profile apply() {
        Profile profile = getProfile();
        profile.setShellConfig(shellForm.get().apply());
        profile.setTerminalThemeConfig(terminalThemeForm.get().apply());
        return profile;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ProfileFormSkin(this);
    }

    public Profile getProfile() {
        return itemProperty().get();
    }

    public ObjectProperty<Profile> profileProperty() {
        return itemProperty();
    }

    public void setProfile(Profile profile) {
        this.setItem(profile);
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

    public void setTerminalThemeSelectionModel(SingleSelectionModel<TerminalThemeConfig> terminalThemeSelectionModel) {
        this.terminalThemeSelectionModel.set(terminalThemeSelectionModel);
    }

    public TerminalThemeProvider getTerminalThemeProvider() {
        return terminalThemeProvider.get();
    }

    public ObjectProperty<TerminalThemeProvider> terminalThemeProviderProperty() {
        return terminalThemeProvider;
    }

    public void setTerminalThemeProvider(TerminalThemeProvider terminalThemeProvider) {
        this.terminalThemeProvider.set(terminalThemeProvider);
    }

    public FormFactoryVisitor<ShellFormFactory<?>> getFormFactoryVisitor() {
        return formFactoryVisitor.get();
    }

    public ObjectProperty<FormFactoryVisitor<ShellFormFactory<?>>> formFactoryVisitorProperty() {
        return formFactoryVisitor;
    }

    public void setFormFactoryVisitor(FormFactoryVisitor<ShellFormFactory<?>> formFactoryVisitor) {
        this.formFactoryVisitor.set(formFactoryVisitor);
    }

    public boolean isNativeTheme() {
        return nativeTheme.get();
    }

    public BooleanProperty nativeThemeProperty() {
        return nativeTheme;
    }

    public void setNativeTheme(boolean nativeTheme) {
        this.nativeTheme.set(nativeTheme);
    }

    public AbstractForm<? extends TerminalThemeConfig> getTerminalThemeForm() {
        return terminalThemeForm.get();
    }

    public ObjectProperty<AbstractForm<? extends TerminalThemeConfig>> terminalThemeFormProperty() {
        return terminalThemeForm;
    }

    public void setTerminalThemeForm(AbstractForm<? extends TerminalThemeConfig> terminalThemeForm) {
        this.terminalThemeForm.set(terminalThemeForm);
    }
}
