package com.thewonggei.regexTester.hamcrest;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 *   
 * @author Nick Watts
 * @since 0.2
 */
public class MatchesAt extends TypeSafeMatcher<String> {
	private java.util.regex.Matcher regexMatcher;
	private int matchPosition = 0;
	private String textToBeMatched = null;
	
	private MatchesAt(int matchPosition, java.util.regex.Matcher regexMatcher) {
		this.regexMatcher = regexMatcher;
		this.matchPosition = matchPosition;
	}
	
	/* (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("%s to match for /%s/ at match position %d.",
				textToBeMatched, regexMatcher.pattern().pattern(), matchPosition));
	}

	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely(String item) {
		int matchCount = 0;
		boolean matchFound = false;
		textToBeMatched = item;
		
		while( regexMatcher.find() ) {
			matchCount++;
			if( matchCount == matchPosition && item.equals(regexMatcher.group()) ) {
				matchFound = true;
				break;
			}
		}
		
		return matchFound;
	}
	
	@Factory
	public static <T> Matcher<String> matchesAt(int matchPosition, java.util.regex.Matcher regexMatcher) {
		return new MatchesAt(matchPosition, regexMatcher);
	}

	@Factory
	public static <T> Matcher<String> matchesAt(int matchPosition, String regex, String testString) {
		Pattern p = Pattern.compile(regex);
		return new MatchesAt(matchPosition, p.matcher(testString));
	}

}
