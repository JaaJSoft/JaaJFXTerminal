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

public class LibraryBuilder {
    private String name;
    private String version;
    private String licence;
    private String website;

    public LibraryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LibraryBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public LibraryBuilder setLicence(String licence) {
        this.licence = licence;
        return this;
    }

    public LibraryBuilder setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Library createLibrary() {
        return new Library(name, version, licence, website);
    }
}
