package com.thewonggei.regexTester.hamcrest;

import static com.thewonggei.regexTester.hamcrest.RegexMatches.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestRegexMatches {
	
	@Test
	public void test_does_not_match_regex() {
		assertThat("abc", doesNotMatchRegex("123"));
	}
	
	@Test
	public void test_does_match_regex() {
		assertThat("abc", doesMatchRegex("[a-c]+"));
	}
	
}
