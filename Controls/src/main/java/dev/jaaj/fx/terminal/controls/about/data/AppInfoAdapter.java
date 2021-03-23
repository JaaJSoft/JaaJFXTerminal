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

package dev.jaaj.fx.terminal.controls.about.data;


import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AppInfoAdapter implements JsonSerializer<AppInfo>, JsonDeserializer<AppInfo> {
    @Override
    public JsonElement serialize(AppInfo src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("appName", src.getAppName());
        jsonObject.addProperty("aboutText", src.getAboutText());
        jsonObject.addProperty("version", src.getVersion());
        jsonObject.addProperty("icon", src.getIcon());
        jsonObject.add("libs", context.serialize(new ArrayList<>(src.getLibsList()), ArrayList.class));
        jsonObject.add("developers", context.serialize(new ArrayList<>(src.getDevelopers()), ArrayList.class));
        jsonObject.add("translators", context.serialize(new ArrayList<>(src.getTranslators()), ArrayList.class));
        jsonObject.add("thanks", context.serialize(new ArrayList<>(src.getThanks()), ArrayList.class));
        return jsonObject;
    }

    @Override
    public AppInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
