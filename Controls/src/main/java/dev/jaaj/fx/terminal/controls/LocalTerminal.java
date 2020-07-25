package dev.jaaj.fx.terminal.controls;

import javafx.scene.control.Skin;

public class LocalTerminal extends AbstractTerminal {
    public LocalTerminal() {
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }
}
