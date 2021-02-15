package dev.jaaj.fx.terminal.controls.form.wsl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Distribution {
    private final String name;
    private final Boolean isDefault;

    public Distribution(String name, Boolean isDefault) {
        this.name = name;
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Boolean isDefault() {
        return isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distribution that = (Distribution) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public static List<Distribution> getAll() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("wsl", "-l", "-v");
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<Distribution> distributions = reader.lines().filter(s -> s.length() > 1).map(s -> {
                boolean isDefault = false;
                if (s.charAt(1) == '*') {
                    isDefault = true;
                }
                String[] split = s.replace('*', ' ')
                        .replaceAll(String.valueOf((char) 0), "")
                        .trim()
                        .split(" ");
                return new Distribution(split[0], isDefault);
            }).collect(Collectors.toList());
            distributions.remove(0);// remove header "NAME"
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                return distributions;
            } else {
                return Collections.emptyList();
            }

        } catch (IOException | InterruptedException e) {
            return Collections.emptyList();
        }

    }
}
