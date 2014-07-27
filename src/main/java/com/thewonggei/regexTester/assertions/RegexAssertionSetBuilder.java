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
		assertions.add(new SpecificMatchAssertion(matchNumber, testString, inputString, shouldItMatch));
		return this;
	}
	
	public RegexAssertionSetBuilder addMatchAssertion(final String inputString) {
		assertions.add(new MatchAssertion(inputString));
		return this;
	}

	public RegexAssertionSetBuilder addNoMatchAssertion(final String inputString) {
		assertions.add(new NoMatchAssertion(inputString));
		return this;
	}

	public RegexAssertionSetBuilder addGroupMatchesAtAssertion(final int matchNumber, final String testString, final String inputString, final boolean shouldItMatch) {
		assertions.add(new CapturedGroupAssertion(matchNumber, testString, inputString, shouldItMatch));
		return this;
	}
	
	public RegexAssertionSet build() {
		return assertions;
	}
}
