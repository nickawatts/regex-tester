package com.thewonggei.regexTester.assertions;

import static com.thewonggei.regexTester.hamcrest.MatchesAt.matchesAt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.regex.Pattern;


/**
 * @author Nick Watts
 * @since 0.2
 */
public class MatchAssertion extends AbstractRegexAssertion {
	private final int matchNumber;
	private final String testString;
	private final String inputString;
	private final boolean shouldItMatch;
	
	public MatchAssertion(final int matchNumber, final String testString, final String inputString, final boolean shouldItMatch) {
		this.matchNumber = matchNumber;
		this.testString = testString;
		this.inputString = inputString;
		this.shouldItMatch = shouldItMatch;
	}
	
	@Override
	public void doAssert(Pattern compiledRegex) {
		if( shouldItMatch ) {
			assertThat(testString, matchesAt(matchNumber, compiledRegex.matcher(inputString)));
		}
		else {
			assertThat(testString, not(matchesAt(matchNumber, compiledRegex.matcher(inputString))));
		}
	}

	/* (non-Javadoc)
	 * @see com.thewonggei.regexTester.assertions.RegexAssertion#getMethodName()
	 */
	@Override
	public String getMethodName() {
		String prefix = "regex_does_match";
		if( !shouldItMatch ) {
			prefix = "regex_does_not_match";
		}
		return String.format("%s_%s_match_in___%s", prefix, ordinal(matchNumber), escapeForMethodName(testString));
	}

	/**
	 * If the {@link #inputString} fields of the two objects being compared do
	 * not match, then this method returns the result of 
	 * <code>this.inputString.compareTo(compareThis.inputString)</code>. If the
	 * input strings are equal, the comparison moves on to the {@link #testString}
	 * fields. Again, if the testString fields are not equal the return value
	 * of this method is the result of calling 
	 * <code>this.testString.compareTo(compareThis.testString)</code>. If the
	 * testString fields are equal then the comparison moves on to the 
	 * {@link #matchNumber} fields. The values of -1, 0 or 1 are returned 
	 * depending on whether this.matchNumber is less than, equal to or greater
	 * than the value of compareThis.matchNumber.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RegexAssertion compareThis) {
		if( !(compareThis instanceof MatchAssertion) ) {
			throw new IllegalArgumentException("Can only compare against type MatchAssertion.");
		}
		MatchAssertion compareMA = (MatchAssertion)compareThis;
		int compareResult = 0;
		
		if( this.inputString.equals(compareMA.inputString) ) {
			if( this.testString.equals(compareMA.testString) ) {
				if( this.matchNumber < compareMA.matchNumber ) {
					compareResult = -1;
				}
				else if( this.matchNumber == compareMA.matchNumber ) {
					compareResult = 0;
				}
				else {
					compareResult = 1;
				}
			}
			else {
				compareResult = this.testString.compareTo(compareMA.testString);
			}
		}
		else {
			compareResult = this.inputString.compareTo(compareMA.inputString);
		}
		
		return compareResult;
	}
	
}
