package com.thewonggei.regexTester;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class RegexRunner extends BlockJUnit4ClassRunner {
	private RegexPojo regexTest;
	
	public RegexRunner(Class<?> clazz, RegexPojo regexTest) throws Exception {
		super(clazz);
		this.regexTest = regexTest;
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		RegexTestStatement statement = new RegexTestStatement(super.methodInvoker(method, test),
				test.getClass().getAnnotation(Regex.class).value(),
				regexTest);
		return statement;
	}
		
    @Override 
    protected String getName() { 
    	if( regexTest.shouldItMatch ) {
    		return String.format("regex_should_match_%s", regexTest.testString);
    	}
    	else {
    		return String.format("regex_should_not_match_%s", regexTest.testString);
    	}
    }
    
    @Override// The name of the test method 
    protected String testName(final FrameworkMethod method) { 
    	if( regexTest.shouldItMatch ) {
    		return String.format("regex_should_match_%s", regexTest.testString);
    	}
    	else {
    		return String.format("regex_should_not_match_%s", regexTest.testString);
    	}
    } 
}
