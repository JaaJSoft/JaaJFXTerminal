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

import dev.jaaj.fx.core.theme.windows.Windows10DarkTheme;
import dev.jaaj.fx.core.theme.windows.Windows10LightTheme;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerminalThemeProviderTest {

    private TerminalThemeProvider terminalThemeProvider;
    private TerminalThemeConfig lightTheme;
    private TerminalThemeConfig darkTheme;

    @Before
    public void setUp() throws Exception {
        terminalThemeProvider = new TerminalThemeProvider();
        lightTheme = new DefaultJMetroLightTerminalThemeFactory().build();
        terminalThemeProvider.registerTheme(new Windows10LightTheme(), lightTheme);
        darkTheme = new DefaultJMetroDarkTerminalThemeFactory().build();
        terminalThemeProvider.registerTheme(new Windows10DarkTheme(), darkTheme);
    }

    @Test
    public void getTerminalTheme() {
        assertEquals(lightTheme, terminalThemeProvider.getTerminalTheme(new Windows10LightTheme()));
        assertEquals(darkTheme, terminalThemeProvider.getTerminalTheme(new Windows10DarkTheme()));
    }

}
