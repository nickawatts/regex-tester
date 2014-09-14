package com.thewonggei.regexTester;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestRegeAnnotationNotFoundException {

	@Test
	public void test() {
		RegexAnnotationNotFoundException ex = new RegexAnnotationNotFoundException(this.getClass());
		assertEquals("The com.thewonggei.regexTester.junit.Regex annotation was expected on test class com.thewonggei.regexTester.TestRegeAnnotationNotFoundException but was not found.", ex.getMessage());
	}

}
