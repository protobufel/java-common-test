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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * An AssertJ compliant assertion class verifying whether the subject under the test is the proper
 * utility class.
 *
 * @author David Tesler
 */
public class UtilityClassAssert extends AbstractClassAssert<UtilityClassAssert> {

    public UtilityClassAssert(Class<?> actual) {
        super(actual, UtilityClassAssert.class);
    }

    public static UtilityClassAssert assertThat(Class<?> actual) {
        return new UtilityClassAssert(actual);
    }

    /**
     * Checks whether the subject under the test is the proper utility class, and if it is, then also
     * accesses its private constructor to bump the class coverage.
     */
    public UtilityClassAssert isUtilityClass() {
        is(getUtilityCondition());
        touchPrivateConstructor(actual);
        return this;
    }

    /**
     * Checks whether the subject under the test is not the proper utility class.
     */
    public UtilityClassAssert isNotUtilityClass() {
        isNot(getUtilityCondition());
        return this;
    }

    protected Condition<Class<?>> getUtilityCondition() {
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

    private final boolean touchPrivateConstructor(Class<?> clazz) {
        Constructor<?> pConstructor = null;
        Optional<Boolean> accessible = Optional.empty();

        try {
            pConstructor = clazz.getDeclaredConstructor();

            if (!Modifier.isPrivate(pConstructor.getModifiers())) {
                return false;
            }

            accessible = Optional.of(pConstructor.isAccessible());
            pConstructor.setAccessible(true);
            pConstructor.newInstance();
            pConstructor.setAccessible(accessible.get());
            return true;
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
        } finally {
            if ((pConstructor != null) && accessible.isPresent()) {
                pConstructor.setAccessible(accessible.get());
      }
    }

    return false;
  }
}
