/**
 * 
 */
package com.thewonggei.regexTester;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.thewonggei.regexTester.hamcrest.RegexMatches.*;

/**
 * @author Nick Watts
 * @since 0.1
 */
public class TestRegexMatcher {

	@Test
	public void test_a_string_that_matches_the_regex() {
		assertThat("foo", doesMatchRegex("fo.+"));
	}

	@Test(expected=AssertionError.class)
	public void test_a_string_that_does_not_match_the_regex__with_doesMatchRegex() {
		assertThat("bar", doesMatchRegex("fo.+"));
	}

	@Test
	public void test_a_string_that_does_not_match_the_regex() {
		assertThat("bar", doesNotMatchRegex("fo.+"));
	}

	@Test(expected=AssertionError.class)
	public void test_a_string_that_does_match_the_regex__with_doesNotMatchRegex() {
		assertThat("foo", doesNotMatchRegex("fo.+"));
	}

}
