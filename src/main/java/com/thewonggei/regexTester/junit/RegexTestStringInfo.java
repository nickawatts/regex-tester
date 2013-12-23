package com.thewonggei.regexTester.junit;

/**
 * A property bag for the values needed to perform matching of a test
 * string against a regular expression.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestStringInfo {
	/**
	 * True if the test string should result in a match against some
	 * regular expression, false otherwise.
	 */
	public boolean shouldItMatch;
	/**
	 * The string against which to test a regular expression for a match.
	 */
	public String testString;
	
	public RegexTestStringInfo(boolean shouldItMatch, String testString) {
		this.shouldItMatch = shouldItMatch;
		this.testString = testString;
	}
}
