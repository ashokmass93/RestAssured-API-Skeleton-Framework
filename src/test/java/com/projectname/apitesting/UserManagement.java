package com.projectname.apitesting;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.projectname.base.BasePage;
import com.projectname.payloads.UserManagementInputs;
import com.projectname.utils.ExtentTestmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;



public class UserManagement extends UserManagementInputs{
	
	JsonPath jsonPathEvaluator;
	boolean responseStatus;
	double responseTime;
	int responseCode;
	String  newRegisterResponse, accessToken, loginResponse, newRegisterForInvalidEmailResponse, newRegisterEmptyInputDataResponse;
	
	@Test
	public void newUserRegistrationWithRegistratedEmail() {
		
		try {

			UserManagement_Registration_Login_BaseURL();
			Response newUser =	getPOSTUsingFormAsBodyWithHeaders(headerAuthorization(), userRegistration, getData(data, "pathparamter"));

			extentReportInformation("Request is Sent");
			System.out.println("New registration user resp is" + newUser.asString());
			extentReportPass("New registration user");
			
			System.out.println("newUserRegistration" + newUser.asString());

			jsonPathEvaluator = newUser.jsonPath();
			responseStatus = jsonPathEvaluator.get("status");

			responseTime = newUser.getTime();
			responseCode = newUser.getStatusCode();
			newRegisterResponse = newUser.asString();

			Assert.assertEquals(400, responseCode);	

		} catch (Exception e) {
			System.out.println("Actual error of new register User is " + e.getMessage());
			Assert.fail();
		}

		finally {

			ExtentTestmanager.getTest().log(Status.PASS, "Request URI:  " + UserManagement_BaseURL() + pathPostParameter);
			ExtentTestmanager.getTest().log(Status.PASS, "Response time:  " + responseTime);
			BasePage.responsecodelog(400, responseCode);
			BasePage.responseSuccessStatuslog(false, responseStatus);
			ExtentTestmanager.getTest().log(Status.PASS, "Response data: " + MarkupHelper.createCodeBlock((newRegisterResponse)).getMarkup());

		}

	}
	
	}
