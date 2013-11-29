package com.thewonggei.regexTester;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(value=RegexSuite.class)
@Regex(value="^xan.*")
public class SampleParameterizedRegexTest {
	
	@RegexTestStrings
	public static List<RegexPojo> getTestParameters() {
		return Arrays.asList(new RegexPojo[] {
				new RegexPojo(true, "xander"),
				new RegexPojo(false, "nick"),
				new RegexPojo(true, "xantheman"),
				new RegexPojo(false, "heisxan"),
				new RegexPojo(false, "Xander")
		});
	}
	
	@Test
	public void test() {}
}
