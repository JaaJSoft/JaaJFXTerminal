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

import dev.jaaj.fx.terminal.controls.about.data.Library;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.io.IOException;
import java.net.URISyntaxException;

public class LibraryViewer extends Control {
    private final ObjectProperty<Library> library = new SimpleObjectProperty<>();

    public LibraryViewer(Library library) {
        this.setLibrary(library);
    }

    public LibraryViewer() {
    }

    public Library getLibrary() {
        return library.get();
    }

    public ObjectProperty<Library> libraryProperty() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library.set(library);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LibraryViewerSkin(this);
    }

    public void openWebsite() throws IOException, URISyntaxException {
        Util.openWebsite(getLibrary().getWebsite());
    }
}
