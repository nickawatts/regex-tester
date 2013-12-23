package com.thewonggei.regexTester.junit;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.junit.Regex;
import com.thewonggei.regexTester.junit.RegexTestStringInfo;
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
	public static List<RegexTestStringInfo> getTestParameters() {
		return Arrays.asList(new RegexTestStringInfo[] {
				new RegexTestStringInfo(true, "com"),
				new RegexTestStringInfo(true, "com.thewonggei"),
				new RegexTestStringInfo(true, "com.thewonggei.regexTester"),
				new RegexTestStringInfo(false, ".com.thewonggei"),
				new RegexTestStringInfo(false, "www.google.com")
		});
	}
	
	@Test
	public void test() {}
}
