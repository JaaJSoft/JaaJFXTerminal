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

import javafx.scene.control.Skin;

public class WSLTerminal extends AbstractTerminal {

    private final String distrib;
    private final String user;

    public WSLTerminal() {
        super("wsl");
        distrib = "";
        user = "";
    }

    public WSLTerminal(String distrib) {
        super("wsl -d " + distrib);
        this.distrib = distrib;
        user = "";
    }

    public WSLTerminal(String distrib, String user) {
        super("wsl -d " + distrib + "-u " + user);
        this.user = user;
        this.distrib = distrib;
    }

    public String getDistrib() {
        return distrib;
    }

    public String getUser() {
        return user;
    }
}
