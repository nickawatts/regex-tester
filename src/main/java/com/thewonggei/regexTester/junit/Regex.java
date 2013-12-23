package com.thewonggei.regexTester.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used with a unit test class to provide the 
 * regular expression string.
 * 
 * For example:
 * <code>@Regex(value="^com.*")</code>
 * 
 * @author Nick Watts
 * @since 0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Regex {
	/**
	 * @return The regular expression.
	 */
	public String value();
}
