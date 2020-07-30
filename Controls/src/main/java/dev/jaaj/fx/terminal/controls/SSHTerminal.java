package dev.jaaj.fx.terminal.controls;

import javafx.scene.control.Skin;

import java.net.InetAddress;

public class SSHTerminal extends AbstractTerminal {
    private final InetAddress address;
    private final String user;

    public SSHTerminal(String user, InetAddress address) {
        super("ssh " + user + "@" + address.getHostName());
        this.address = address;
        this.user = user;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinTerminalFX(this);
    }
}
