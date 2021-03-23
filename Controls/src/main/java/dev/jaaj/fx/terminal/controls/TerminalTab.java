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

package dev.jaaj.fx.terminal.controls;

import dev.jaaj.fx.terminal.controls.profile.ProfileDialog;
import dev.jaaj.fx.terminal.models.profile.Profile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class TerminalTab extends Tab {
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle(TerminalTab.class.getPackageName() + ".Terminal");
    private final ObjectProperty<Terminal> terminal = new SimpleObjectProperty<>();

    public TerminalTab(Terminal terminal) {
        super(terminal.getTitle(), terminal);
        textProperty().bind(terminal.getProfile().profileNameProperty());
        this.terminal.set(terminal);
        this.contentProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(oldValue, newValue) && newValue instanceof Terminal) {
                this.setTerminal((Terminal) newValue);
            }
        });
        this.terminalProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(oldValue, newValue)) {
                this.setContent(newValue);
            }
        });
        this.onCloseRequestProperty().addListener((observable, oldValue, newValue) -> terminal.close());
        this.onClosedProperty().addListener((observable, oldValue, newValue) -> terminal.close());
        this.setContextMenu(createContextMenu());
        this.setGraphic(createImage(terminal));
    }

    private Node createImage(Terminal terminal) {
        ImageView imageView = null;
        String shellIcon = terminal.getTerminalConfig().getShellIcon();
        if (shellIcon != null) {
            imageView = new ImageView(shellIcon);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(20);
        }

        return imageView;
    }

    private ContextMenu createContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem edit = new MenuItem(BUNDLE.getString("edit"));
        edit.setOnAction(event -> {
            ProfileDialog profileDialog = new ProfileDialog(getTerminal().getProfile());
            profileDialog.initOwner(this.getTabPane().getScene().getWindow());
            Optional<Profile> optional = profileDialog.showAndWait();
            optional.ifPresent(profile -> {

            });
        });
        contextMenu.getItems().add(edit);
        return contextMenu;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal.set(terminal);
        this.setContent(terminal);
    }

    public Terminal getTerminal() {
        return terminal.get();
    }

    public ObjectProperty<Terminal> terminalProperty() {
        return terminal;
    }

    @Override
    public String toString() {
        return "TerminalTab{" +
                "terminal=" + terminal.get() +
                '}';
    }
}
