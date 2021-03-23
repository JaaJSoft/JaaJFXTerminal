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

package dev.jaaj.fx.terminal.controls.about.data;

import java.util.ArrayList;
import java.util.List;

public class AppInfoBuilder {
    private String appName;
    private String aboutText;
    private String version;
    private String icon;
    private List<Library> libraries = new ArrayList<>();
    private List<Person> developers = new ArrayList<>();
    private List<Person> translators = new ArrayList<>();
    private List<Person> thanks = new ArrayList<>();

    public AppInfoBuilder setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public AppInfoBuilder setAboutText(String aboutText) {
        this.aboutText = aboutText;
        return this;
    }

    public AppInfoBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public AppInfoBuilder setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public AppInfoBuilder setLibraries(List<Library> libraries) {
        this.libraries = libraries;
        return this;
    }

    public AppInfoBuilder setDevelopers(List<Person> developers) {
        this.developers = developers;
        return this;
    }

    public AppInfoBuilder setTranslators(List<Person> translators) {
        this.translators = translators;
        return this;
    }

    public AppInfoBuilder setThanks(List<Person> thanks) {
        this.thanks = thanks;
        return this;
    }

    public AppInfo createAppInfo() {
        return new AppInfo(appName, aboutText, version, icon, libraries, developers, translators, thanks);
    }
}
