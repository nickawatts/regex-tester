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
			.addMatchAssertion("0.0.0.0")
			.addMatchAssertion("127.0.0.1")
			.addMatchAssertion("255.255.255.255")
			.addNoMatchAssertion("256.256.256.256")
			.addNoMatchAssertion("A.B.C.D")
			.addNoMatchAssertion("-1.-1.-1.-1")
			.addMatchesAtAssertion(2, "192.168.1.1", "192.168.1.0 192.168.1.1", true)
			.addMatchesAtAssertion(1, "192.168.1.0", "192.168.1.0 192.168.1.1", true)
			.addMatchesAtAssertion(1, "192.168.1.1", "192.168.1.0 192.168.1.1", false)
			// IPv6 style address
			.addNoMatchAssertion("2001:0db8:0000:0000:0000:ff00:0042:8329")
			// MAC Address
			.addNoMatchAssertion("FF:FF:FF:FF:FF:FF")
			// Street Address :)
			.addNoMatchAssertion("123 North Melody Lane")
			.build();
	}
	
	@Test
	public void test() {
		/*
		 * A single, empty test method must exist to satisfy JUnit.
		 */
	}
}
