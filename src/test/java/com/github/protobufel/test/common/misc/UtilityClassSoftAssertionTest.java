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

import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.github.protobufel.test.common.misc.CommonAssertions.assertThatType;

public class UtilityClassSoftAssertionTest {
    private CommonSoftAssertions softly;

    @Before
    public void setUp() throws Exception {
        softly = new CommonSoftAssertions();
    }

    @After
    public void tearDown() throws Exception {
        softly = null;
    }

    @Test
    public void softUtilityTestPositive() throws Exception {
        softly.assertThatType(UtilityHelperClass.class).isUtilityClass();
        softly.assertAll();
    }

    @Test
    public void softUtilityTestNegative() throws Exception {
        ImmutableList.of(
                UtilityHelperClass.NonUtility1.class,
                UtilityHelperClass.NonUtility2.class,
                UtilityHelperClass.NonUtility3.class,
                UtilityHelperClass.NonUtility4.class,
                UtilityHelperClass.NonUtility5.class)
                .forEach(clazz -> softly.assertThatType(clazz).isNotUtilityClass());
        softly.assertAll();
    }

    @Test
    public void utilityTestPositive() throws Exception {
        assertThatType(UtilityHelperClass.class).isUtilityClass();
    }

    @Test
    public void utilityTestNegative() throws Exception {
        ImmutableList.of(
                UtilityHelperClass.NonUtility1.class,
                UtilityHelperClass.NonUtility2.class,
                UtilityHelperClass.NonUtility3.class,
                UtilityHelperClass.NonUtility4.class,
                UtilityHelperClass.NonUtility5.class)
                .forEach(clazz -> assertThatType(clazz).isNotUtilityClass());
    }
}
