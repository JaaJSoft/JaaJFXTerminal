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
import dev.jaaj.fx.terminal.controls.about.data.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

public class PersonViewerSkin extends SkinFXML<PersonViewer> {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(AboutSkin.class.getPackageName() + ".About");
    @FXML
    Label nameLabel;
    @FXML
    Label titleLabel;
    @FXML
    Button mailBtn;
    @FXML
    Button websiteBtn;
    @FXML
    HBox buttonBox;

    public PersonViewerSkin(PersonViewer control) {
        super(control, PersonViewer.class.getResource("Person.fxml"), BUNDLE);
        control.personProperty().addListener((observable, oldValue, newValue) -> {
            set(newValue);
        });
        if (control.getPerson() != null) {
            set(control.getPerson());
        }
    }

    private void set(Person newValue) {
        if (newValue != null) {
            String name = newValue.getName();
            if (name != null && !name.isBlank()) {
                this.nameLabel.setText(name);
            }
            String title = newValue.getTitle();
            if (title != null && !title.isBlank()) {
                this.titleLabel.setText(title);
            }
            String email = newValue.getEmail();
            if (email == null || email.isBlank()) {
                buttonBox.getChildren().remove(mailBtn);
            }
            this.mailBtn.setTooltip(new Tooltip(email));
            String website = newValue.getWebsite();
            if (website == null || website.isBlank()) {
                buttonBox.getChildren().remove(websiteBtn);
            }
            this.websiteBtn.setTooltip(new Tooltip(website));
        }
    }

    public void sendEmail(ActionEvent actionEvent) {
        this.getSkinnable().sendEmail();
    }

    public void openWebsite(ActionEvent actionEvent) {
        this.getSkinnable().openWebsite();
    }
}
