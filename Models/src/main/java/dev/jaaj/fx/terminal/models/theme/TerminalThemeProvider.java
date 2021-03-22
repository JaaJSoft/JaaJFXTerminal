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

package dev.jaaj.fx.terminal.models.theme;

import dev.jaaj.fx.core.theme.Theme;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TerminalThemeProvider { //TODO loading from config
    private final Map<Class<? extends Theme>, TerminalThemeConfig> terminalThemeConfigMap = new HashMap<>();

    public TerminalThemeConfig getTerminalTheme(Theme theme) {
        return terminalThemeConfigMap.get(theme.getClass());
    }

    public void registerTheme(Theme theme, TerminalThemeConfig terminalThemeConfig) {
        terminalThemeConfigMap.put(theme.getClass(), terminalThemeConfig);
    }

    public Set<TerminalThemeConfig> getTerminalThemes() {
        return new HashSet<>(terminalThemeConfigMap.values());
    }
}
