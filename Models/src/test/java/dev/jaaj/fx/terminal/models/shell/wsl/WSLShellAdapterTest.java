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

package dev.jaaj.fx.terminal.models.shell.wsl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jaaj.fx.terminal.models.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.models.shell.adapter.AbstractShellAdapter;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WSLShellAdapterTest {
    private static WSLConfig CONFIG;
    private static Gson gson;
    private static final String JSON = "{\"type\":\"dev.jaaj.fx.terminal.models.shell.wsl.WSLConfig\",\"properties\":{\"distribution\":{\"name\":\"ubuntu\",\"isDefault\":true},\"user\":\"pierre\",\"workingDirectory\":\".\",\"commandToExecute\":\"ls\",\"extraArgs\":\"\",\"shellIcon\":\"" + WSLConfig.class.getResource("wsl.png").toExternalForm() + "\"}}";

    @BeforeClass
    public static void beforeClass() throws Exception {
        CONFIG = new WSLConfig();
        CONFIG.setDistribution(new Distribution("ubuntu", true));
        CONFIG.setUser("pierre");
        CONFIG.setWorkingDirectory(".");
        CONFIG.setCommandToExecute("ls");

        gson = new GsonBuilder()
                .registerTypeAdapter(WSLConfig.class, new WSLShellAdapter())
                .registerTypeAdapter(AbstractShellConfig.class, new AbstractShellAdapter())
                .create();
    }

    @Test
    public void serialize() {
        String s = gson.toJson(CONFIG, AbstractShellConfig.class);
        assertEquals(JSON, s);
    }

    @Test
    public void deserialize() {
        AbstractShellConfig configFromJson = gson.fromJson(JSON, AbstractShellConfig.class);
        assertEquals(CONFIG, configFromJson);
    }
}
