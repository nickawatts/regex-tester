package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestSpecificMatchAssertion {

	@Test
	public void test_compare_to_with_equal_objects() {
		SpecificMatchAssertion ma1 = new SpecificMatchAssertion(1, "test string", "input string", true);
		SpecificMatchAssertion ma2 = new SpecificMatchAssertion(1, "test string", "input string", true);
		assertEquals(0, ma1.compareTo(ma1));
		assertEquals(0, ma1.compareTo(ma2));
		assertEquals(0, ma2.compareTo(ma1));

		SpecificMatchAssertion ma3 = new SpecificMatchAssertion(20, "KMFDM Rocks!", "The man on YouTube said KMFDM Rocks!", false);
		SpecificMatchAssertion ma4 = new SpecificMatchAssertion(20, "KMFDM Rocks!", "The man on YouTube said KMFDM Rocks!",false);
		assertEquals(0, ma3.compareTo(ma3));
		assertEquals(0, ma3.compareTo(ma4));
		assertEquals(0, ma4.compareTo(ma3));
	}

	@Test
	public void test_compare_to_with_unequal_objects() {
		SpecificMatchAssertion ma1 = new SpecificMatchAssertion(1, "test string", "input abc", true);
		SpecificMatchAssertion ma2 = new SpecificMatchAssertion(1, "test string", "input xyz", false);
		assertTrue(ma1.compareTo(ma2) < 0);
		assertTrue(ma2.compareTo(ma1) > 0);
		
		ma1 = new SpecificMatchAssertion(1, "test string", "input string", true);
		ma2 = new SpecificMatchAssertion(2, "test string", "input string", true);
		assertEquals(-1, ma1.compareTo(ma2));
		assertEquals(1, ma2.compareTo(ma1));
	}
	
}
