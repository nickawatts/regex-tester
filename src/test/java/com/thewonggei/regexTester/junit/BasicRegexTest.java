package com.thewonggei.regexTester.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.assertions.RegexAssertionSet;
import com.thewonggei.regexTester.assertions.RegexAssertionSetBuilder;
import com.thewonggei.regexTester.assertions.RegexAssertions;

/**
 * This test represents the most basic usage of the Regex Tester library where 
 * assertions are made against both matches and capture groups. 
 * 
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.2.
 */
@RunWith(value=RegexTestSuite.class)
@Regex(value="The\\s+Journal\\s+of\\s+(\\w+)")
public class BasicRegexTest {
	
	@RegexAssertions
	public static RegexAssertionSet getRegexAssertions() {
		/*
		 * A public static method with the name getRegexAssertions and annotated 
		 * with @RegexAssertions defines the things to assert about the regex.
		 */
		return new RegexAssertionSetBuilder()
			.addMatchesAtAssertion(1, "The Journal of Physics", "The Journal of Physics", true)
			.addMatchesAtAssertion(1, "The Journal of Physics Letters", "The Journal of Physics Letters", false)
			.addMatchesAtAssertion(1, "The Journal of Chemistry", "The Journal of Physics", false)
			.addGroupMatchesAtAssertion(1, "Physics", "The Journal of Physics", true)
			.build();
	}
	
	@Test
	public void test() {
		/*
		 * A single, empty test method must exist to satisfy JUnit.
		 */
	}
}
