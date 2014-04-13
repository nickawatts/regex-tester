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
@Regex(value="[mM]ario")
public class BasicRegexTest {
	
	@RegexAssertions
	public static RegexAssertionSet getRegexAssertions() {
		String input = "Mario Andretti is a race car driver. There is a mario in the Super Mario Brothers line of video games.";
		
		return new RegexAssertionSetBuilder()
			.addMatchesAtAssertion(1, "Mario", input, true)
			.addMatchesAtAssertion(1, "mario", input, false)
			.addMatchesAtAssertion(2, "mario", input, true)
			.addMatchesAtAssertion(3, "Mario", input, true)
			.addMatchesAtAssertion(3, "mario", input, false)
			.build();
	}
	
	@Test
	public void test() {}
}
