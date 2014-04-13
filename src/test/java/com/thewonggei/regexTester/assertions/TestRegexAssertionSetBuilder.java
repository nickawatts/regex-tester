package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestRegexAssertionSetBuilder {

	@Test
	public void test_no_assertions_added() {
		RegexAssertionSet assertions = new RegexAssertionSetBuilder().build();
		assertEquals(0, assertions.size());
	}
	
	@Test
	public void test_simplest_scenario_with_one_assertion() {
		RegexAssertionSet assertions = new RegexAssertionSetBuilder().addMatchesAtAssertion(1, "com", "com.thewonggei", true).build();
		assertEquals(1, assertions.size());
	}

}
