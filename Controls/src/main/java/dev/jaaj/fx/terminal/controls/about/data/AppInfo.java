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

package dev.jaaj.fx.terminal.controls.about.data;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.List;

public class AppInfo {
    private final ObservableList<Library> libsList = FXCollections.observableArrayList();
    private final ObservableList<Person> developers = FXCollections.observableArrayList();
    private final ObservableList<Person> translators = FXCollections.observableArrayList();
    private final ObservableList<Person> thanks = FXCollections.observableArrayList();
    private final ObjectProperty<Image> icon = new SimpleObjectProperty<>();
    private final StringProperty version = new SimpleStringProperty();
    private final StringProperty aboutText = new SimpleStringProperty();
    private final StringProperty appName = new SimpleStringProperty();

    public AppInfo(String appName, String aboutText, String version, Image icon, List<Library> libraries, List<Person> developers, List<Person> translators, List<Person> thanks) {
        this.appName.set(appName);
        this.aboutText.set(aboutText);
        this.version.set(version);
        this.icon.set(icon);
        this.libsList.addAll(libraries);
        this.developers.addAll(developers);
        this.translators.addAll(translators);
        this.thanks.addAll(thanks);
    }

    public ObservableList<Library> getLibsList() {
        return libsList;
    }

    public ObservableList<Person> getDevelopers() {
        return developers;
    }

    public ObservableList<Person> getTranslators() {
        return translators;
    }

    public ObservableList<Person> getThanks() {
        return thanks;
    }

    public Image getIcon() {
        return icon.get();
    }

    public ObjectProperty<Image> iconProperty() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon.set(icon);
    }

    public String getVersion() {
        return version.get();
    }

    public StringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public String getAboutText() {
        return aboutText.get();
    }

    public StringProperty aboutTextProperty() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText.set(aboutText);
    }

    public String getAppName() {
        return appName.get();
    }

    public StringProperty appNameProperty() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName.set(appName);
    }
}
