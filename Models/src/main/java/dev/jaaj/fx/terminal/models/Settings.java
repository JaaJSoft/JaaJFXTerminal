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

package dev.jaaj.fx.terminal.models;

import dev.jaaj.fx.core.theme.DefaultTheme;
import dev.jaaj.fx.core.theme.Theme;
import dev.jaaj.fx.core.theme.windows.Windows10DarkTheme;
import dev.jaaj.fx.core.theme.windows.Windows10LightTheme;
import dev.jaaj.fx.terminal.models.profile.ProfilesController;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroDarkTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroLightTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.nio.file.Path;

public class Settings {

    private static Settings instance;

    private final ObjectProperty<Theme> theme = new SimpleObjectProperty<>(new DefaultTheme());
    private final TerminalThemeProvider terminalThemeProvider;
    private final ObjectProperty<Path> configLocation = new SimpleObjectProperty<>();
    private final ObjectProperty<ProfilesController> profilesController = new SimpleObjectProperty<>();

    private Settings() {

        terminalThemeProvider = new TerminalThemeProvider();
        terminalThemeProvider.registerTheme(new Windows10LightTheme(), new DefaultJMetroLightTerminalThemeFactory().build());
        terminalThemeProvider.registerTheme(new Windows10DarkTheme(), new DefaultJMetroDarkTerminalThemeFactory().build());
        terminalThemeProvider.registerTheme(new DefaultTheme(), new DefaultJMetroLightTerminalThemeFactory().build());
        configLocation.addListener((observable, oldValue, newValue) -> profilesController.set(new ProfilesController(getConfigLocation().resolve("profiles.json"))));
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public Theme getTheme() {
        return theme.get();
    }

    public ObjectProperty<Theme> themeProperty() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme.set(theme);
    }

    public TerminalThemeProvider getTerminalThemeProvider() {
        return terminalThemeProvider;
    }

    public TerminalThemeConfig getTerminalThemeFromCurrentTheme() {
        return getTerminalThemeProvider().getTerminalTheme(getTheme());
    }

    public Path getConfigLocation() {
        return configLocation.get();
    }

    public void setConfigLocation(Path configLocation) {
        this.configLocation.set(configLocation);
    }

    public ProfilesController getProfilesController() {
        return profilesController.get();
    }

    public ObjectProperty<Path> configLocationProperty() {
        return configLocation;
    }

    public ReadOnlyObjectProperty<ProfilesController> profilesControllerProperty() {
        return profilesController;
    }
}

