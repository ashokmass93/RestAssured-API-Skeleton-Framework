package com.projectname.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.projectname.files.FilesProvider;
import com.projectname.utils.ConfigurationManager;
import com.projectname.utils.Constants;
import com.projectname.utils.ExtentTestmanager;


public class BaseClass {
	
	public static String timeStamp;
	public static String environm;
	public JSONObject data;
	public static  ExtentTest test;
	
	
	@BeforeSuite
	public void beforeSuite() {		
		
		try {
			
			System.out.println("==================== "+Constants.PROJECT_NAME+" Automation Testing ====================");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	@BeforeTest
	public void loadConfigurationValues(ITestContext context){		
		
		ConfigurationManager.createManager(context);
		
		environm = ConfigurationManager.getenviron();
						
	}
	
/*	@Parameters({ "testcaseName"})
	@BeforeMethod
	public void createReport(String testcaseName) {
		
		test=extent.createTest(testcaseName);
		
	}*/
	
	
	
	@AfterMethod
	public void getResult(ITestResult result) {

		/*try {

			if (result.getStatus() == ITestResult.FAILURE) {				
				test.log(Status.FAIL, "Test case got failed");
			}
			
		}catch (Exception e) {
			
			
		}*/
		
		ExtentTestmanager.endTest();
	}
	
	
	@AfterSuite
	public void flushReport() {
		
		
	}
	
	
	public void extentReportInformation(String infoData) {		
		ExtentTestmanager.getTest().log(Status.INFO, infoData);		
	}
	
	public static void extentReportPass(String PassData) {		
		ExtentTestmanager.getTest().log(Status.PASS, PassData);		
	}
	
	
	public static void buildReport(String testedCases,String passedCases,String failedCases) {
		timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
		StringBuilder builder = new StringBuilder();
		
		try {
            String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
			builder.append("<html>"
					+ "<head><style>table {border-collapse: collapse;}"
					+ "table, td, th {border: 2px solid black;font-weight: bold;}</style></head>"
					+ "<body><center><img src='http://pgc-dev-test.s3.amazonaws.com/salomanat/images/final.png' alt='SalmonAT' height='25%' width='25%'></center>"
					+ "<p>Hi All,<p>"
					+ "<p>PFB. Pagination Pro API Automation Status.</p>"
					+ "<table>"
					+ "<tr>"
					+ "<td><font color='Purple'>TestCases: "+testedCases+"</font></td><td><font color='Green'>Passed: "+passedCases+"</font></td><td><font color='Red'>Failed: "+failedCases+"</font></td>"
					+ "</tr>"
					+ "</table>"
					+ "<p><a href ='http://autotestresult.tnq.co.in:81//Projects/AutomationTesting/Falcons/Api/Reports/ApiTestReport_"+timeStamp+".html'>Click here </a>to view the result.</p>"
					+ "<p><b>Note:</b> This is an automated mail. Do not reply to this mail. If you have any queries reply to this mail id "
					+ "<u>karthik.nithianandam@tnqsoftware.co.in</u></p>"
					+ "<p>Thanks &amp; regards,</p>"
					+ "<p>TestLab.</p>"
					+ "<pre>******* Happy Testing *******</pre>"
					+ "</blockquote>"
					+ "<br>"
					+ "</html>");
			File summaryLocation = new File("Summary.html");
			if(summaryLocation.exists()) {
				summaryLocation.delete();
				Thread.sleep(5000);
				summaryLocation.createNewFile();
			}
			OutputStream outputstream = new FileOutputStream(summaryLocation);
			Writer writer = new OutputStreamWriter(outputstream);
			writer.write(builder.toString());
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	 @Parameters({ "testcaseid"})
	 @BeforeMethod
	 public void createReport(String testcaseid) throws Exception {
		 System.out.println(testcaseid);
	  data = getDetails(testcaseid);
	  ExtentTestmanager.startTest(getData(data, "description"));
	  
	 }
	 
	 public String getData(JSONObject object, String keyName) throws Exception {
		  try {
		   return object.get(keyName.toLowerCase()).toString(); 
		  }
		  catch(Exception e) {
		   e.printStackTrace();
		   throw new Exception(keyName+" not found");
		  }
		 }
	 
	 public JSONObject getDetails(String testcaseid) throws Exception {
		  JSONObject finalObj = null;
		  try {
		   JSONParser parser = new JSONParser();
		   JSONArray jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/testdata.json"))));
		   System.out.println(jsonarray);
		   for(Object jsonobj : jsonarray) {
		     JSONObject myObj = (JSONObject) jsonobj;
		     String id = myObj.get("id").toString();
		     if(id.equalsIgnoreCase(testcaseid)) {
		      finalObj = (JSONObject) myObj.get("data");

		     }
		   }
		  }  
		  catch(Exception ex) {
		   ex.printStackTrace();
		   throw new Exception("Unable to Find TestCase"+testcaseid);
		  }
		  return finalObj;
		 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 }
