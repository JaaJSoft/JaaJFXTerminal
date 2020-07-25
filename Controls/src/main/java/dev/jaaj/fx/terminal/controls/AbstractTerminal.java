package dev.jaaj.fx.terminal.controls;

import dev.jaaj.fx.control.JaaJControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public abstract class AbstractTerminal extends JaaJControl {
    protected final StringProperty lastCommand = new SimpleStringProperty("");
    private final String startCommand;

    public AbstractTerminal() {
        this("");
    }

    public AbstractTerminal(String startCommand) {
        this.startCommand = startCommand;
    }

    public void execute(String command) {
        lastCommand.set(command);
    }

    public String getStartCommand() {
        return startCommand;
    }

    public String getLastCommand() {
        return lastCommand.get();
    }

    public StringProperty lastCommandProperty() {
        return lastCommand;
    }

    protected abstract Skin<?> createDefaultSkin();

}
