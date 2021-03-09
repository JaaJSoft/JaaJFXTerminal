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

package dev.jaaj.fx.terminal.controls.util;

import dev.jaaj.fx.core.form.AbstractForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormFactoryVisitor {
    private final List<AbstractFormFactory<?>> formFactories = new ArrayList<>();

    public FormFactoryVisitor register(AbstractFormFactory<?> formFactory) {
        formFactories.add(formFactory);
        return this;
    }

    public boolean unregister(AbstractFormFactory<?> formFactory) {
        return formFactories.remove(formFactory);
    }

    public Optional<AbstractForm<?>> visit(Object o) {
        for (AbstractFormFactory<?> formFactory : formFactories) {
            System.out.println(formFactory.getClass().toGenericString());
            if (formFactory.canBuild(o)) {
                return Optional.ofNullable(formFactory.build(o));
            }
        }
        return Optional.empty();
    }
}
