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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jaaj.fx.terminal.models.theme.DefaultJMetroLightTerminalThemeFactory;
import dev.jaaj.fx.terminal.models.theme.TerminalThemeConfig;
import javafx.scene.text.Font;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerminalThemeAdapterTest {
    private static final TerminalThemeConfig TERMINAL_THEME_CONFIG = new DefaultJMetroLightTerminalThemeFactory().build();
    private static Gson gson;
    private static final String json = "{\"themeName\":\"Light JMetro\",\"backgroundColor\":\"#F8F8F8FF\",\"foregroundColor\":\"#000000FF\",\"cursorColor\":\"#000000FF\",\"font\":\"Arial\",\"fontSize\":14.0}";

    @BeforeClass
    public static void beforeClass() throws Exception {
        TERMINAL_THEME_CONFIG.setFont(new Font("Arial", 14));
        gson = new GsonBuilder()
                .registerTypeAdapter(TerminalThemeConfig.class, new TerminalThemeAdapter())
                .create();
    }

    @Test
    public void serialize() {
        String s = gson.toJson(TERMINAL_THEME_CONFIG);
        System.out.println(s);
        assertEquals(json, s);
    }

    @Test
    public void deserialize() {
        TerminalThemeConfig terminalThemeConfig = gson.fromJson(json, TerminalThemeConfig.class);
        assertEquals(TERMINAL_THEME_CONFIG, terminalThemeConfig);
    }
}
