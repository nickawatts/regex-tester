package com.thewonggei.regexTester.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.thewonggei.regexTester.assertions.RegexAssertionSet;
import com.thewonggei.regexTester.assertions.RegexAssertionSetBuilder;
import com.thewonggei.regexTester.assertions.RegexAssertions;

/**
 * 
 * @author Nick Watts
 * @since This style of library usage has been available since version 0.2.
 */
@RunWith(value=RegexTestSuite.class)
//From http://www.regular-expressions.info/examples.html
@Regex(value="\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b")
public class TestIPAddressRegex {
	
	@RegexAssertions
	public static RegexAssertionSet getRegexAssertions() {
		/*
		 * A public static method with the name getRegexAssertions and annotated 
		 * with @RegexAssertions defines the things to assert about the regex.
		 */
		return new RegexAssertionSetBuilder()
			.addMatchesAtAssertion(1, "0.0.0.0", "0.0.0.0", true)
			.addMatchesAtAssertion(1, "127.0.0.1", "127.0.0.1", true)
			.addMatchesAtAssertion(1, "255.255.255.255", "255.255.255.255", true)
			.addMatchesAtAssertion(1, "256.256.256.256", "256.256.256.256", false)
			.addMatchesAtAssertion(1, "A.B.C.D", "A.B.C.D", false)
			.addMatchesAtAssertion(2, "192.168.1.1", "192.168.1.0 192.168.1.1", true)
			.addMatchesAtAssertion(1, "192.168.1.0", "192.168.1.0 192.168.1.1", true)
			.addMatchesAtAssertion(1, "192.168.1.1", "192.168.1.0 192.168.1.1", false)
			.build();
	}
	
	@Test
	public void test() {
		/*
		 * A single, empty test method must exist to satisfy JUnit.
		 */
	}
}
