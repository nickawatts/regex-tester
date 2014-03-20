package com.thewonggei.regexTester.hamcrest;

import static com.thewonggei.regexTester.hamcrest.MatchesAt.matchesAt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Nick Watts
 * @since 0.2
 *
 */
public class TestMatchesAt {
	private Pattern pattern;
	
	@Test
	public void test_matching_against_the_empty_string() {
		pattern = Pattern.compile(".*");
		assertThat("", matchesAt(1, pattern.matcher("")));
		pattern = Pattern.compile(".+");
		assertThat("", not(matchesAt(1, pattern.matcher(""))));
		assertThat("", not(matchesAt(1, pattern.matcher("abc"))));
		assertThat("", not(matchesAt(1, pattern.matcher("abc def 123 *&^%@"))));
		assertThat("", not(matchesAt(1, ".+", "")));
		assertThat("", not(matchesAt(1, ".+", "abc")));
		assertThat("", not(matchesAt(1, ".+", "abc def 123 *&^%@")));
	}
	
	@Test
	public void test_single_match_against_a_single_word() {
		pattern = Pattern.compile("te.+?");
		
		//Using a Pattern object
		assertThat("tes", matchesAt(1, pattern.matcher("test")));
		assertThat("tex", matchesAt(2, pattern.matcher("test text")));
		assertThat("tex", matchesAt(2, pattern.matcher("testtext")));
		
		//Using a string regex
		assertThat("tes", matchesAt(1, "te.+?", "test"));
		assertThat("tex", matchesAt(2, "te.+?", "test text"));
		assertThat("tex", matchesAt(2, "te.+?", "testtext"));
	}

	@Test
	public void test_single_match_against_a_two_words() {
		pattern = Pattern.compile("[tT]he man");
		
		//Using a Pattern object
		assertThat("the man", matchesAt(1, pattern.matcher("You are the man!")));
		assertThat("the man", not(matchesAt(1, pattern.matcher("You are The man!"))));
		assertThat("The man", matchesAt(1, pattern.matcher("The man walked down the street.")));
		assertThat("the man", not(matchesAt(1, pattern.matcher("The man walked down the street."))));

		//Using a string regex
		assertThat("the man", matchesAt(1, "[tT]he man", "You are the man!"));
		assertThat("the man", not(matchesAt(1, "[tT]he man", "You are The man!")));
		assertThat("The man", matchesAt(1, "[tT]he man", "The man walked down the street."));
		assertThat("the man", not(matchesAt(1, "[tT]he man", "The man walked down the street.")));
	}
	
	@Test
	public void test_multiple_matches_against_a_single_word() {
		pattern = Pattern.compile("te.+?");

		//Using a Pattern object
		assertThat("tea", matchesAt(1, pattern.matcher("tea time is when you drink tea, of course. I drink ten cups of green tea.")));
		assertThat("tea", matchesAt(2, pattern.matcher("tea time is when you drink tea, of course. I drink ten cups of green tea.")));
		assertThat("tea", not(matchesAt(3, pattern.matcher("tea time is when you drink tea, of course. I drink ten cups of green tea."))));
		assertThat("tea", matchesAt(4, pattern.matcher("tea time is when you drink tea, of course. I drink ten cups of green tea.")));

		//Using a string regex
		assertThat("tea", matchesAt(1, "te.+?", "tea time is when you drink tea, of course. I drink ten cups of green tea."));
		assertThat("tea", matchesAt(2, "te.+?", "tea time is when you drink tea, of course. I drink ten cups of green tea."));
		assertThat("tea", not(matchesAt(3, "te.+?", "tea time is when you drink tea, of course. I drink ten cups of green tea.")));
		assertThat("tea", matchesAt(4, "te.+?", "tea time is when you drink tea, of course. I drink ten cups of green tea."));
	}

}
