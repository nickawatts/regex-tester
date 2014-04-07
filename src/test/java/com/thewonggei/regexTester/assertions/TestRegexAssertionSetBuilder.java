package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestRegexAssertionSetBuilder {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		RegexAssertionSet assertions = new RegexAssertionSetBuilder().addMatchesAtAssertion(1, "com", true).build();
		assertEquals(1, assertions.size());
	}

}
