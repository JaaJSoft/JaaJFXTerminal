package dev.jaaj.fx.terminal.app;


import dev.jaaj.fx.terminal.controls.SSHTerminal;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public BorderPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            root.setCenter(new SSHTerminal("root", InetAddress.getByName("vps741987.ovh.net")));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
