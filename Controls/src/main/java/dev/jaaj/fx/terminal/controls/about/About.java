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

import dev.jaaj.fx.terminal.controls.about.data.Person;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;

public class About extends Control {
    private final ObservableList<String> libsList = new SimpleListProperty<>();
    private final ObservableList<Person> developers = new SimpleListProperty<>();
    private final ObservableList<Person> translators = new SimpleListProperty<>();
    private final ObservableList<Person> thanks = new SimpleListProperty<>();
    private final ObjectProperty<Image> icon = new SimpleObjectProperty<>();
    private final StringProperty version = new SimpleStringProperty();
    private final StringProperty aboutText = new SimpleStringProperty();

    public ObservableList<String> getLibsList() {
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

    @Override
    protected Skin<?> createDefaultSkin() {
        return new AboutSkin(this);
    }
}
