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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProfilesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilesController.class);

    private static final Gson GSON = getBuilder().create();

    private final ObservableList<Profile> profiles = FXCollections.observableArrayList();
    private final Path location;
    private final Type listOfType;

    public ProfilesController(Path location) throws IOException {
        this.location = location;
        listOfType = new TypeToken<ArrayList<Profile>>() {
        }.getType();
        try (BufferedReader bufferedReader = Files.newBufferedReader(location)) {
            List<Profile> fromJson = GSON.fromJson(bufferedReader, listOfType);
            profiles.addAll(fromJson);
        }
        profiles.addListener((ListChangeListener<? super Profile>) c -> {
            try {
                saveProfiles(this.location, listOfType);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }

    public void saveProfiles(Path location, Type listOfType) throws IOException {
        String toJson = GSON.toJson(new ArrayList<>(profiles), listOfType);
        if (!Files.exists(location)) {
            Files.createDirectories(location.getParent());
            Files.createFile(location);
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(location)) {
            bufferedWriter.write(toJson);
        }
    }

    public void savesProfiles() throws IOException {
        saveProfiles(location, listOfType);
    }


    public ProfilesController(Path location, List<Profile> profiles) throws IOException {
        this(location);
        this.profiles.addAll(profiles);
    }

    public boolean saveProfile(Profile profile) {
        if (profiles.contains(profile)) {
            return false;
        }
        profiles.add(profile);
        return true;
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
            profiles.add(new Profile(shellConfig, null));
        }
        return profiles.get(0);
    }

    public static GsonBuilder getBuilder() {
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

    public static Profile copy(Profile profile) {
        //Yes, it is disgusting.
        String s = GSON.toJson(profile);
        return GSON.fromJson(s, Profile.class);
    }
}


