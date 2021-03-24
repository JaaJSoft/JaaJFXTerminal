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

package dev.jaaj.fx.terminal.controls.about.data.adapter;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dev.jaaj.fx.terminal.controls.about.data.AppInfo;
import dev.jaaj.fx.terminal.controls.about.data.Library;
import dev.jaaj.fx.terminal.controls.about.data.Person;
import dev.jaaj.fx.terminal.controls.about.data.builder.AppInfoBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AppInfoAdapter implements JsonSerializer<AppInfo>, JsonDeserializer<AppInfo> {
    private static final Type listOfTypePerson = new TypeToken<ArrayList<Person>>() {
    }.getType();
    private static final Type listOfTypeLibs = new TypeToken<ArrayList<Library>>() {
    }.getType();

    @Override
    public JsonElement serialize(AppInfo src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("appName", src.getAppName());
        jsonObject.addProperty("aboutText", src.getAboutText());
        jsonObject.addProperty("version", src.getVersion());
        jsonObject.addProperty("icon", src.getIcon());
        jsonObject.add("libs", context.serialize(new ArrayList<>(src.getLibsList()), listOfTypeLibs));
        jsonObject.add("developers", context.serialize(new ArrayList<>(src.getDevelopers()), listOfTypePerson));
        jsonObject.add("translators", context.serialize(new ArrayList<>(src.getTranslators()), listOfTypePerson));
        jsonObject.add("thanks", context.serialize(new ArrayList<>(src.getThanks()), listOfTypePerson));
        return jsonObject;
    }

    @Override
    public AppInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        AppInfoBuilder appInfoBuilder = new AppInfoBuilder();
        JsonElement appName = jsonObject.get("appName");
        if (appName != null) appInfoBuilder.setAppName(appName.getAsString());
        JsonElement aboutText = jsonObject.get("aboutText");
        if (aboutText != null) appInfoBuilder.setAboutText(aboutText.getAsString());
        JsonElement version = jsonObject.get("version");
        if (version != null) appInfoBuilder.setVersion(version.getAsString());
        JsonElement icon = jsonObject.get("icon");
        if (icon != null) appInfoBuilder.setIcon(icon.getAsString());
        JsonElement libs = jsonObject.get("libs");
        if (libs != null) {
            appInfoBuilder.setLibraries(context.deserialize(libs, listOfTypeLibs));
        }
        JsonElement developers = jsonObject.get("developers");
        if (developers != null) {
            appInfoBuilder.setDevelopers(context.deserialize(developers, listOfTypePerson));
        }
        JsonElement translators = jsonObject.get("translators");
        if (translators != null) {
            appInfoBuilder.setTranslators(context.deserialize(translators, listOfTypePerson));
        }
        JsonElement thanks = jsonObject.get("thanks");
        if (thanks != null) {
            appInfoBuilder.setThanks(context.deserialize(thanks, listOfTypePerson));
        }
        return appInfoBuilder.createAppInfo();
    }
}
