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

package dev.jaaj.fx.terminal.models.shell.adapter;

import com.google.gson.*;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;

import java.lang.reflect.Type;

public class AbstractShellAdapter implements JsonSerializer<AbstractShellConfig>, JsonDeserializer<AbstractShellConfig> {

    @Override
    public JsonElement serialize(AbstractShellConfig src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getCanonicalName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }

    @Override
    public AbstractShellConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");
        try {
            return context.deserialize(element, Class.forName(type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }

    public static void serializeProperties(JsonObject result, AbstractShellConfig config) {
        result.addProperty("shellIcon", config.getShellIcon());
    }

    public static void deserializeProperties(JsonObject input, AbstractShellConfig config) {
        config.setShellIcon(input.get("shellIcon").getAsString());
    }
}
