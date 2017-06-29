/*
 *    Copyright 2017 David Tesler
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.github.protobufel.test.common.misc;

import org.assertj.core.api.AbstractClassAssert;
import org.assertj.core.api.Condition;
import org.assertj.core.condition.AllOf;
import org.assertj.core.condition.Not;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

public class UtilityClassAssert extends AbstractClassAssert<UtilityClassAssert> {

    public UtilityClassAssert(Class<?> actual) {
        super(actual, UtilityClassAssert.class);
    }

    public static UtilityClassAssert assertThat(Class<?> actual) {
        return new UtilityClassAssert(actual);
    }

    // FIXME: remove after savepoint
    public UtilityClassAssert isUtilityClassOld() {
        isNotNull()
                .isPublic()
                .isFinal()
                .isNotInterface()
                .isNotAnnotation()
                .is(
                        new Condition<Class<?>>(
                                clazz -> !clazz.isMemberClass() || Modifier.isStatic(clazz.getModifiers()),
                                "top level or static"))
                .isNot(new Condition<Class<?>>(clazz -> clazz.isAnonymousClass(), "anonymous"))
                .isNot(new Condition<Class<?>>(clazz -> clazz.isArray(), "array"))
                .isNot(new Condition<Class<?>>(clazz -> clazz.isEnum(), "enum"))
                .isNot(new Condition<Class<?>>(clazz -> clazz.isLocalClass(), "local class"))
                .isNot(new Condition<Class<?>>(clazz -> clazz.isPrimitive(), "primitive"))
                .isNot(new Condition<Class<?>>(clazz -> clazz.isSynthetic(), "synthetic"))
                .is(
                        new Condition<Class<?>>(
                                clazz -> clazz.getConstructors().length == 0, "with public constructors"))
                .is(
                        new Condition<Class<?>>(
                                clazz -> clazz.getDeclaredConstructors().length == 1, "with only one constructor"))
                .is(
                        new Condition<Class<?>>(
                                clazz ->
                                        Arrays.stream(clazz.getDeclaredConstructors())
                                                .allMatch(constructor -> Modifier.isPrivate(constructor.getModifiers())),
                                "with only private constructor"));
        return this;
    }

    public UtilityClassAssert isUtilityClass() {
        is(getUtilityCondition());
        return this;
    }

    public UtilityClassAssert isNotUtilityClass() {
        isNot(getUtilityCondition());
        return this;
    }

    private Condition<Class<?>> getUtilityCondition() {
        return AllOf.allOf(
                Not.not(new Condition<Class<?>>(clazz -> Objects.isNull(clazz), "null")),
                new Condition<Class<?>>(clazz -> Modifier.isPublic(clazz.getModifiers()), "public"),
                new Condition<Class<?>>(clazz -> Modifier.isFinal(clazz.getModifiers()), "final"),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isAnnotation(), "annotation")),
                new Condition<Class<?>>(
                        clazz -> !clazz.isMemberClass() || Modifier.isStatic(clazz.getModifiers()),
                        "top level or static"),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isAnonymousClass(), "anonymous")),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isArray(), "array")),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isEnum(), "enum")),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isLocalClass(), "local class")),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isPrimitive(), "primitive")),
                Not.not(new Condition<Class<?>>(clazz -> clazz.isSynthetic(), "synthetic")),
                new Condition<Class<?>>(
                        clazz -> clazz.getConstructors().length == 0, "with public constructors"),
                new Condition<Class<?>>(
                        clazz -> clazz.getDeclaredConstructors().length == 1, "with only one constructor"),
                new Condition<Class<?>>(
                        clazz ->
                                Arrays.stream(clazz.getDeclaredConstructors())
                                        .allMatch(constructor -> Modifier.isPrivate(constructor.getModifiers())),
                        "with only private constructor"));
    }
}
