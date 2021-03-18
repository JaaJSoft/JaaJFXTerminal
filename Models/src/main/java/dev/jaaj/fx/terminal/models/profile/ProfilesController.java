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

package dev.jaaj.fx.terminal.models.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jaaj.fx.terminal.models.Settings;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.shell.cmd.CmdShellConfig;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import dev.jaaj.fx.terminal.models.theme.adapter.TerminalThemeAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProfilesController {
    private final ObservableList<Profile> profiles = FXCollections.observableArrayList();

    public ProfilesController() {

    }

    public ProfilesController(List<Profile> profiles) {
        this.profiles.addAll(profiles);
    }

    public void saveProfile(Profile profile) {
        profiles.add(profile);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TerminalThemeConfig.class, new TerminalThemeAdapter())
                .create();
        String toJson = gson.toJson(profile.getTerminalThemeConfig());

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("test.json"))) {
            bufferedWriter.write(toJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TerminalThemeConfig terminalThemeConfig = gson.fromJson(toJson, TerminalThemeConfig.class);
    }


    public boolean deleteProfile(Profile profile) {
        return profiles.remove(profile);
    }

    public ObservableList<Profile> getProfiles() {
        return profiles;
    }

    public Profile getDefaultProfile() {
        if (profiles.isEmpty()) {
            AbstractShellConfig shellConfig = new CmdShellConfig();
            profiles.add(new Profile(shellConfig, Settings.getInstance().getTerminalThemeFromCurrentTheme()));
        }
        return profiles.get(0);
    }
}


