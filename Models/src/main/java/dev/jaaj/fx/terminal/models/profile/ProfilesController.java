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
import com.google.gson.reflect.TypeToken;
import dev.jaaj.fx.terminal.models.Settings;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.shell.adapter.AbstractShellAdapter;
import dev.jaaj.fx.terminal.models.shell.cmd.CmdShellAdapter;
import dev.jaaj.fx.terminal.models.shell.cmd.CmdShellConfig;
import dev.jaaj.fx.terminal.models.shell.powershell.PowerShellAdapter;
import dev.jaaj.fx.terminal.models.shell.powershell.PowerShellConfig;
import dev.jaaj.fx.terminal.models.shell.powershell.PwshAdapter;
import dev.jaaj.fx.terminal.models.shell.powershell.PwshConfig;
import dev.jaaj.fx.terminal.models.shell.ssh.SSHConfig;
import dev.jaaj.fx.terminal.models.shell.ssh.SSHConfigAdapter;
import dev.jaaj.fx.terminal.models.shell.wsl.WSLConfig;
import dev.jaaj.fx.terminal.models.shell.wsl.WSLShellAdapter;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import dev.jaaj.fx.terminal.models.theme.adapter.TerminalThemeAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProfilesController {
    private final ObservableList<Profile> profiles = FXCollections.observableArrayList();
    private final Gson gson;

    public ProfilesController(Path location) {
        GsonBuilder gsonBuilder = getBuilder();
        gson = gsonBuilder.create();
        Type listOfType = new TypeToken<ArrayList<Profile>>() {
        }.getType();
        try (BufferedReader bufferedReader = Files.newBufferedReader(location)) {
            List<Profile> fromJson = gson.fromJson(bufferedReader, listOfType);
            profiles.addAll(fromJson);
        } catch (IOException e) {
            e.printStackTrace();
            //no profile found
        }
        profiles.addListener((ListChangeListener<? super Profile>) c -> {
            saveProfiles(location, listOfType);
        });
    }

    public void saveProfiles(Path location, Type listOfType) {
        String toJson = gson.toJson(new ArrayList<>(profiles), listOfType);
        if (!Files.exists(location)) {
            try {
                Files.createDirectories(location.getParent());
                Files.createFile(location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(location)) {
            bufferedWriter.write(toJson);
        } catch (IOException e) {
            e.printStackTrace(); //todo error
        }
    }


    public ProfilesController(Path location, List<Profile> profiles) {
        this(location);
        this.profiles.addAll(profiles);
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
            AbstractShellConfig shellConfig = new CmdShellConfig();
            profiles.add(new Profile(shellConfig, Settings.getInstance().getTerminalThemeFromCurrentTheme()));
        }
        return profiles.get(0);
    }

    private GsonBuilder getBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(Profile.class, new ProfileAdapter())
                .registerTypeAdapter(AbstractShellConfig.class, new AbstractShellAdapter())
                .registerTypeAdapter(SSHConfig.class, new SSHConfigAdapter())
                .registerTypeAdapter(WSLConfig.class, new WSLShellAdapter())
                .registerTypeAdapter(CmdShellConfig.class, new CmdShellAdapter())
                .registerTypeAdapter(PowerShellConfig.class, new PowerShellAdapter())
                .registerTypeAdapter(PwshConfig.class, new PwshAdapter())
                .registerTypeAdapter(TerminalThemeConfig.class, new TerminalThemeAdapter())
                .setPrettyPrinting();
    }
}


