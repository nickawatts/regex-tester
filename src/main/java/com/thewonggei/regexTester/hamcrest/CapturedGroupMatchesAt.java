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
	private int matchNumber = 1;
	private int capturedGroupNumber = 0;
	private String textToBeMatched = null;
	private String capturedText = null;
	
	private CapturedGroupMatchesAt(int matchNumber, int capturedGroupNumber, java.util.regex.Matcher regexMatcher) {
		this.regexMatcher = regexMatcher;
		this.matchNumber = matchNumber;
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
		description.appendValue(modifiedTextToBeMatched);
		description.appendText(String.format(" to match for /%s/ at capture group #%d of match #%d.",
				regexMatcher.pattern().pattern(), capturedGroupNumber, matchNumber));
	}
	
	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely(String item,
			Description mismatchDescription) {
		if(capturedText == null ) {
			super.describeMismatchSafely("<no-match-found>", mismatchDescription);
		}
		else {
			super.describeMismatchSafely(capturedText, mismatchDescription);
		}
	}

	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely(String item) {
		boolean matchFound = false;
		textToBeMatched = item;
		
		/*
		 * Locate the desired match against which to check the capture group.
		 * Then see if the captured text is as expected or not.
		 */
		for( int i=1; regexMatcher.find(); i++ ) {
			if( i == matchNumber && capturedGroupNumber <= regexMatcher.groupCount() )  {
				capturedText = regexMatcher.group(capturedGroupNumber);
				if( textToBeMatched.equals(capturedText) ) {
					matchFound = true;
				}
				else {
					matchFound = false;
				}
				break;
			} 
		}
		
		return matchFound;
	}
	
	@Factory
	public static <T> Matcher<String> matchesGroupAt(int groupNumber, java.util.regex.Matcher regexMatcher) {
		return new CapturedGroupMatchesAt(1, groupNumber, regexMatcher);
	}

	@Factory
	public static <T> Matcher<String> matchesGroupAt(int matchNumber, int groupNumber, java.util.regex.Matcher regexMatcher) {
		return new CapturedGroupMatchesAt(matchNumber, groupNumber, regexMatcher);
	}

	@Factory
	public static <T> Matcher<String> matchesGroupAt(int matchNumber, int groupNumber, String regex, String testString) {
		Pattern p = Pattern.compile(regex);
		return new CapturedGroupMatchesAt(matchNumber, groupNumber, p.matcher(testString));
	}

	@Factory
	public static <T> Matcher<String> matchesGroupAt(int groupNumber, String regex, String testString) {
		Pattern p = Pattern.compile(regex);
		return new CapturedGroupMatchesAt(1, groupNumber, p.matcher(testString));
	}

}
