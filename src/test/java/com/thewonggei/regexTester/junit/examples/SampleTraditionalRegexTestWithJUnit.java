package com.thewonggei.regexTester.junit.examples;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.thewonggei.regexTester.junit.RegexPojo;

@RunWith(value=Parameterized.class)
public class SampleTraditionalRegexTestWithJUnit {
	private RegexPojo rpojo;
	private String regex = "^xan.*";
	
	@Parameters
	public static Collection<RegexPojo[]> getTestParameters() {
		return Arrays.asList(new RegexPojo[][] {
				{new RegexPojo(true, "xander")},
				{new RegexPojo(false, "nick")},
				{new RegexPojo(true, "xantheman")},
				{new RegexPojo(false, "heisxan")},
				{new RegexPojo(false, "Xander")}
		});
	}
	
	public SampleTraditionalRegexTestWithJUnit(RegexPojo rpojo) {
		this.rpojo = rpojo;
	}
	
	@Test
	public void test() {
		assertEquals(rpojo.shouldItMatch, rpojo.testString.matches(regex));
	}

}
