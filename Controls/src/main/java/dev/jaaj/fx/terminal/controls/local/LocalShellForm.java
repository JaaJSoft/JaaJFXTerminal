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

package dev.jaaj.fx.terminal.controls.local;

import dev.jaaj.fx.core.form.AbstractForm;
import dev.jaaj.fx.terminal.config.shell.LocalShellConfig;
import dev.jaaj.fx.terminal.config.shell.WSLConfig;
import dev.jaaj.fx.terminal.controls.wsl.Distribution;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Skin;

public class LocalShellForm extends AbstractForm<LocalShellConfig> {
    private final ObjectProperty<LocalShellConfig> localShellConfig = new SimpleObjectProperty<>();
    private final StringProperty workingDirectory = new SimpleStringProperty();
    private final StringProperty executable = new SimpleStringProperty();

    public LocalShellForm(LocalShellConfig config) {
        this.localShellConfig.addListener((observable, oldValue, newValue) -> {
            workingDirectory.set(newValue.getCommandLine());
        });
        this.localShellConfig.set(config);
    }

    @Override
    public boolean validate() {// TODO find a way to show error
        if (executable.isNull().get() || workingDirectory.getValue().isBlank()) {
            return false;
        }
        return true;
    }

    @Override
    public LocalShellConfig apply() {
        LocalShellConfig config = localShellConfig.getValue();
        if (config != null) {
            /*config.setDistribution(distribution.getValue());
            config.setUser(user.get());
            config.setWorkingDirectory(workingDirectory.getValue());
            config.setCommand(command.getValue());*/
        }
        return config;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LocalShellFormSkin(this);
    }

    public LocalShellConfig getLocalShellConfig() {
        return localShellConfig.get();
    }

    public ObjectProperty<LocalShellConfig> localShellConfigProperty() {
        return localShellConfig;
    }

    public void setLocalShellConfig(LocalShellConfig localShellConfig) {
        this.localShellConfig.set(localShellConfig);
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

    public String getExecutable() {
        return executable.get();
    }

    public StringProperty executableProperty() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable.set(executable);
    }
}
