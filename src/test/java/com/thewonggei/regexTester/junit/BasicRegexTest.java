package com.thewonggei.regexTester.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.assertions.RegexAssertionSet;
import com.thewonggei.regexTester.assertions.RegexAssertionSetBuilder;
import com.thewonggei.regexTester.assertions.RegexAssertions;

/**
 * This test represents the most common usage of the
 * Regex Tester library where a single regex is specified
 * and a small list of test strings are given.
 * 
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.2.
 */
@RunWith(value=RegexTestSuite.class)
@Regex(value="^abc(def)g")
public class BasicRegexTest {
	
	@RegexAssertions
	public static RegexAssertionSet getRegexAssertions() {
		String input = "abcdefg";
		
		return new RegexAssertionSetBuilder()
			.addMatchesAtAssertion(1, "abcdefg", input, true)
			.addMatchesAtAssertion(1, "ABCDEFG", input, false)
			.addGroupMatchesAtAssertion(1, "def", input, true)
			.build();
	}
	
	@Test
	public void test() {}
}
