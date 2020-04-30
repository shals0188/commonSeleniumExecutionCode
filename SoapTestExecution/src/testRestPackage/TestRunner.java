package testRestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStep;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class TestRunner 
{
   //Grab Project 
   WsdlProject project=new WsdlProject(System.getProperty("user.dir")+"\\API Files\\Deck-of-Cards-readyapi-project.xml");
   @Test
   public void restApiTestExecution()
   {
     for(int i=0;i<project.getTestSuiteCount();i++ )
     {
       WsdlTestSuite testSuite=project.getTestSuiteAt(i);
       //WsdlTestSuite testSuite=project.getTestSuiteByName("End-to-End Test Suite");
       System.out.println(testSuite.getName());
       
       for(int j=0;j<testSuite.getTestCaseCount();j++)
       {
	    //Grab the test case
        WsdlTestCase testCase= testSuite.getTestCaseAt(j);
        System.out.println("Test Case name is : " + testCase.getName());
    
        for(int k=0;k<testCase.getTestStepCount();k++)
        {
          WsdlTestStep testStep=testCase.getTestStepAt(k); 
          System.out.println("Test Step name is : " + testStep.getName());
          //Using TestRunner class to run the Test cases
          WsdlTestCaseRunner runner=testCase.run(new PropertiesMap(), false);
          System.out.println(runner.getStatus());
          System.out.println(Status.FINISHED);
  
          //To verify actual and expected output
	      Assert.assertEquals(Status.FINISHED, runner.getStatus());
         }
       }
     }
   }
}
