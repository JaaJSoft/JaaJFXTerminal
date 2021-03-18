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

package dev.jaaj.fx.terminal.models.shell.ssh;

import com.google.gson.*;
import dev.jaaj.fx.terminal.models.shell.adapter.AbstractShellAdapter;

import java.lang.reflect.Type;
import java.net.InetAddress;

public class SSHConfigAdapter implements JsonSerializer<SSHConfig>, JsonDeserializer<SSHConfig> {
    @Override
    public JsonElement serialize(SSHConfig src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("inetAddress", context.serialize(src.getInetAddress()));
        result.addProperty("user", src.getUser());
        result.addProperty("port", src.getPort());
        result.addProperty("command", src.getCommand());
        AbstractShellAdapter.serializeProperties(result, src);
        return result;
    }

    @Override
    public SSHConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        SSHConfig config = new SSHConfig();
        config.setInetAddress(context.deserialize(jsonObject.get("inetAddress"), InetAddress.class));
        config.setUser(jsonObject.get("user").getAsString());
        config.setPort(jsonObject.get("port").getAsInt());
        config.setCommand(jsonObject.get("command").getAsString());
        AbstractShellAdapter.deserializeProperties(jsonObject, config);
        return config;
    }


}
