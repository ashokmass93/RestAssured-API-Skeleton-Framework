package com.projectname.files;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilesProvider {
	

	public static String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
//	public static String extentReportFilePath="//tnqfs07/TESTING_SERVICES/Projects/AutomationTesting/Falcons/Api/Reports/ProjectName_Automation_Testing_Report.html";
	public static String extentReportFilePath="./reports/ProjectName_Automation_Testing_Report.html";
	public static String extentReportConfiXMLFilePath="./src/main/resources/extent-config.xml";
	public static String configPropertyFilePath="./src/main/resources/config.properties";
	public static String testdataJsonFilePath="./src/test/resources/testdata.json";
	public static String UploadInvalidfile = "./Input-Files/Invalid.doc";
	
}
