/**
 * 
 */
package com.thewonggei.regexTester.assertions;

import static com.thewonggei.regexTester.hamcrest.CapturedGroupMatchesAt.matchesGroupAt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.regex.Pattern;

/**
 * @author Nick Watts
 * @since 0.2
 */
public class CapturedGroupAssertion extends AbstractRegexAssertion {
	private final int groupNumber;
	private final String testString;
	private final String inputString;
	private final boolean shouldItMatch;
	
	public CapturedGroupAssertion(final int groupNumber, final String testString, final String inputString, final boolean shouldItMatch) {
		this.groupNumber = groupNumber;
		this.testString = testString;
		this.inputString = inputString;
		this.shouldItMatch = shouldItMatch;
	}
	

	/* (non-Javadoc)
	 * @see com.thewonggei.regexTester.assertions.RegexAssertion#getMethodName()
	 */
	@Override
	public String getMethodName() {
		String prefix = "regex_does_match_captured_group";
		if( !shouldItMatch ) {
			prefix = "regex_does_not_match_captured_group";
		}
		return String.format("%s_%s_match_in___%s", prefix, ordinal(groupNumber), escapeForMethodName(testString));
	}

	/* (non-Javadoc)
	 * @see com.thewonggei.regexTester.assertions.RegexAssertion#doAssert(java.util.regex.Pattern)
	 */
	@Override
	public void doAssert(Pattern compiledRegex) {
		if( shouldItMatch ) {
			assertThat(testString, matchesGroupAt(groupNumber, compiledRegex.matcher(inputString)));
		}
		else {
			assertThat(testString, not(matchesGroupAt(groupNumber, compiledRegex.matcher(inputString))));
		}
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
		
		if( !(compareThis instanceof CapturedGroupAssertion) &&
			(compareThis instanceof RegexAssertion) ) 
		{
			//For simplicity, an instance of this class is always "less than"
			//an instance of another class in the same hierarchy.
			return -1;
		}
		else if( !(compareThis instanceof CapturedGroupAssertion) ) {
			throw new IllegalArgumentException("Can only compare against type CapturedGroupAssertion.");
		}
		
		CapturedGroupAssertion compareCGA = (CapturedGroupAssertion)compareThis;
		int compareResult = 0;
		
		if( this.inputString.equals(compareCGA.inputString) ) {
			if( this.testString.equals(compareCGA.testString) ) {
				if( this.groupNumber < compareCGA.groupNumber ) {
					compareResult = -1;
				}
				else if( this.groupNumber == compareCGA.groupNumber ) {
					compareResult = 0;
				}
				else {
					compareResult = 1;
				}
			}
			else {
				compareResult = this.testString.compareTo(compareCGA.testString);
			}
		}
		else {
			compareResult = this.inputString.compareTo(compareCGA.inputString);
		}
		
		return compareResult;
	}
	
}
