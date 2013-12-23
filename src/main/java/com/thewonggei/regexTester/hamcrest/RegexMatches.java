package com.thewonggei.regexTester.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * A custom <a href="http://code.google.com/p/hamcrest/">Hamcrest matcher</a>
 * for asserting that a string either matches or does not match against a regular
 * expression. This is both an internal library convenience and a convenience for 
 * the user of this library. Depending on your approach to testing code and your
 * needs, you may wish to use only this Hamcrest matcher and ignore the JUnit and
 * TestNG extensions. It is up to you.
 * 
 * <h2>How to assert that a string matches a regular expression</h2>
 * <code>assertThat("foo", doesMatchRegex("fo.+"));</code>
 *  
 * <h2>How to assert that a string does not match a regular expression</h2>
 * <code>assertThat("bar", doesNotMatchRegex("fo.+"));</code>
 *   
 * @author Nick Watts
 * @since 0.1
 */
public class RegexMatches extends TypeSafeMatcher<String> {
	private String regex = "";
	private boolean shouldItMatch;
	
	/**
	 * This is used by the factory methods to seed the values
	 * necessary to perform the assertion in the {@link #matchesSafely(String)}
	 * method.
	 * 
	 * @param regex The regular expression against which to test a string for a match.
	 * @param shouldItMatch True if the regular expression should match against
	 * the test string, false otherwise.
	 */
	private RegexMatches(String regex, boolean shouldItMatch) {
		this.regex = regex;
		this.shouldItMatch = shouldItMatch;
	}
	
	/**
	 * Perform the assertion by determining if testString
	 * results in a match against the regular expression.
	 * 
	 * @param testString
	 */
	@Override
	public boolean matchesSafely(String testString) {
		boolean match = testString.matches(regex);
		
		//If the test string should result in a match then
		//the result is simply the result of the matches method.
		if( shouldItMatch ) {
			return match;
		}
		//Otherwise, the result is only true if the the result
		//of matches is false.
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

	/**
	 * Used with {@link org.hamcrest.MatcherAssert.assertThat} to assert that
	 * a string given as the first argument matches the given regular expression.
	 * 
	 * @param regex The regular expression used to match against the given test string.
	 * @return True if a match is found against the test string, false otherwise.
	 */
	@Factory
	public static <T> Matcher<String> doesMatchRegex(String regex) {
		return new RegexMatches(regex, true);
	}

	/**
	 * Used with {@link org.hamcrest.MatcherAssert.assertThat} to assert that
	 * a string given as the first argument does not match the given regular expression.
	 * 
	 * @param regex The regular expression used to match against the given test string.
	 * @return True if a match is not found against the test string, false otherwise.
	 */
	@Factory
	public static <T> Matcher<String> doesNotMatchRegex(String regex) {
		return new RegexMatches(regex, false);
	}

}
