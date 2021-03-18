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

package dev.jaaj.fx.terminal.models.theme.adapter;

import com.google.gson.*;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import dev.jaaj.fx.terminal.models.util.SerializableUtil;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.lang.reflect.Type;

public class TerminalThemeAdapter implements JsonSerializer<TerminalThemeConfig>, JsonDeserializer<TerminalThemeConfig> {
    @Override
    public JsonElement serialize(TerminalThemeConfig src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("themeName", src.getThemeName());
        result.addProperty("backgroundColor", SerializableUtil.getHexFromColor(src.getBackgroundColor()));
        result.addProperty("foregroundColor", SerializableUtil.getHexFromColor(src.getForegroundColor()));
        result.addProperty("cursorColor", SerializableUtil.getHexFromColor(src.getCursorColor()));
        result.addProperty("font", src.getFont().getName());
        result.addProperty("fontSize", src.getFont().getSize());
        return result;
    }

    @Override
    public TerminalThemeConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TerminalThemeConfig terminalThemeConfig = new TerminalThemeConfig();
        JsonObject jsonObject = json.getAsJsonObject();
        terminalThemeConfig.setThemeName(jsonObject.get("themeName").getAsString());
        terminalThemeConfig.setBackgroundColor(Color.valueOf(jsonObject.get("backgroundColor").getAsString()));
        terminalThemeConfig.setForegroundColor(Color.valueOf(jsonObject.get("foregroundColor").getAsString()));
        terminalThemeConfig.setCursorColor(Color.valueOf(jsonObject.get("cursorColor").getAsString()));
        String fontName = jsonObject.get("font").getAsString();
        double size = jsonObject.get("fontSize").getAsDouble();
        terminalThemeConfig.setFont(new Font(fontName, size));
        return terminalThemeConfig;
    }


}
