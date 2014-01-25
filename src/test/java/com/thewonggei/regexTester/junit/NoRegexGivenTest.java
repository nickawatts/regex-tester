package com.thewonggei.regexTester.junit;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.1.
 */
//@RunWith(value=RegexTestSuite.class)
//com.thewonggei.regexTester.junit.Regex annotation is missing!
public class NoRegexGivenTest {

	@RegexTestStrings
	public static List<RegexTestStringInfo> getTestParameters() {
		return Arrays.asList(new RegexTestStringInfo[] {
				new RegexTestStringInfo(true, "com"),
				new RegexTestStringInfo(true, "com.thewonggei"),
				new RegexTestStringInfo(true, "com.thewonggei.regexTester"),
				new RegexTestStringInfo(false, ".com.thewonggei"),
				new RegexTestStringInfo(false, "www.google.com")
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
