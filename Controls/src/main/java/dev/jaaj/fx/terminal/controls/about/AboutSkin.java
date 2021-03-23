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
import dev.jaaj.fx.terminal.controls.about.data.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;

public class AboutSkin extends SkinFXML<About> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AboutSkin.class);

    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(AboutSkin.class.getPackageName() + ".About");

    @FXML
    ImageView iconImageView;
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

    protected AboutSkin(About control) {
        super(control, AboutSkin.class.getResource("About.fxml"), BUNDLE);

        AppInfo appInfo = control.getAppInfo();
        appInfo.iconProperty().addListener((observable, oldValue, newValue) -> {
            LOGGER.debug("new Icon");
            iconImageView.setImage(new Image(newValue));
        });
        String icon = appInfo.getIcon();
        if (icon != null) {
            this.iconImageView.setImage(new Image(icon));
        }
        appName.textProperty().bind(appInfo.appNameProperty());
        version.textProperty().bind(appInfo.versionProperty());
        aboutText.textProperty().bind(appInfo.aboutTextProperty());

        if (!appInfo.getLibsList().isEmpty()) {
            tabPane.getTabs().add(getLibraryTab(appInfo.getLibsList(), BUNDLE.getString("libsTab")));
        }
        if (!appInfo.getDevelopers().isEmpty()) {
            tabPane.getTabs().add(getPersonTab(appInfo.getDevelopers(), BUNDLE.getString("developersTab")));
        }
        if (!appInfo.getThanks().isEmpty()) {
            tabPane.getTabs().add(getPersonTab(appInfo.getThanks(), BUNDLE.getString("thanksTab")));
        }
        if (!appInfo.getTranslators().isEmpty()) {
            tabPane.getTabs().add(getPersonTab(appInfo.getTranslators(), BUNDLE.getString("translatorsTab")));
        }
    }

    private Tab getPersonTab(List<Person> personList, String title) {
        Tab tab = new Tab(title);
        AboutPeopleList peopleListControl = new AboutPeopleList(personList);
        ScrollPane scrollPane = new ScrollPane(peopleListControl);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);
        return tab;
    }

    private Tab getLibraryTab(List<Library> libraries, String title) {
        Tab tab = new Tab(title);
        AboutLibraryList peopleListControl = new AboutLibraryList(libraries);
        ScrollPane scrollPane = new ScrollPane(peopleListControl);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);
        return tab;
    }
}
