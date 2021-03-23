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

package dev.jaaj.fx.terminal.models.shell.adapter;

import com.google.gson.JsonObject;
import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;

public class LocalShellAdapterHelper {
    
    private LocalShellAdapterHelper() {
    }

    public static void serializeProperties(JsonObject result, LocalShellConfig localShellConfig) {
        result.addProperty("workingDirectory", localShellConfig.getWorkingDirectory());
        result.addProperty("commandToExecute", localShellConfig.getCommandToExecute());
        result.addProperty("extraArgs", localShellConfig.getExtraArgs());
        AbstractShellAdapter.serializeProperties(result, localShellConfig);
    }

    public static void deserializeProperties(JsonObject input, LocalShellConfig localShellConfig) {
        localShellConfig.setWorkingDirectory(input.get("workingDirectory").getAsString());
        localShellConfig.setCommandToExecute(input.get("commandToExecute").getAsString());
        localShellConfig.setExtraArgs(input.get("extraArgs").getAsString());
        AbstractShellAdapter.deserializeProperties(input, localShellConfig);
    }

}
