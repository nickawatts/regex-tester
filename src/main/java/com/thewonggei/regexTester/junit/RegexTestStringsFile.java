package com.thewonggei.regexTester.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.thewonggei.regexTester.RegexTestStringInfoFileLoader;

/**
 * This annotation specifies the path to a properties file that
 * supplies a set of test strings against which a given regular 
 * expression is to be tested. By using this annotation, you cut
 * down the amount of code in your JUnit test to the bare minimum.
 * 
 * All JUnit test classes that use this annotation will follow the
 * following form:
 * 
 * <pre>
 * @RunWith(value=RegexTestSuite.class)
 * @Regex(value="[fF]oo.*")
 * @RegexTestStringsFile(propsFile="src/test/resources/regex-test-strings.properties")
 * public class BasicRegexTestWithPropsFile {
 * 
 *   @Test
 *   public void test() {}
 *   
 * }
 * </pre>
 * 
 * The file specified is a simple 
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Properties.html">
 * Java properties file</a> where the key is a string against which to test the 
 * regular expression and the value indicates whether or not a match should be 
 * found (either "true" or "false").
 * 
 * See this library's unit test suite for further examples of usage.
 * 
 * @author Nick Watts
 * @since 0.1
 * @see {@link RegexTestStringInfoFileLoader}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegexTestStringsFile {
	public String propsFile();
}
