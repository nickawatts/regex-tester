package com.thewonggei.regexTester;

import static org.junit.Assert.assertEquals;

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
		assertEquals(regexPojo.shouldItMatch, regexPojo.testString.matches(regex));
	}
	
}
