package com.projectname.utils;

import java.net.InetAddress;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.projectname.files.FilesProvider;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class Extentmanager {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			try {
				htmlReporter = new ExtentHtmlReporter(FilesProvider.extentReportFilePath);
				htmlReporter.loadXMLConfig(FilesProvider.extentReportConfiXMLFilePath);
				extent = new ExtentReports();
				extent.attachReporter(htmlReporter);
				extent.setSystemInfo("Name", Constants.PROJECT_NAME);
				extent.setSystemInfo("OS", Constants.OS);
				extent.setSystemInfo("Host Name", Constants.HOST_NAME);
				extent.setSystemInfo("Environment", Constants.ENV);
				extent.setSystemInfo("User Name", Constants.USER_NAME);
				
				System.out.println("OBJ is  not Available");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("OBJ is Available");
		}
		return extent;
	}
}
