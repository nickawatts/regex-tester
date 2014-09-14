package com.thewonggei.regexTester.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.assertions.RegexAssertions;

/**
 * Unfortunately, you must run this test only to see that it fails, which affirms
 * what is being tested. An Exception is expected to be thrown in this test 
 * because the getRegexAssertions method returns the wrong class type. Because
 * the test method is called via reflection, there is no way to make this test
 * pass. Thus, it remains commented to serve as documentation.
 * 
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.2.
 */
//@RunWith(value=RegexTestSuite.class)
@Regex(value=".*")
public class TestWrongClassReturnedFromGetRegexAssertionsMethod {

	@RegexAssertions
	public static Object getRegexAssertions() {
		return null;
	}
	
//	@Test
	public void test() {}

}
