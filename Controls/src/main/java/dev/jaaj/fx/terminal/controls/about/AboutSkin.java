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

import dev.jaaj.fx.core.skin.SkinFXML;
import dev.jaaj.fx.terminal.controls.about.data.AppInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;

public class AboutSkin extends SkinFXML<About> {
    @FXML
    ImageView icon;
    @FXML
    Label appName;
    @FXML
    Label version;

    protected AboutSkin(About control) {
        super(control, AboutSkin.class.getResource("About.fxml"), ResourceBundle.getBundle(AboutSkin.class.getPackageName() + ".About"));
        AppInfo appInfo = control.getAppInfo();
        icon.imageProperty().bind(appInfo.iconProperty());
        appName.textProperty().bind(appInfo.appNameProperty());
        version.textProperty().bind(appInfo.versionProperty());
    }

}
