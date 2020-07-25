package dev.jaaj.fx.terminal.controls;

import com.kodedu.terminalfx.Terminal;
import com.kodedu.terminalfx.config.TerminalConfig;
import javafx.scene.control.SkinBase;


public class SkinTerminalFX extends SkinBase<AbstractTerminal> {
    private final Terminal terminal;

    protected SkinTerminalFX(AbstractTerminal control) {
        super(control);
        TerminalConfig config = new TerminalConfig();
        if (!control.getStartCommand().isEmpty()) {
            config.setWindowsTerminalStarter(control.getStartCommand());
        }
        terminal = new Terminal(config, null);
        control.lastCommand.addListener((observable, oldValue, newValue) -> {
            terminal.command(newValue);
        });
        this.getChildren().add(terminal);
    }
}
