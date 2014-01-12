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
 * @author Nick Watts
 *
 */
public class TestRegexTestStringInfoFileLoader {
	private RegexTestStringInfoFileLoader rtsiLoader;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rtsiLoader = new RegexTestStringInfoFileLoader("src/test/resources/simple-regex-test-strings-sample.properties");
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
