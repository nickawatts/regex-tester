package com.thewonggei.regexTester.junit;

import static com.thewonggei.regexTester.RegexMatches.doesMatchRegex;
import static com.thewonggei.regexTester.RegexMatches.doesNotMatchRegex;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.runners.model.Statement;

public class RegexTestStatement extends Statement {
	private String regex;
	private RegexPojo regexPojo;
	
	public RegexTestStatement(Statement wrappedStatement, String regex, RegexPojo regexPojo) {
		this.regex = regex;
		this.regexPojo = regexPojo;
	}

	@Override
	/**
	 * The body of the actual test method is ignored, 100%.
	 */
	public void evaluate() throws Throwable {
		//This is the implementation implied by this statement
		if( regexPojo.shouldItMatch ) {
			assertThat(regexPojo.testString, doesMatchRegex(regex));
		}
		else {
			assertThat(regexPojo.testString, doesNotMatchRegex(regex));
		}
	}
	
}
