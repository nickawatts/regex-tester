/**
 * 
 */
package com.thewonggei.regexTester;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Nick Watts
 *
 */
public class RegexMatches extends TypeSafeMatcher<String> {
	private String regex = "";
	private boolean shouldItMatch;
	
	private RegexMatches(String regex, boolean shouldItMatch) {
		this.regex = regex;
		this.shouldItMatch = shouldItMatch;
	}
	
	@Override
	public boolean matchesSafely(String testString) {
		boolean match = testString.matches(regex);
		if( shouldItMatch ) {
			return match;
		}
		else {
			if( !match ) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public void describeTo(Description description) {
		if( shouldItMatch ) {
			description.appendText(" test string to match against /"+regex+"/ ");
		}
		else {
			description.appendText("test string to NOT match against /"+regex+"/ ");
		}
	}

	@Factory
	public static <T> Matcher<String> doesMatchRegex(String regex) {
		return new RegexMatches(regex, true);
	}

	@Factory
	public static <T> Matcher<String> doesNotMatchRegex(String regex) {
		return new RegexMatches(regex, false);
	}

}
