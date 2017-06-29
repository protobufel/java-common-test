[![Travis branch](https://img.shields.io/travis/protobufel/java-common-test/master.svg?style=plastic)](https://travis-ci.org/protobufel/java-common-test)
[![codecov](https://codecov.io/gh/protobufel/java-common-test/branch/master/graph/badge.svg)](https://codecov.io/gh/protobufel/java-common-test)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.protobufel/java-common-test.svg?style=plastic)](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22java-common-test%22)

# Common Java 8 Test Utilities #


```java

 // This package provides some common AssertJ assertions and other test utilities.

 import static com.github.protobufel.test.common.misc.CommonAssertions.assertThatType;
 import com.github.protobufel.test.common.misc.CommonJUnitSoftAssertions;

 class MyTest {
     
 //...
 
     @Test
     public void testUtilityClass() {
        assertThatType(MyUtilityClass.class).isUtilityClass();
     }
     
     @Test
     public void testSomethingElse() {
        assertThatType(MyNonUtilityClass.class).isNotUtilityClass();
     }
     
// or use soft assertions:
    @Rule
    public final CommonJunitSoftAssertions softly = new CommonJunitSoftAssertions();

     
     @Test
     public void testUtilityClassSoftly() {
        softly.assertThatType(MyUtilityClass.class).isUtilityClass();
        softly.assertThat(true).isTrue();
     }
          
 //...
 }

```

##### For more see the [JavaDoc Documentation](https://protobufel.github.io/java-common-test/javadoc/ "JavaDoc and more").  

Happy coding,

David Tesler
