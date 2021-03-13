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

package dev.jaaj.fx.terminal.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.InputStream;
import java.util.ResourceBundle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"), ResourceBundle.getBundle("dev/jaaj/fx/terminal/app/Terminal"));
        primaryStage.setTitle("JaaJFX - Terminal");
        Scene scene = new Scene(root, 800, 500);

        JMetro jMetro = new JMetro(scene, Style.DARK);
        //jMetro.setScene(scene);

        scene.getStylesheets().add(Main.class.getResource("styles/Styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
        InputStream inputStream = getClass().getResourceAsStream("img/icon.png");
        primaryStage.getIcons().add(new Image(inputStream));
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

