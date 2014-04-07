package com.thewonggei.regexTester.assertions;

import java.text.MessageFormat;


/**
 * @author Nick Watts
 * @since 0.2
 */
public class RegexAssertionsForJUnitNotFoundException extends Exception {
	private static final long serialVersionUID = 7604765870749686689L;
	private final Class<?> testClass;

	public RegexAssertionsForJUnitNotFoundException(Class<?> testClass) {
		super();
		this.testClass = testClass;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return MessageFormat.format("No public static method annotated with @RegexAssertions on class {0} was found.", 
				testClass.getName());
	}
	
}
