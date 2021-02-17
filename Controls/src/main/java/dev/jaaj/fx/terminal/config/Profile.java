package dev.jaaj.fx.terminal.config;

import dev.jaaj.fx.terminal.config.shell.AbstractShellConfig;
import dev.jaaj.fx.terminal.config.terminal.TerminalThemeConfig;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * a profile is composed of a ShellConfig and and a ThemeConfig
 */
public class Profile {
    private final ObjectProperty<AbstractShellConfig> shellConfig = new SimpleObjectProperty<>();
    private final ObjectProperty<TerminalThemeConfig> terminalThemeConfig = new SimpleObjectProperty<>();

    public Profile(AbstractShellConfig shellConfig, TerminalThemeConfig themeConfig) {
        this.shellConfig.set(shellConfig);
        this.terminalThemeConfig.set(themeConfig);
    }

    public AbstractShellConfig getShellConfig() {
        return shellConfig.get();
    }

    public ObjectProperty<AbstractShellConfig> shellConfigProperty() {
        return shellConfig;
    }

    public void setShellConfig(AbstractShellConfig shellConfig) {
        this.shellConfig.set(shellConfig);
    }

    public TerminalThemeConfig getTerminalThemeConfig() {
        return terminalThemeConfig.get();
    }

    public ObjectProperty<TerminalThemeConfig> terminalThemeConfigProperty() {
        return terminalThemeConfig;
    }

    public void setTerminalThemeConfig(TerminalThemeConfig terminalThemeConfig) {
        this.terminalThemeConfig.set(terminalThemeConfig);
    }
}
