package com.thewonggei.regexTester.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.1.
 */
@RunWith(value=RegexTestSuite.class)
@Regex(value="[fF]oo.*")
@RegexTestStringsFile(propsFile="src/test/resources/basic-test-strings-01.properties")
public class BasicRegexTestWithPropsFile {

	@Test
	public void test() {
	}

}
