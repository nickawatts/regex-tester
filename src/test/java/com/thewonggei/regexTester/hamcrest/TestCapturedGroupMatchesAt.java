package com.thewonggei.regexTester.hamcrest;

import static com.thewonggei.regexTester.hamcrest.CapturedGroupMatchesAt.matchesGroupAt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestCapturedGroupMatchesAt {
	private Pattern pattern;
	
	@Test
	public void test_matching_against_group_zero() {
		/*
		 * Group zero is the entire match, so no capture groups need be
		 * specified in order to produce a positive match in this case.
		 */
		pattern = Pattern.compile(".*");
		assertThat("", matchesGroupAt(0, pattern.matcher("")));		
		assertThat("abc", matchesGroupAt(0, pattern.matcher("abc")));		
		assertThat("a", not(matchesGroupAt(0, pattern.matcher("abc"))));		
		pattern = Pattern.compile("abc(def)g");
		assertThat("abcdefg", matchesGroupAt(0, pattern.matcher("abcdefg")));
	}
	
	@Test
	public void test_matching_against_non_zero_groups() {
		//1 capture group in regex
		pattern = Pattern.compile("abc(def)g");
		assertThat("def", matchesGroupAt(1, pattern.matcher("abcdefg")));
		
		//2 capture groups in regex
		pattern = Pattern.compile("abc(def)ghi(jk)lmn");
		assertThat("def", matchesGroupAt(1, pattern.matcher("abcdefghijklmn")));
		assertThat("def", not(matchesGroupAt(1, pattern.matcher("xyz"))));
		assertThat("def", not(matchesGroupAt(2, pattern.matcher("xyz"))));
		assertThat("def", not(matchesGroupAt(2, pattern.matcher("abcdefghijklmn"))));
		assertThat("jk", matchesGroupAt(2, pattern.matcher("abcdefghijklmn")));
		assertThat("jk", not(matchesGroupAt(1, pattern.matcher("xyz"))));
		assertThat("jk", not(matchesGroupAt(2, pattern.matcher("xyz"))));
		assertThat("jk", not(matchesGroupAt(1, pattern.matcher("abcdefghijklmn"))));
	}
	
}
