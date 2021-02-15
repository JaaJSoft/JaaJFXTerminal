/*
 * Copyright 2020 JaaJSoft
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

package dev.jaaj.fx.terminal.controls;

import dev.jaaj.fx.terminal.config.WSLConfig;

public class WSLTerminal extends AbstractTerminal {

    private final WSLConfig wslConfig;

    public WSLTerminal(WSLConfig wslConfig) {
        super(wslConfig);
        this.wslConfig = wslConfig;
    }

    public WSLTerminal() {
        super(new WSLConfig());
        wslConfig = new WSLConfig();
    }

    @Override
    public String getTitle() {
        String distrib = getDistrib();
        String user = getUser();
        if (!user.isBlank()) {
            user += "@";
        }
        if (distrib.isBlank()) {
            distrib = "Default"; //TODO: resource bundle
        }
        return user + distrib;
    }


    public String getDistrib() {
        return wslConfig.getDistribution().getName();
    }

    public String getUser() {
        return wslConfig.getUser();
    }
}
