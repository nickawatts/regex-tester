#regex-tester
A Java library that reduces the complexity of testing regular expressions with
JUnit.

#Basic Usage
regex-tester simplifies the task of testing regular expressions in Java to a 
matter of specifying the regular expression, the strings to test against and
whether or not each string should produce a match. In other words, regex-tester
handles the boiler-plate code that you must write to perform such tests in JUnit
and leaves only a simple declaration of what is to be tested.

There are two flavors of test specifications in regex-tester: declare the 
strings to be tested in the JUnit test case or declare them in a properties file.
Below is an example of each.

##Declaring a regular expression test completely in a JUnit test case

    import com.thewonggei.regexTester.junit.Regex;
    import com.thewonggei.regexTester.junit.RegexTestStringInfo;
    import com.thewonggei.regexTester.junit.RegexTestSuite;
    import com.thewonggei.regexTester.junit.RegexTestStrings;
    //other necessary imports
    
    @RunWith(value=RegexTestSuite.class)
    @Regex(value="^com.*")
    public class BasicRegexTest {
        
        @RegexTestStrings
        public static List<RegexTestStringInfo> getTestParameters() {
            return Arrays.asList(new RegexTestStringInfo[] {
                    new RegexTestStringInfo(true, "com"),
                    new RegexTestStringInfo(true, "com.thewonggei"),
                    new RegexTestStringInfo(true, "com.thewonggei.regexTester"),
                    new RegexTestStringInfo(false, ".com.thewonggei"),
                    new RegexTestStringInfo(false, "www.google.com")
            });
        }
        
        @Test
        public void test() {}
    }
    
regex-tester lets you declare the parameters of the test with annotations. There
are two annotations that are required on each JUnit test case: 
`@Runwith(value=RegexTestSuite.class)` and `@Regex`. The 
`@RunWith(value=RegexTestSuite.class)` annotation informs JUnit that you will 
delegate control of test suite creation to the regex-tester library. The
`@Regex` annotation specifies the regular expression under test.

In this flavor of usage, your test case must include one, and only one, method 
annotated with `@RegexTestStrings`. This method declares each string to test
the regular expression specified with the `@Regex` annotation against and 
whether or not your expect there to be a match. 

From these annotations, the regex-tester library will create a suite of test
cases. There will be one test case for each test string specified. 