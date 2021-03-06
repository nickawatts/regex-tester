package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestMatchAssertion {

	@Test
	public void test_compare_to_with_equal_objects() {
		MatchAssertion ma1 = new MatchAssertion("input string");
		MatchAssertion ma2 = new MatchAssertion("input string");
		assertEquals(0, ma1.compareTo(ma1));
		assertEquals(0, ma1.compareTo(ma2));
		assertEquals(0, ma2.compareTo(ma1));
	}

	@Test
	public void test_compare_to_with_unequal_objects() {
		MatchAssertion ma3 = new MatchAssertion("input string 1");
		MatchAssertion ma4 = new MatchAssertion("input string 2");
		assertEquals(0, ma3.compareTo(ma3));
		assertEquals(-1, ma3.compareTo(ma4));
		assertEquals(1, ma4.compareTo(ma3));
	}
	
}
