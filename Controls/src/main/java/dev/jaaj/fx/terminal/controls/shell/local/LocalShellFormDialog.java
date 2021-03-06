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

package dev.jaaj.fx.terminal.controls.shell.local;

import dev.jaaj.fx.core.form.DialogForm;
import dev.jaaj.fx.terminal.models.shell.LocalShellConfig;

import java.util.ResourceBundle;

public class LocalShellFormDialog extends DialogForm<LocalShellConfig> {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(LocalShellFormDialog.class.getPackageName() + ".LocalShellForm");

    public LocalShellFormDialog(LocalShellConfig shellConfig) {
        super(new LocalShellForm(shellConfig), BUNDLE.getString("title_dialog"), String.format(BUNDLE.getString("header_dialog"), shellConfig.getTitle()));
    }

}
