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

    /*
     * The first two tests show the most basic usage of the library.
     * You either assert that a string matches a regex or that it doesn't.
     */

	@Test
	public void test_a_string_that_matches_the_regex() {
		assertThat("foo", doesMatchRegex("fo.+"));
	}

	@Test
	public void test_a_string_that_does_not_match_the_regex() {
		assertThat("bar", doesNotMatchRegex("fo.+"));
	}

    /*
     * Another way to test is to assert that an AssertionError is thrown, which
     * is a way to do negative testing.
     */

	@Test(expected=AssertionError.class)
	public void test_a_string_that_does_not_match_the_regex__with_doesMatchRegex() {
		assertThat("bar", doesMatchRegex("bar.+"));
	}

	@Test(expected=AssertionError.class)
	public void test_a_string_that_does_match_the_regex__with_doesNotMatchRegex() {
		assertThat("foo", doesNotMatchRegex("fo.+"));
	}

}
