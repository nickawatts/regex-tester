package com.thewonggei.regexTester.assertions;

import java.util.regex.Pattern;

/**
 * @author Nick Watts
 * @since 0.2
 */
public interface RegexAssertion {

	public void doAssert(Pattern compiledRegex);	
	public String getMethodName();
	
}
