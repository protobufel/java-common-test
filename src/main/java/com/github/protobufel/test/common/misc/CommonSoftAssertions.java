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

import org.assertj.core.api.SoftAssertions;

/**
 * Additional common soft assertions.
 *
 * @author David Tesler
 */
public class CommonSoftAssertions extends SoftAssertions {

    /**
     * Initiates the flow of the utility class soft assertions.
     *
     * @param actual the class to test for being a proper utility class
     */
    public UtilityClassAssert assertThatType(Class<?> actual) {
        return proxy(UtilityClassAssert.class, Class.class, actual);
    }
}