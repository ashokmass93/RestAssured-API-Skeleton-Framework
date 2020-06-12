package com.projectname.base;

import java.util.Map;

import org.testng.IExecutionListener;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer,IExecutionListener,ITestListener,ISuiteListener{
	
	static int TOTALTCCOUNT;
	static int PASSTCCOUNT;
	static int FAILTCCOUNT;
    private int count = 0;
    private static int maxTry = 0;

	@Override
	public boolean retry(ITestResult iTestResult) {
		 if (!iTestResult.isSuccess()) {                      //Check if test not succeed
	            if (count < maxTry) {                            //Check if maxtry count is reached
	                count++;                                     //Increase the maxTry count by 1
	                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
	                return true;                                 //Tells TestNG to re-run the test
	            } else {
	                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
	            }
	        } else {
	            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
	        }
	        return false;
	}

	@Override
	public void onFinish(ITestContext arg0) {
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("TOT: "+TOTALTCCOUNT);
		System.out.println("PS: "+PASSTCCOUNT);
		System.out.println("FL: "+FAILTCCOUNT);
		BaseClass.buildReport(String.valueOf(TOTALTCCOUNT), String.valueOf(PASSTCCOUNT), String.valueOf(FAILTCCOUNT));
		}

	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		Map<String,ISuiteResult> resultMap = suite.getResults();
		IResultMap failedTestMap = null;
		IResultMap passTestMap = null;
		IResultMap Total = null;
		for (Map.Entry<String,ISuiteResult> ent : resultMap.entrySet()) {
			ISuiteResult res = ent.getValue();
			failedTestMap = res.getTestContext().getFailedTests();
			passTestMap = res.getTestContext().getPassedTests();
			TOTALTCCOUNT = TOTALTCCOUNT
					+ res.getTestContext().getAllTestMethods().length;
			FAILTCCOUNT = FAILTCCOUNT + failedTestMap.size();
			PASSTCCOUNT = PASSTCCOUNT + passTestMap.size();
		}  
		
	}

	@Override
	public void onStart(ISuite arg0) {
		
	}

}
