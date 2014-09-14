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
	public void test_no_match_at_all() {
		assertThat("1", not(matchesGroupAt(1, "[a-z]+", "abc")));
	}
	
	@Test
	public void test_desired_match_group_out_of_bounds() {
		pattern = Pattern.compile("^\\$(\\d{2})\\d+");
		/*
		 * There is only one group, so asking for a match at the second group
		 * should result in false.
		 */
		assertThat("22", not(matchesGroupAt(2, pattern.matcher("$22.96"))));

		//Now without the compiled Pattern
		assertThat("22", not(matchesGroupAt(2, "^\\$(\\d{2})\\d+", "$22.96")));
	}
	
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

	
		//Now without the compiled Pattern
		assertThat("", matchesGroupAt(0, ".*", ""));		
		assertThat("abc", matchesGroupAt(0, ".*", "abc"));		
		assertThat("a", not(matchesGroupAt(0, ".*", "abc")));		
		assertThat("abcdefg", matchesGroupAt(0, "abc(def)g", "abcdefg"));
	}
	
	@Test
	public void test_matching_against_non_zero_groups() {
		//1 capture group in regex
		pattern = Pattern.compile("abc(def)g");
		assertThat("def", matchesGroupAt(1, pattern.matcher("abcdefg")));
		
		//Now without the compiled Pattern
		assertThat("def", matchesGroupAt(1, "abc(def)g", "abcdefg"));

		
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

	
		//Now without the compiled Pattern
		assertThat("def", matchesGroupAt(1, "abc(def)ghi(jk)lmn", "abcdefghijklmn"));
		assertThat("def", not(matchesGroupAt(1, "abc(def)ghi(jk)lmn", "xyz")));
		assertThat("def", not(matchesGroupAt(2, "abc(def)ghi(jk)lmn", "xyz")));
		assertThat("def", not(matchesGroupAt(2, "abc(def)ghi(jk)lmn", "abcdefghijklmn")));
		assertThat("jk", matchesGroupAt(2, "abc(def)ghi(jk)lmn", "abcdefghijklmn"));
		assertThat("jk", not(matchesGroupAt(1,"abc(def)ghi(jk)lmn",  "xyz")));
		assertThat("jk", not(matchesGroupAt(2, "abc(def)ghi(jk)lmn", "xyz")));
		assertThat("jk", not(matchesGroupAt(1, "abc(def)ghi(jk)lmn", "abcdefghijklmn")));
	}
	
}
