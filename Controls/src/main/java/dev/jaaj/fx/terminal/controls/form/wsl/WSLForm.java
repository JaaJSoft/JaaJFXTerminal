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

package dev.jaaj.fx.terminal.controls.form.wsl;

import dev.jaaj.fx.core.form.AbstractForm;
import dev.jaaj.fx.terminal.config.shell.WSLConfig;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Skin;

public class WSLForm extends AbstractForm<WSLConfig> {
    private final ObjectProperty<WSLConfig> wslConfig = new SimpleObjectProperty<>();

    private final ObjectProperty<Distribution> distribution = new SimpleObjectProperty<>();
    private final StringProperty user = new SimpleStringProperty();
    private final StringProperty workingDirectory = new SimpleStringProperty();
    private final StringProperty command = new SimpleStringProperty();
    private final ObservableList<Distribution> distributions = FXCollections.observableList(Distribution.getAll());

    public WSLForm() {
        this(new WSLConfig());
    }

    public WSLForm(WSLConfig config) {
        this.wslConfig.addListener((observable, oldValue, newValue) -> {
            distribution.set(newValue.getDistribution());
            user.set(newValue.getUser());
            workingDirectory.set(newValue.getWorkingDirectory());
            command.set(newValue.getCommand());
        });
        this.wslConfig.set(config);
    }

    @Override
    public boolean validate() {// TODO find a way to show error
        if (distribution.isNull().get() || distribution.getValue().getName().isBlank()) {
            return false;
        }
        return true;
    }

    @Override
    public WSLConfig apply() {
        WSLConfig config = wslConfig.getValue();
        if (config != null) {
            config.setDistribution(distribution.getValue());
            config.setUser(user.get());
            config.setWorkingDirectory(workingDirectory.getValue());
            config.setCommand(command.getValue());
        }
        return config;
    }

    public WSLConfig getWslConfig() {
        return wslConfig.get();
    }

    public ObjectProperty<WSLConfig> wslConfigProperty() {
        return wslConfig;
    }

    public void setWslConfig(WSLConfig wslConfig) {
        this.wslConfig.set(wslConfig);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new WSLFormSkin(this);
    }

    public Distribution getDistribution() {
        return distribution.get();
    }

    public ObjectProperty<Distribution> distributionProperty() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution.set(distribution);
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getWorkingDirectory() {
        return workingDirectory.get();
    }

    public StringProperty workingDirectoryProperty() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory.set(workingDirectory);
    }

    public String getCommand() {
        return command.get();
    }

    public void setCommand(String command) {
        this.command.set(command);
    }

    public StringProperty userProperty() {
        return user;
    }

    public StringProperty commandProperty() {
        return command;
    }

    public ObservableList<Distribution> getPossibleDistributions() {
        return distributions;
    }
}
