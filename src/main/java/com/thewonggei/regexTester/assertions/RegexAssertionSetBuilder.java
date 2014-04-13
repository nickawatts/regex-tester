package com.thewonggei.regexTester.assertions;


/**
 * @author Nick Watts
 * @since 0.2
 */
public class RegexAssertionSetBuilder {
	private RegexAssertionSet assertions;
	
	public RegexAssertionSetBuilder() {
		assertions = new RegexAssertionSet();
	}
	
	public RegexAssertionSetBuilder addMatchesAtAssertion(final int matchNumber, final String testString, final String inputString, final boolean shouldItMatch) {
		assertions.add(new MatchAssertion(matchNumber, testString, inputString, shouldItMatch));
		return this;
	}
	
	public RegexAssertionSet build() {
		return assertions;
	}
}
