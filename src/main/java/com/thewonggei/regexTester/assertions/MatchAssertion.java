package com.thewonggei.regexTester.assertions;

import static com.thewonggei.regexTester.hamcrest.RegexMatches.doesMatchRegex;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.regex.Pattern;


/**
 * @author Nick Watts
 * @since 0.2
 */
public class MatchAssertion extends AbstractRegexAssertion {
	private final String inputString;
	
	public MatchAssertion(final String inputString) {
		this.inputString = inputString;
	}
	
	@Override
	public void doAssert(Pattern compiledRegex) {
		assertThat(inputString, doesMatchRegex(compiledRegex.toString()));
	}

	/* (non-Javadoc)
	 * @see com.thewonggei.regexTester.assertions.RegexAssertion#getMethodName()
	 */
	@Override
	public String getMethodName() {
		String prefix = "assert_regex_does_match";
		return String.format("%s_match_in___%s", prefix, escapeForMethodName(inputString));
	}

	/**
	 * Returns the result of <code>this.inputString.compareTo(compareThis.inputString)
	 * </code> when {@code compareThis} is an instance of {@link MatchAssertion}.
	 * A value of 1 is returned if {@code compareThis} is an instance of another
	 * class in the {@link RegexAssertion} hierarchy.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RegexAssertion compareThis) {
		if( !(compareThis instanceof MatchAssertion) &&
			(compareThis instanceof RegexAssertion)) 
		{
			//For simplicity, an instance of this class is always "more than"
			//an instance of another class in the same hierarchy.
			return 1;
		}
		else if( !(compareThis instanceof MatchAssertion) ) {
			throw new IllegalArgumentException("Can only compare against type NoMatchAssertion.");
		}
		else {
			return inputString.compareTo(((MatchAssertion)compareThis).inputString);
		}

	}
	
}
