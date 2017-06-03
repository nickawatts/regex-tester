# regex-tester
A Java library that reduces the complexity of testing regular expressions with JUnit.

# Requirements
* Java 6 or above
* JUnit 4.5 or above
* Maven 3 (if building the code)

__If you need support for earlier Java or JUnit versions, please submit an [issue
report](https://github.com/nickawatts/regex-tester/issues) explaining your needs.__

# What is regex-tester?
*regex-tester* provides a simple API for testing regular expressions in Java. Using annotations that fit naturally with
JUnit, you simply specify the regular expression, the strings to test against and indicate if each string should produce 
a match. In other words, *regex-tester* handles the boiler-plate code that you must write to perform such tests in JUnit
and leaves only a simple declaration of what is to be tested.

# Why Have a Regex Test Library?
Because regular expressions are complex and error prone and you probably don't unit test them! A regular expression, or
 regex for short, often represents important business logic and can expand out to thousands, or more, variations. Since
 regexes are so compact and ubiquitous in programming, developers tend to overlook them when looking for code to test.
 And since code coverage tools overlook them too, every regex represents complex, brittle yet important code that is
 not covered by unit tests.

# Basic Usage
*regex-tester* provides a fluent API that hooks into JUnit. With this API, you can create a JUnit test case for each
regex you want to test. The test case below shows several assertions being made about strings that match and don't match
the regex `The\s+Journal\s+of\s+(\w+)`. You can make simple assertions like "The string 'blah' does match the regex but
the string 'woohoo' doesn't". You can also assert that numbered capture groups in the regex do or don't match specific
text strings. The fluent API allows you to build up a set of assertions to make against the regex, all of which are 
automatically executed when you run the unit test.

    @RunWith(value=RegexTestSuite.class)
    @Regex(value="The\\s+Journal\\s+of\\s+(\\w+)")
    public class BasicRegexTest {
	
        @RegexAssertions
        public static RegexAssertionSet getRegexAssertions() {
            /*
             * A public static method named getRegexAssertions and annotated 
             * with @RegexAssertions defines the things to assert about the regex.
             */
            return new RegexAssertionSetBuilder()
                .addMatchAssertion("The Journal of Physics")
                .addNoMatchAssertion("The Journal of Physics Letters")
                .addMatchAssertion("The Journal of Chemistry")
                .addGroupMatchesAtAssertion(1, "Physics", "The Journal of Physics", true)
                .addGroupMatchesAtAssertion(1, "Physics", "The Journal of Chemistry", false)
                .build();
        }
        
        @Test
        public void test() {
            /*
             * A single, empty test method must exist to satisfy JUnit.
             */
        }
    }
        
*regex-tester* uses as little boiler-plate code as possible so that each test case makes a clear statement about
 various types of strings that match and don't match the regex. The `@RunWith` annotation delegates responsibility for
 running the test to the API. `@Regex` decleares the regex that is under test. `@RegexAssertions` declares the assertions 
 you want to make about what does and doesn't match the regex. Using just three annotations, you're able to communicate
 your expectations about the usage and limits of a regex to other developers.
 
# Getting Started
The test suite for the API itself is meant to demonstrate its capabilities. There are many tests and they're commented
to help you figure out how to use the library. Start with the `BasicRegextTest` class. It shows the most common usage
scenarios for the libray, just like in the *Basic Usage* section above.

# Download

## Maven

    <dependency>
        <groupId>com.thewonggei</groupId>
        <artifactId>regex-tester</artifactId>
        <version>0.2</version>
    </dependency>
