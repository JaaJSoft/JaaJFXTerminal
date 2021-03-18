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

package dev.jaaj.fx.terminal.models.shell.cmd;

import com.google.gson.*;
import dev.jaaj.fx.terminal.models.shell.adapter.LocalShellAdapterHelper;

import java.lang.reflect.Type;

public class CmdShellAdapter implements JsonSerializer<CmdShellConfig>, JsonDeserializer<CmdShellConfig> {

    @Override
    public JsonElement serialize(CmdShellConfig src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        LocalShellAdapterHelper.serializeProperties(result, src);
        return result;
    }

    @Override
    public CmdShellConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        CmdShellConfig config = new CmdShellConfig();
        LocalShellAdapterHelper.deserializeProperties(jsonObject, config);
        return config;
    }
}
