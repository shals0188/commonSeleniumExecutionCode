package testSoapPackage;

//import required packages
import org.testng.Assert;
import org.testng.annotations.Test;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStep;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class TestRunnerClass 
{
   @Test
   public void SoapTest()
   {
	 //Grab the project path 
	 WsdlProject project=new WsdlProject(System.getProperty("user.dir")+"\\API Files\\Calculator-readyapi-project.xml");
	 //Grab the Test Suite in the project
	 WsdlTestSuite testSuite=project.getTestSuiteByName("Calculator TestSuite");
	 // Create loop to execute all the test cases one by one that comes under the selected Test Suite
	 for(int i=0;i<testSuite.getTestCaseCount();i++)
	 {
		//Grab test cases for the test suites
	    WsdlTestCase testCase= testSuite.getTestCaseAt(i);
	    System.out.println("Test Case name is : " + testCase.getName());
	    
	    for(int j=0;j<testCase.getTestStepCount();j++)
	    {
	    	//Grab test steps for the test cases
	    	WsdlTestStep testStep=testCase.getTestStepAt(j);
	    	System.out.println("Test Step name is : " + testStep.getName());
	    	//Run test cases using TestRunner class
	    	TestRunner runner=testCase.run(new PropertiesMap(), false);
	    	//Verify actual and expected results
	    	Assert.assertEquals(Status.FINISHED, runner.getStatus());
	    }	
	  }
    }
}
