package com.thewonggei.regexTester;

import java.text.MessageFormat;

import com.thewonggei.regexTester.junit.Regex;

/**
 * @author Nick Watts
 * @since 0.1
 */
public class RegexAnnotationNotFoundException extends Exception {
	private static final long serialVersionUID = 7650540794943658962L;
	private final Class<?> testClass;
	
	public RegexAnnotationNotFoundException(Class<?> testClass) {
		super();
		this.testClass = testClass;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return MessageFormat.format("The {0} annotation was expected on test class {1} but was not found.", 
				Regex.class.getName(), testClass.getName());
	}
	
}
