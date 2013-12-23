package com.thewonggei.regexTester.junit;

import static com.thewonggei.regexTester.hamcrest.RegexMatches.doesMatchRegex;
import static com.thewonggei.regexTester.hamcrest.RegexMatches.doesNotMatchRegex;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.runners.model.Statement;

/**
 * Provides the implementation of the regular expression test method for JUnit.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestStatement extends Statement {
	private String regex;
	private RegexTestStringInfo regexPojo;
	
	public RegexTestStatement(Statement wrappedStatement, String regex, RegexTestStringInfo regexPojo) {
		this.regex = regex;
		this.regexPojo = regexPojo;
	}

	/**
	 * The body of the actual test method is ignored, 100%.
	 * In place of whatever test method code may be supplied 
	 * (there should not be any) a test string, supplied by
	 * the method in the unit test annotated with 
	 * {@link RegexTestStrings}, is tested against the regular
	 * expression supplied by the {@link Regex} annotation.
	 */
	@Override
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
