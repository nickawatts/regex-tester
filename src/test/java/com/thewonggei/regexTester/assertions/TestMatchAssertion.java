package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestMatchAssertion {

	@Test
	public void test_compare_to_with_equal_objects() {
		MatchAssertion ma1 = new MatchAssertion(1, "test string", true);
		MatchAssertion ma2 = new MatchAssertion(1, "test string", true);
		assertEquals(0, ma1.compareTo(ma1));
		assertEquals(0, ma1.compareTo(ma2));
		assertEquals(0, ma2.compareTo(ma1));

		MatchAssertion ma3 = new MatchAssertion(20, "KMFDM Rocks!", false);
		MatchAssertion ma4 = new MatchAssertion(20, "KMFDM Rocks!", false);
		assertEquals(0, ma3.compareTo(ma3));
		assertEquals(0, ma3.compareTo(ma4));
		assertEquals(0, ma4.compareTo(ma3));
	}

	@Test
	public void test_compare_to_with_unequal_objects() {
		MatchAssertion ma1 = new MatchAssertion(1, "test string", true);
		MatchAssertion ma2 = new MatchAssertion(1, "test string", false);
		assertEquals(1, ma1.compareTo(ma2));
		assertEquals(-1, ma2.compareTo(ma1));
		
		ma1 = new MatchAssertion(1, "test string", true);
		ma2 = new MatchAssertion(2, "test string", true);
		assertEquals(-1, ma1.compareTo(ma2));
		assertEquals(1, ma2.compareTo(ma1));
		
		ma1 = new MatchAssertion(3, "abc", true);
		ma2 = new MatchAssertion(3, "def", true);
		assertEquals(-1, ma1.compareTo(ma2));
		assertEquals(1, ma2.compareTo(ma1));		
	}
	
}
