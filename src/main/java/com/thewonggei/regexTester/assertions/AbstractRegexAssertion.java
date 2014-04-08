package com.thewonggei.regexTester.assertions;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public abstract class AbstractRegexAssertion implements RegexAssertion, Comparable<RegexAssertion> {

	/**
	 * Convert an integer into the plain-English equivalent (e.g. 1st, 2nd, 3rd,
	 * etc...).
	 * 
	 * @param i The integer to convert.
	 * @return The ordinal string representation.
	 * @see http://stackoverflow.com/a/20932775/1786836 (origin of this method).
	 */
	protected String ordinal(int i) {
	    return i % 100 == 11 || i % 100 == 12 || i % 100 == 13 ? i + "th" : i + new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"}[i % 10];
	}
	
	protected String escapeForMethodName(String text) {
		return StringEscapeUtils.escapeJava(text);
	}
	
}
