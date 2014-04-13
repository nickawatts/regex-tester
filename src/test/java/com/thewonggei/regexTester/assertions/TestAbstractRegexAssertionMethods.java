package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestAbstractRegexAssertionMethods {
	private MatchAssertion ma;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ma = new MatchAssertion(1, "test string", "input string", true);
	}

	@Test
	public void test_ordinal() {
		assertEquals("1st", ma.ordinal(1));
		assertEquals("2nd", ma.ordinal(2));
		assertEquals("3rd", ma.ordinal(3));
		assertEquals("4th", ma.ordinal(4));
		assertEquals("5th", ma.ordinal(5));
		assertEquals("6th", ma.ordinal(6));
		assertEquals("7th", ma.ordinal(7));
		assertEquals("8th", ma.ordinal(8));
		assertEquals("9th", ma.ordinal(9));
		assertEquals("10th", ma.ordinal(10));
		assertEquals("11th", ma.ordinal(11));
		assertEquals("12th", ma.ordinal(12));
		assertEquals("13th", ma.ordinal(13));
	}

}
