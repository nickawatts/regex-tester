package com.thewonggei.regexTester;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;

import com.thewonggei.regexTester.junit.RegexTestStringInfo;

/**
 * Load information about strings to test against a given regular
 * expression from a standard 
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Properties.html">
 * Java properties file</a> where the key is a string against which to test the 
 * regular expression and the value indicates whether or not a match should be 
 * found (either "true" or "false"). Currently, the boolean indicator in each
 * property's value must be either the string "true" or "false". XML properties 
 * files are not supported.
 * 
 * @author Nick Watts
 * @since 0.1
 */
public class RegexTestStringInfoFileLoader {
	private File propsFile;
//	private Properties props = null;
	
	public RegexTestStringInfoFileLoader(String propsFilePath) {
		propsFile = new File(propsFilePath);
	}
	
//	public RegexTestStringInfoFileLoader cache() throws IOException {
//		loadPropsFromFile();
//		return this;
//	}
	
	public List<RegexTestStringInfo> load() throws FileNotFoundException, IOException {
		List<RegexTestStringInfo> rtsiList;
		FileInputStream fileInput = null;
		try {
			rtsiList = new ArrayList<RegexTestStringInfo>();
			Properties props = new Properties();
			fileInput = new FileInputStream(propsFile);
			props.load(new BufferedInputStream(fileInput));
			for( String testString : props.stringPropertyNames() ) {
				boolean shouldItMatch = BooleanUtils.toBoolean(props.getProperty(testString));
				rtsiList.add(new RegexTestStringInfo(shouldItMatch, testString));
			}
		} finally {
			if( fileInput != null ) {
				fileInput.close();
			}
		}
		return rtsiList;
	}
	
//	private void loadPropsFromFile() throws IOException {
//		FileInputStream fileInput = null;
//		try {
//			fileInput = new FileInputStream(propsFile);
//			props.load(new BufferedInputStream(fileInput));
//		} finally {
//			if( fileInput != null ) {
//				fileInput.close();
//			}
//		}
//	}
}
