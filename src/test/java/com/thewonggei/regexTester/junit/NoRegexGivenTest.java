package com.thewonggei.regexTester.junit;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.RegexAnnotationNotFoundException;

/**
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.1.
 */
//@RunWith(value=RegexTestSuite.class)
//com.thewonggei.regexTester.junit.Regex annotation is missing!
public class NoRegexGivenTest {

	@RegexTestStrings
	public static List<RegexPojo> getTestParameters() {
		return Arrays.asList(new RegexPojo[] {
				new RegexPojo(true, "com"),
				new RegexPojo(true, "com.thewonggei"),
				new RegexPojo(true, "com.thewonggei.regexTester"),
				new RegexPojo(false, ".com.thewonggei"),
				new RegexPojo(false, "www.google.com")
		});
	}
	
	/*
	 * This test will always fail because it will result in an initialization
	 * error within the JUnit framework. Uncommenting the @Test and @RunWith
	 * annotations will demonstrate this. I haven't figured out yet how to coerce
	 * JUnit into throwing the real exception (which would make this test pass)
	 * rather than reporting an initialization error.
	 */
//	@Test(expected=RegexAnnotationNotFoundException.class)
	public void test() {}
	
}
