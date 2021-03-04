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

import com.sun.javafx.application.HostServicesDelegate;
import dev.jaaj.fx.terminal.controls.about.data.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class PersonViewer extends Control {
    private final ObjectProperty<Person> person = new SimpleObjectProperty<>();

    public PersonViewer(Person person) {
        this.person.set(person);
    }

    public PersonViewer() {
    }

    public Person getPerson() {
        return person.get();
    }

    public ObjectProperty<Person> personProperty() {
        return person;
    }

    public void setPerson(Person person) {
        this.person.set(person);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new PersonViewerSkin(this);
    }

    public void sendEmail() {
        try {
            Desktop.getDesktop().browse(new URL("mailto:" + getPerson().getEmail()).toURI());
        } catch (IOException | URISyntaxException ignored) {
        }
    }

    public void openWebsite() {
        try {
            Desktop.getDesktop().browse(new URL(person.get().getWebsite()).toURI());
        } catch (IOException | URISyntaxException ignored) {
        }
    }
}

