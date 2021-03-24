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
import dev.jaaj.fx.terminal.controls.about.data.Library;
import dev.jaaj.fx.terminal.controls.about.data.builder.LibraryBuilder;

import java.lang.reflect.Type;

public class LibraryAdapter implements JsonSerializer<Library>, JsonDeserializer<Library> {
    @Override
    public JsonElement serialize(Library src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("version", src.getVersion());
        jsonObject.addProperty("licence", src.getLicence());
        jsonObject.addProperty("website", src.getWebsite());
        return jsonObject;
    }

    @Override
    public Library deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        LibraryBuilder libraryBuilder = new LibraryBuilder();
        JsonElement name = jsonObject.get("name");
        if (name != null) libraryBuilder.setName(name.getAsString());
        JsonElement version = jsonObject.get("version");
        if (version != null) libraryBuilder.setVersion(version.getAsString());
        JsonElement licence = jsonObject.get("licence");
        if (licence != null) libraryBuilder.setLicence(licence.getAsString());
        JsonElement website = jsonObject.get("website");
        if (website != null) libraryBuilder.setWebsite(website.getAsString());
        return libraryBuilder.createLibrary();
    }
}
