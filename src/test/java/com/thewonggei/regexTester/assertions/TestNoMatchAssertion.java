package com.thewonggei.regexTester.assertions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestNoMatchAssertion {

	@Test
	public void test_compare_to_with_equal_objects() {
		NoMatchAssertion ma1 = new NoMatchAssertion("input string");
		NoMatchAssertion ma2 = new NoMatchAssertion("input string");
		assertEquals(0, ma1.compareTo(ma1));
		assertEquals(0, ma1.compareTo(ma2));
		assertEquals(0, ma2.compareTo(ma1));
	}

	@Test
	public void test_compare_to_with_unequal_objects() {
		NoMatchAssertion ma3 = new NoMatchAssertion("input string 1");
		NoMatchAssertion ma4 = new NoMatchAssertion("input string 2");
		assertEquals(0, ma3.compareTo(ma3));
		assertEquals(-1, ma3.compareTo(ma4));
		assertEquals(1, ma4.compareTo(ma3));
	}
	
}
