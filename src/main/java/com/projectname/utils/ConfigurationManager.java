package com.projectname.utils;

import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class ConfigurationManager {
	
	private static final String ENVIRONMENT="environment";
	
	public static XmlSuite suite;	

	public static void createManager(ITestContext context){
		try {			
			suite = context.getSuite().getXmlSuite();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static String getenviron(){
		return suite.getParameter(ENVIRONMENT);
	}

	
	
	public static String getConfigurationValue(String configurationKey){
		return suite.getParameter(configurationKey);
	}
	
//	public static boolean getTestRailIntegrationMode(){
//		boolean testrailMode=false;
//		String mode = suite.getParameter(TESTRAIL_MODE).toLowerCase();
//		
//		if(mode.equals(TESTRAIL_MODE_ON.toLowerCase())){
//			testrailMode=true;
//		}else if (mode.equals(TESTRAIL_MODE_OFF.toLowerCase())){
//			testrailMode=false;
//		}
//		return testrailMode;
//	}
	

}
