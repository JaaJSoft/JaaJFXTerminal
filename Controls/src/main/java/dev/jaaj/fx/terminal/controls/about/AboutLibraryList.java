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
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.List;

public class AboutLibraryList extends VBox {
    public AboutLibraryList(List<Library> libraries) {
        this(libraries, 0);
    }

    public AboutLibraryList(List<Library> libraries, double spacing) {
        super(spacing);
        libraries.stream().map(LibraryViewer::new).forEach(libraryViewer -> {
            this.getChildren().add(libraryViewer);
            this.getChildren().add(new Separator());
        });
    }
}
