package com.thewonggei.regexTester.junit;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.thewonggei.regexTester.RegexTestStringsNotFoundException;

/**
 * A test runner implementation that is used to drive your regular expression test
 * suites when using JUnit. To use this test runner, you must specify the 
 * {@link RunWith} annotation in your JUnit test case like this:
 * 
 * <code>@RunWith(value=RegexTestSuite.class)</code>
 * 
 * Then use the {@link Regex} annotation as well as some form of the {@link RegexTestStrings}
 * annotation.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestSuite extends Suite {
	private List<RegexTestStringInfo> regexTests;
	private static final List<Runner> NO_RUNNERS = Collections.<Runner>emptyList();
	private final ArrayList<Runner> runners = new ArrayList<Runner>();
	
	/**
     * Only called reflectively. Do not use programmatically.
     */
    public RegexTestSuite(Class<?> klass) throws Throwable {
        super(klass, NO_RUNNERS);
        regexTests = pullRegexTestStringsFromTestClass();
        createRunnersForParameters();
    }

    private void createRunnersForParameters() throws Exception {
        for (RegexTestStringInfo rp : regexTests) {
            RegexRunner runner = new RegexRunner(getTestClass().getJavaClass(), rp);
            runners.add(runner);
        }
    }
    
    @Override
    protected List<Runner> getChildren() {
        return runners;
    }
    
    @SuppressWarnings("unchecked")
    private List<RegexTestStringInfo> pullRegexTestStringsFromTestClass() throws Exception, Throwable {
        Object regexTestStrings = getRegexTestStringsMethod().invokeExplosively(null);
        if (regexTestStrings instanceof List<?>) {
            return (List<RegexTestStringInfo>) regexTestStrings;
        } else {
            String className = getTestClass().getName();
            String methodName = getRegexTestStringsMethod().getName();
            String message = MessageFormat.format(
                    "{0}.{1}() must return an object of type List<RegexTestStringInfo>.",
                    className, methodName);
            throw new Exception(message);
        }
    }

    private FrameworkMethod getRegexTestStringsMethod() throws Exception {
        List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(
                RegexTestStrings.class);
        for (FrameworkMethod each : methods) {
            if (each.isStatic() && each.isPublic() ) {
                return each;
            }
        }

        throw new RegexTestStringsNotFoundException(getTestClass().getClass());
    }

}

/**
 * 
 * @author Nick Watts
 * @since 0.1
 */
class RegexRunner extends BlockJUnit4ClassRunner {
	private RegexTestStringInfo regexTest;
	
	public RegexRunner(Class<?> clazz, RegexTestStringInfo regexTest) throws Exception {
		super(clazz);
		this.regexTest = regexTest;
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		RegexTestStatement statement = null;
			statement = new RegexTestStatement(super.methodInvoker(method, test),
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

