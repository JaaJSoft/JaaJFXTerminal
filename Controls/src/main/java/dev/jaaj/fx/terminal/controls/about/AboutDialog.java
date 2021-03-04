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

package dev.jaaj.fx.terminal.controls.about;

import dev.jaaj.fx.terminal.controls.about.data.AppInfo;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ResourceBundle;

public class AboutDialog extends Dialog<Void> {

    private static final ResourceBundle ABOUT_BUNDLE = ResourceBundle.getBundle(AboutDialog.class.getPackageName() + ".About");

    public AboutDialog(AppInfo appInfo) {
        this.setTitle(appInfo.getAppName());
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        this.getDialogPane().setContent(new About(appInfo));

    }
}
