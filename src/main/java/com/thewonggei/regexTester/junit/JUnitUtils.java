package com.thewonggei.regexTester.junit;

import java.lang.reflect.Modifier;

import org.junit.runners.model.FrameworkMethod;

/**
 * A collection of utilities that fills gaps in JUnits functionality. In many
 * cases, the provided methods may implement functionality available in newer
 * versions of JUnit but not in earlier versions for which the regex-tester
 * provides support. 
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class JUnitUtils {

	/**
	 * An implementation of the <code>FrameworkMethod.isStatic()</code> method
	 * that appears in JUnit 4.11. This method will be deprecated when JUnit 4.11
	 * becomes a minimum requirement for regex-tester.
	 * 
	 * @param method
	 * @return True if the specified method is declared static, false otherwise.
	 */
	public static boolean isMethodStatic(FrameworkMethod method) {
		return Modifier.isStatic(method.getMethod().getModifiers());
	}

	/**
	 * An implementation of the <code>FrameworkMethod.isPublic()</code> method
	 * that appears in JUnit 4.11. This method will be deprecated when JUnit 4.11
	 * becomes a minimum requirement for regex-tester.
	 * 
	 * @param method
	 * @return True if the specified method is declared public, false otherwise.
	 */
	public static boolean isMethodPublic(FrameworkMethod method) {
		return Modifier.isPublic(method.getMethod().getModifiers());
	}

}
