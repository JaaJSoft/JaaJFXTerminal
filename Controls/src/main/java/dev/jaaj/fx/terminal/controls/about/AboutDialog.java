package dev.jaaj.fx.terminal.controls.about;

import dev.jaaj.fx.terminal.controls.form.ssh.SSHFormDialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.ResourceBundle;

public class AboutDialog extends Dialog<Void> {
    private static final ResourceBundle ABOUT_BUNDLE = ResourceBundle.getBundle(AboutDialog.class.getPackageName() + ".About");

    public AboutDialog() {
        this.setTitle(ABOUT_BUNDLE.getString("title"));
        this.setHeaderText(ABOUT_BUNDLE.getString("header"));
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

    }
}
