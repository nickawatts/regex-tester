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
public class CapturedGroupMatchesAt extends TypeSafeMatcher<String> {
	private java.util.regex.Matcher regexMatcher;
	private int capturedGroupNumber = 0;
	private String textToBeMatched = null;
	
	private CapturedGroupMatchesAt(int capturedGroupNumber, java.util.regex.Matcher regexMatcher) {
		this.regexMatcher = regexMatcher;
		this.capturedGroupNumber = capturedGroupNumber;
	}
	
	/* (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		String modifiedTextToBeMatched = textToBeMatched;
		if( modifiedTextToBeMatched.trim().isEmpty() ) {
			modifiedTextToBeMatched = "<empty-string>";
		}
		description.appendText(String.format("%s to match for /%s/ at group number %d.",
				modifiedTextToBeMatched, regexMatcher.pattern().pattern(), capturedGroupNumber));
	}

	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely(String item) {
		boolean matchFound = false;
		textToBeMatched = item;
		
		/*
		 * If there are no matches then there are no captured groups against
		 * which to compare.
		 */
		if( regexMatcher.matches() && capturedGroupNumber <= regexMatcher.groupCount() ) {
			String capturedText = regexMatcher.group(capturedGroupNumber);
			if( textToBeMatched.equals(capturedText) ) {
				matchFound = true;
			}
			else {
				matchFound = false;
			}
		} 
		
		return matchFound;
	}
	
	@Factory
	public static <T> Matcher<String> matchesGroupAt(int groupNumber, java.util.regex.Matcher regexMatcher) {
		return new CapturedGroupMatchesAt(groupNumber, regexMatcher);
	}

	@Factory
	public static <T> Matcher<String> matchesGroupAt(int groupNumber, String regex, String testString) {
		Pattern p = Pattern.compile(regex);
		return new CapturedGroupMatchesAt(groupNumber, p.matcher(testString));
	}

}
