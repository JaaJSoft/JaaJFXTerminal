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
import dev.jaaj.fx.terminal.controls.about.data.Library;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.ResourceBundle;

public class AboutSkin extends SkinFXML<About> {

    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(AboutSkin.class.getPackageName() + ".About");

    @FXML
    ImageView icon;
    @FXML
    Label appName;
    @FXML
    Label version;
    @FXML
    TabPane tabPane;
    @FXML
    Tab aboutTab;
    @FXML
    Label aboutText;
    @FXML
    Tab libsTab;
    @FXML
    Tab developersTab;
    @FXML
    Tab thanksTab;
    @FXML
    Tab translatorsTab;

    protected AboutSkin(About control) {
        super(control, AboutSkin.class.getResource("About.fxml"), BUNDLE);

        AppInfo appInfo = control.getAppInfo();
        icon.imageProperty().bind(appInfo.iconProperty());
        appName.textProperty().bind(appInfo.appNameProperty());
        version.textProperty().bind(appInfo.versionProperty());
        aboutText.textProperty().bind(appInfo.aboutTextProperty());
        appInfo.getLibsList().addListener((ListChangeListener<? super Library>) c -> {
            tabPane.getTabs().add(getLibsTab());
        });

    }

    private Tab getAboutTab() {
        Tab tab = new Tab();
        return tab;
    }

    private TabPane getTabPane() {
        return tabPane;
    }

    private Tab getLibsTab() {
        return libsTab;
    }

    private Tab getDevelopersTab() {
        return developersTab;
    }

    private Tab getThanksTab() {
        return thanksTab;
    }

    private Tab getTranslatorsTab() {
        return translatorsTab;
    }
}
