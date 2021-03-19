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

import com.google.gson.*;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;

import java.lang.reflect.Type;

public class ProfileAdapter implements JsonSerializer<Profile>, JsonDeserializer<Profile> {
    @Override
    public JsonElement serialize(Profile src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("profileName", src.getProfileName());
        result.add("shellConfig", context.serialize(src.getShellConfig(), AbstractShellConfig.class));
        result.add("terminalThemeConfig", context.serialize(src.getTerminalThemeConfig(), TerminalThemeConfig.class));
        return result;
    }

    @Override
    public Profile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String profileName = jsonObject.get("profileName").getAsString();
        AbstractShellConfig shellConfig = context.deserialize(jsonObject.get("shellConfig").getAsJsonObject(), AbstractShellConfig.class);
        TerminalThemeConfig terminalThemeConfig = context.deserialize(jsonObject.get("terminalThemeConfig").getAsJsonObject(), TerminalThemeConfig.class);
        return new Profile(profileName, shellConfig, terminalThemeConfig);
    }
}
