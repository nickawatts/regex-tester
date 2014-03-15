package com.thewonggei.regexTester.hamcrest;

import static com.thewonggei.regexTester.hamcrest.MatchesAt.matchesAt;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestMatchesAt {
	private static Pattern pattern;
	
	@BeforeClass
	public static void beforeClass() {
		pattern = Pattern.compile("te.+?");
	}
	
	@Test
	public void testWithRegexMatcher() {
		assertThat("tes", matchesAt(1, pattern.matcher("test")));
		assertThat("tex", matchesAt(2, pattern.matcher("test text")));
	}

	@Test
	public void testWithRegexString() {
		assertThat("tes", matchesAt(1, "te.+?", "test"));
		assertThat("tex", matchesAt(2, "te.+?", "test text"));
	}

}
