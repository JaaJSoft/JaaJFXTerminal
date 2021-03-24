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
import dev.jaaj.fx.terminal.controls.about.data.Person;
import dev.jaaj.fx.terminal.controls.about.data.builder.PersonBuilder;

import java.lang.reflect.Type;

public class PersonAdapter implements JsonSerializer<Person>, JsonDeserializer<Person> {
    @Override
    public JsonElement serialize(Person src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("title", src.getTitle());
        jsonObject.addProperty("email", src.getEmail());
        jsonObject.addProperty("website", src.getWebsite());
        return jsonObject;
    }

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PersonBuilder personBuilder = new PersonBuilder();
        JsonElement name = jsonObject.get("name");
        if (name != null) personBuilder.setName(name.getAsString());
        JsonElement title = jsonObject.get("title");
        if (title != null) personBuilder.setTitle(title.getAsString());
        JsonElement email = jsonObject.get("email");
        if (email != null) personBuilder.setEmail(email.getAsString());
        JsonElement website = jsonObject.get("website");
        if (website != null) personBuilder.setWebsite(website.getAsString());
        return personBuilder.createPerson();
    }
}
