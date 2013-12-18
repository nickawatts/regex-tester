package com.thewonggei.regexTester.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.RegexTestStringsNotFoundException;

/**
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.1.
 */
//@RunWith(value=RegexTestSuite.class)
@Regex(value=".*")
public class NoRegexTestStringsGivenTest {

	/*
	 * This test will always fail because it will result in an initialization
	 * error within the JUnit framework. Uncommenting the @Test and @RunWith
	 * annotations will demonstrate this. I haven't figured out yet how to coerce
	 * JUnit into throwing the real exception (which would make this test pass)
	 * rather than reporting an initialization error.
	 */
//	@Test(expected=RegexTestStringsNotFoundException.class)
	public void test() {}
	
}
