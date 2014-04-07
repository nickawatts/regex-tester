package com.thewonggei.regexTester.junit;

import java.util.regex.Pattern;

import org.junit.runners.model.Statement;

import com.thewonggei.regexTester.assertions.RegexAssertion;

/**
 * Provides the implementation of the regular expression test method for JUnit.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestStatement extends Statement {
	private Pattern compiledRegex;
	private RegexAssertion assertion;
	
	public RegexTestStatement(Statement wrappedStatement, Pattern compiledRegex, RegexAssertion assertion) {
		this.compiledRegex = compiledRegex;
		this.assertion = assertion;
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
		assertion.doAssert(compiledRegex);
	}
	
}
