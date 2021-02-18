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

package dev.jaaj.fx.terminal.controls.options;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class OptionsDialog extends Dialog<Boolean> { //TODO maybe return an enum flag or something

    public OptionsDialog() {

        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

    }
}
