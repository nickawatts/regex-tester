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
		 * A public static method named getRegexAssertions and annotated 
		 * with @RegexAssertions defines the things to assert about the regex.
		 */
		return new RegexAssertionSetBuilder()
			.addMatchAssertion("The Journal of Physics")
			.addNoMatchAssertion("The Journal of Physics Letters")
			.addMatchAssertion("The Journal of Chemistry")
			.addGroupMatchesAtAssertion(1, "Physics", "The Journal of Physics", true)
			.addGroupMatchesAtAssertion(1, "Physics", "The Journal of Chemistry", false)
			.build();
	}
	
	@Test
	public void test() {
		/*
		 * A single, empty test method must exist to satisfy JUnit.
		 */
	}
}
