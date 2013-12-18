package com.thewonggei.regexTester;

import java.text.MessageFormat;


/**
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestStringsNotFoundException extends Exception {
	private static final long serialVersionUID = -6669614791287537476L;
	private final Class<?> testClass;

	public RegexTestStringsNotFoundException(Class<?> testClass) {
		super();
		this.testClass = testClass;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return MessageFormat.format("No public static method annotated with @RegexTestStrings on class {0} was found.", 
				testClass.getName());
	}
	
}
