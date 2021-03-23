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
import dev.jaaj.fx.terminal.controls.about.data.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

public class LibraryViewerSkin extends SkinFXML<LibraryViewer> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(AboutSkin.class.getPackageName() + ".About");
    @FXML
    Label nameLabel;
    @FXML
    Label licenceLabel;
    @FXML
    Button websiteBtn;
    @FXML
    HBox buttonBox;

    public LibraryViewerSkin(LibraryViewer control) {
        super(control, PersonViewer.class.getResource("Library.fxml"), BUNDLE);
        control.libraryProperty().addListener((observable, oldValue, newValue) -> set(newValue));
        if (control.getLibrary() != null) {
            set(control.getLibrary());
        }
    }

    private void set(Library newValue) {
        if (newValue != null) {
            String name = newValue.getName() + " " + newValue.getVersion();
            if (!name.isBlank()) {
                this.nameLabel.setText(name);
            }
            String licence = newValue.getLicence();
            if (licence != null && !licence.isBlank()) {
                this.licenceLabel.setText(licence);
            }
            String website = newValue.getWebsite();
            if (website == null || website.isBlank()) {
                buttonBox.getChildren().remove(websiteBtn);
            }
            this.websiteBtn.setTooltip(new Tooltip(website));
        }
    }

    public void openWebsite(ActionEvent actionEvent) throws IOException, URISyntaxException {
        this.getSkinnable().openWebsite();
    }
}
