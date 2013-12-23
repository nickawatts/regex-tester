package com.thewonggei.regexTester.junit.examples;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.thewonggei.regexTester.junit.RegexTestStringInfo;

@RunWith(value=Parameterized.class)
public class SampleTraditionalRegexTestWithJUnit {
	private RegexTestStringInfo rpojo;
	private String regex = "^xan.*";
	
	@Parameters
	public static Collection<RegexTestStringInfo[]> getTestParameters() {
		return Arrays.asList(new RegexTestStringInfo[][] {
				{new RegexTestStringInfo(true, "xander")},
				{new RegexTestStringInfo(false, "nick")},
				{new RegexTestStringInfo(true, "xantheman")},
				{new RegexTestStringInfo(false, "heisxan")},
				{new RegexTestStringInfo(false, "Xander")}
		});
	}
	
	public SampleTraditionalRegexTestWithJUnit(RegexTestStringInfo rpojo) {
		this.rpojo = rpojo;
	}
	
	@Test
	public void test() {
		assertEquals(rpojo.shouldItMatch, rpojo.testString.matches(regex));
	}

}
