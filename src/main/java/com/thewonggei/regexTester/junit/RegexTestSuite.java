package com.thewonggei.regexTester.junit;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.thewonggei.regexTester.assertions.RegexAssertion;
import com.thewonggei.regexTester.assertions.RegexAssertionSet;
import com.thewonggei.regexTester.assertions.RegexAssertions;
import com.thewonggei.regexTester.assertions.RegexAssertionsForJUnitNotFoundException;

/**
 * A test runner implementation that is used to drive your regular expression test
 * suites when using JUnit. To use this test runner, you must specify the 
 * {@link RunWith} annotation in your JUnit test case like this:
 * 
 * <code>@RunWith(value=RegexTestSuite.class)</code>
 * 
 * Then use the {@link Regex} annotation as well as some form of the 
 * {@link RegexTestStrings} or {@link RegexAssertions} annotation.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestSuite extends Suite {
	private RegexAssertionSet regexAssertions;
	private static final List<Runner> NO_RUNNERS = Collections.<Runner>emptyList();
	private final ArrayList<Runner> runners = new ArrayList<Runner>();
	private final Pattern compiledRegex;
	
	/**
     * Only called reflectively. Do not use programmatically.
     */
    public RegexTestSuite(Class<?> klass) throws Throwable {
        super(klass, NO_RUNNERS);
        regexAssertions = pullRegexAssertionSetFromTestClass();
        compiledRegex = Pattern.compile(getTestClass().getJavaClass().getAnnotation(Regex.class).value());
        createRunnersForParameters();
    }

    private void createRunnersForParameters() throws Exception {
    	int count = 1;
    	for( RegexAssertion assertion : regexAssertions ) {
    		RegexRunner runner = new RegexRunner(getTestClass().getJavaClass(), compiledRegex, assertion, count++);
    		runners.add(runner);
    	}
    }
    
    @Override
    protected List<Runner> getChildren() {
        return runners;
    }
    
    /**
     * Execute the method from the test class annotated with 
     * {@link RegexAssertionSet} to obtain the list of {@link RegexAssertion}s
     * that are to be tested by the generated test cases.
     * 
     * @return The set of {@link RegexAssertion}s  that are to be tested by the 
     * generated test cases.
     * @throws Exception
     * @throws Throwable
     */
    private RegexAssertionSet pullRegexAssertionSetFromTestClass() throws Exception, Throwable {
    	Object shouldBeRegexAssertionSet = null;
   		shouldBeRegexAssertionSet = getRegexAssertionsMethod().invokeExplosively(null);
   		
   		if( shouldBeRegexAssertionSet instanceof RegexAssertionSet ) {
   			return (RegexAssertionSet) shouldBeRegexAssertionSet;
   		} else {
            String className = getTestClass().getName();
            String methodName = getRegexAssertionsMethod().getName();
            String message = MessageFormat.format(
                    "{0}.{1}() must return an object of type RegexAssertionSet.",
                    className, methodName);
            throw new Exception(message);
        }
    }
    
    /**
     * @return The first instance of a public, static method annotated with 
     * {@link RegexAssertions} found in the test class. The method name does 
     * not matter.
     * 
     * @throws Exception If the desired method signature is not found.
     */
    private FrameworkMethod getRegexAssertionsMethod() throws Exception {
        List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(
                RegexAssertions.class);
        for (FrameworkMethod each : methods) {
            if (JUnitUtils.isMethodStatic(each) && 
            	JUnitUtils.isMethodPublic(each) ) 
            {
                return each;
            }
        }

        throw new RegexAssertionsForJUnitNotFoundException(getTestClass().getClass());
    }

}

/**
 * 
 * @author Nick Watts
 * @since 0.1
 */
class RegexRunner extends BlockJUnit4ClassRunner {
	private RegexAssertion regexAssertion;
	private Pattern compiledRegex;
	private int suiteNumber = 0;
	
	public RegexRunner(Class<?> clazz, Pattern compiledRegex, RegexAssertion regexAssertion, int suiteNumber) throws Exception {
		super(clazz);
		this.compiledRegex = compiledRegex;
		this.regexAssertion = regexAssertion;
		this.suiteNumber = suiteNumber;
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		RegexTestStatement statement = null;
			statement = new RegexTestStatement(super.methodInvoker(method, test),
					compiledRegex, regexAssertion);
		return statement;
	}
		
    @Override 
    protected String getName() { 
    	return String.format("%s_suite_%d", regexAssertion.getClass().getSimpleName(), suiteNumber);
    }
    
    @Override// The name of the test method 
    protected String testName(final FrameworkMethod method) {
    	return regexAssertion.getMethodName();
    } 
}

