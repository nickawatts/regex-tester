package com.thewonggei.regexTester.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks a method on a JUnit test case as
 * being the method that supplies a set of test strings against
 * which a given regular expression is to be tested.
 * 
 * The method you annotate with this annotation must conform to 
 * a specific signature to be used by the library, although the
 * name of the method does not matter. For example:
 * 
 * <code>
 * @RegexTestStrings
 * public static List<RegexTestStringInfo> getTestParameters() {
 *		return Arrays.asList(new RegexTestStringInfo[] {
 *				new RegexTestStringInfo(true, "com"),
 *				new RegexTestStringInfo(true, "com.thewonggei")
 *		});
 *	}
 * </code>
 * 
 * See this library's unit test suite for further examples of usage.
 * 
 * @author Nick Watts
 * @since 0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RegexTestStrings {
}
