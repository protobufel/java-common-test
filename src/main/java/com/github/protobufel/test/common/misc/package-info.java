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

/**
 * This package provides some common AssertJ assertions and other test utilities.
 *
 * <p>
 *
 * <pre>
 import static com.github.protobufel.test.common.misc.CommonAssertions.assertThatType;
 import com.github.protobufel.test.common.misc.CommonJUnitSoftAssertions;

 class MyTest {

 //...

 {@literal @}Test
 public void testUtilityClass() {
 assertThatType(MyUtilityClass.class).isUtilityClass();
 }

 {@literal @}Test
 public void testSomethingElse() {
 assertThatType(MyNonUtilityClass.class).isNotUtilityClass();
 }

 // or use soft assertions:
 {@literal @}Rule
 public final CommonJunitSoftAssertions softly = new CommonJunitSoftAssertions();


 {@literal @}Test
 public void testUtilityClassSoftly() {
 softly.assertThatType(MyUtilityClass.class).isUtilityClass();
 softly.assertThat(true).isTrue();
 }

 //...
 }
 * </pre>
 *
 * @author David Tesler
 */
package com.github.protobufel.test.common.misc;
