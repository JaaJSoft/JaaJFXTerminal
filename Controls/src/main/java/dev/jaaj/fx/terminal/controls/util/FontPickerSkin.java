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

package dev.jaaj.fx.terminal.controls.util;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.text.Font;
import org.controlsfx.dialog.FontSelectorDialog;

import java.util.Optional;

public class FontPickerSkin extends SkinBase<FontPicker> {

    protected FontPickerSkin(FontPicker control) {
        super(control);
        Button button = new Button();
        button.textProperty().bind(control.fontProperty().asString());
        button.setOnAction(this::openDialog);
        getChildren().setAll(button);
        button.setAlignment(Pos.CENTER_LEFT);
        button.prefWidthProperty().bind(control.widthProperty());
        button.prefHeightProperty().bind(control.heightProperty());
        button.minHeightProperty().bind(control.minHeightProperty());
        button.minWidthProperty().bind(control.minWidthProperty());
        button.maxHeightProperty().bind(control.maxHeightProperty());
        button.maxWidthProperty().bind(control.maxWidthProperty());
    }

    private void openDialog(ActionEvent event) {
        FontSelectorDialog fontSelectorDialog = new FontSelectorDialog(this.getSkinnable().getFont());
        fontSelectorDialog.initOwner(getSkinnable().getScene().getWindow());
        Optional<Font> optionalFont = fontSelectorDialog.showAndWait();
        optionalFont.ifPresent(font -> getSkinnable().setFont(font));
    }
}
