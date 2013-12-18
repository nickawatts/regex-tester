package com.thewonggei.regexTester.junit;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.junit.Regex;
import com.thewonggei.regexTester.junit.RegexPojo;
import com.thewonggei.regexTester.junit.RegexTestSuite;
import com.thewonggei.regexTester.junit.RegexTestStrings;

/**
 * This test represents the most common usage of the
 * Regex Tester library where a single regex is specified
 * and a small list of test strings are given.
 * 
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.1.
 */
@RunWith(value=RegexTestSuite.class)
@Regex(value="^com.*")
public class BasicRegexTest {
	
	@RegexTestStrings
	public static List<RegexPojo> getTestParameters() {
		return Arrays.asList(new RegexPojo[] {
				new RegexPojo(true, "com"),
				new RegexPojo(true, "com.thewonggei"),
				new RegexPojo(true, "com.thewonggei.regexTester"),
				new RegexPojo(false, ".com.thewonggei"),
				new RegexPojo(false, "www.google.com")
		});
	}
	
	@Test
	public void test() {}
}
