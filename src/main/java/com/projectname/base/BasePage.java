package com.projectname.base;

import com.aventstack.extentreports.Status;
import com.projectname.utils.ExtentTestmanager;

public class BasePage extends BaseClass {
	
	public static boolean responsecodelog(int Expected, int Actual)
	{
		boolean flag=false;
		if(Expected==Actual)
		{
			ExtentTestmanager.getTest().log(Status.PASS, "Status Code - Verification Done </br> <b> Status Code : </b><span style='color:green'>"+Actual+"</span>");
			flag=true;
		}
		else
		{
			ExtentTestmanager.getTest().log(Status.FAIL, "Status Code - Mismatch </br><b> Actuals Status Code: </b><span style='color:red'>"+Actual+"</span></br> <b>Expected Status Code: </b><span style='color:green'>"+Expected+"</span> ");
		}
			
		return flag;
		
	}
	
	public static boolean responseJsonStringlog(String Expected, String Actual)
	{
		boolean flag=false;
		if(Expected.equalsIgnoreCase(Actual))
		{
			ExtentTestmanager.getTest().log(Status.PASS, "Response string - Verification Done </br> <b> String is : </b><span style='color:green'>"+Actual+"</span>");
			flag=true;
		}
		else
		{
			ExtentTestmanager.getTest().log(Status.FAIL, "Response string - Mismatch </br><b> Actuals String is </b><span style='color:red'>"+Actual+"</span></br> <b>Expected String is </b><span style='color:green'>"+Expected+"</span> ");
		}			
		return flag;
		
	}
	
	public static boolean responseSuccessStatuslog(boolean Expected, boolean Actual)
	{
		boolean flag=false;
		if(Expected==Actual)
		{
			ExtentTestmanager.getTest().log(Status.PASS, "Response Status - Verification Done </br> <b> Status is : </b><span style='color:green'>"+Actual+"</span>");
			flag=true;
		}
		else
		{
			ExtentTestmanager.getTest().log(Status.FAIL, "Response Status - Mismatch </br><b> Actuals Status is </b><span style='color:red'>"+Actual+"</span></br> <b>Expected Status is </b><span style='color:green'>"+Expected+"</span> ");
		}			
		return flag;
		
	}
	
	public static boolean responseFileSizelog(int Expected, int Actual)
	{
		boolean flag=false;
		if(Expected==Actual)
		{
			ExtentTestmanager.getTest().log(Status.PASS, "Status Code - Verification Done </br> <b> File Size is : </b><span style='color:green'>"+Actual+"</span>");
			flag=true;
		}
		else
		{
			ExtentTestmanager.getTest().log(Status.FAIL, "Status Code - Mismatch </br><b> Actuals File Size is  : </b><span style='color:red'>"+Actual+"</span></br> <b>Expected File Size is  : </b><span style='color:green'>"+Expected+"</span> ");
		}			
		return flag;		
	}
	

}
