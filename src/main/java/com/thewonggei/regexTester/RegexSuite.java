package com.thewonggei.regexTester;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;

public class RegexSuite extends Suite {
	private List<RegexPojo> regexTests;
	private static final List<Runner> NO_RUNNERS = Collections.<Runner>emptyList();
	private final ArrayList<Runner> runners = new ArrayList<Runner>();
	
	/**
     * Only called reflectively. Do not use programmatically.
     */
    public RegexSuite(Class<?> klass) throws Throwable {
        super(klass, NO_RUNNERS);
        regexTests = pullRegexTestStringsFromTestClass();
        createRunnersForParameters();
    }

    private void createRunnersForParameters() throws Exception {
        for (RegexPojo rp : regexTests) {
            RegexRunner runner = new RegexRunner(getTestClass().getJavaClass(), rp);
            runners.add(runner);
        }
    }
    
    @Override
    protected List<Runner> getChildren() {
        return runners;
    }
    
    @SuppressWarnings("unchecked")
    private List<RegexPojo> pullRegexTestStringsFromTestClass() throws Exception, Throwable {
        Object regexTestStrings = getRegexTestStringsMethod().invokeExplosively(null);
        if (regexTestStrings instanceof List<?>) {
            return (List<RegexPojo>) regexTestStrings;
        } else {
            String className = getTestClass().getName();
            String methodName = getRegexTestStringsMethod().getName();
            String message = MessageFormat.format(
                    "{0}.{1}() must return an object of type List<RegexPojo>.",
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

        throw new Exception("No public static method annotated with @RegexTestStrings on class "
                + getTestClass().getName());
    }

}
