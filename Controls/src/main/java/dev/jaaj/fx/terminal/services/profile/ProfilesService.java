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

package dev.jaaj.fx.terminal.services.profile;

import dev.jaaj.fx.terminal.models.profile.Profile;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.shell.DefaultShellConfig;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroLightTerminalThemeFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfilesService {
    private final ObservableList<Profile> profiles = FXCollections.observableArrayList();

    public ProfilesService() {

    }

    public void saveProfile(Profile profile) {
        profiles.add(profile);
    }


    public boolean deleteProfile(Profile profile) {

        return profiles.remove(profile);
    }

    public ObservableList<Profile> getProfiles() {
        return profiles;
    }

    public Profile getDefaultProfile() {
        if (profiles.isEmpty()) {
            AbstractShellConfig shellConfig = new DefaultShellConfig();
            profiles.add(new Profile("Default", shellConfig, new DefaultJMetroLightTerminalThemeFactory().build()));
        }
        return profiles.get(0);
    }
}


