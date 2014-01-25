/**
 * 
 */
package com.thewonggei.regexTester;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thewonggei.regexTester.junit.RegexTestStringInfo;

/**
 * Load information about strings to test against a given regular
 * expression from a standard 
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Properties.html">
 * Java properties file</a> where the key is a string against which to test the 
 * regular expression and the value indicates whether or not a match should be 
 * found (either "true" or "false"). Currently, the boolean indicator in each
 * property's value must be either the string "true" or "false". XML properties 
 * files are not supported.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class TestRegexTestStringInfoFileLoader {
	private RegexTestStringInfoFileLoader rtsiLoader;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rtsiLoader = new RegexTestStringInfoFileLoader("src/test/resources/basic-test-strings-01.properties");
	}

	@Test(expected=FileNotFoundException.class)
	public void testFileNotFoundException() throws FileNotFoundException, IOException {
		rtsiLoader = new RegexTestStringInfoFileLoader("src/test/resources/null");
		rtsiLoader.load();
	}
	
	@Test
	public void test() throws FileNotFoundException, IOException {
		List<RegexTestStringInfo> list = rtsiLoader.load();
		assertEquals(5, list.size());
		for( RegexTestStringInfo rtsi : list ) {
			if( "foo".equals(rtsi.testString) ||
				"foofoo".equals(rtsi.testString) ||
				"The Foo Fighters are a rock band".equals(rtsi.testString)) {
				assertTrue(rtsi.shouldItMatch);
			}
			else {
				assertFalse(rtsi.shouldItMatch);
			}
		}
	}

}
