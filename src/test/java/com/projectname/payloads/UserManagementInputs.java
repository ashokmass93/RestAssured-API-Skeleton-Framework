package com.projectname.payloads;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.projectname.basetest.APIMapper_New;

public class UserManagementInputs extends APIMapper_New {
	
	public Map<String, Object> headerAuthorization(){
		Map<String, Object> headAuth = new HashMap<String, Object>();
		headAuth.put("X-Customer-Id", "695233");
		headAuth.put("Content-Type", "application/json");		
		return headAuth;
	}
	
	
 public String userRegistration = "{\r\n" + 
 		"                        \"first_name\": [\r\n" + 
 		"                            {\r\n" + 
 		"                                \"lang\": \"en\",\r\n" + 
 		"                                \"value\": \"Nareshv\"\r\n" + 
 		"                            }\r\n" + 
 		"                        ],\r\n" + 
 		"                        \"email\": [\r\n" + 
 		"                            {\r\n" + 
 		"                                \"lang\": \"en\",\r\n" + 
 		"                                \"value\": \"naresh.vodlamani@tnqsoftware.co.in\"\r\n" + 
 		"                            }\r\n" + 
 		"                        ],\r\n" + 
 		"                        \"user_password\": [\r\n" + 
 		"                            {\r\n" + 
 		"                                \"lang\": \"en\",\r\n" + 
 		"                                \"value\": \"test1234\"\r\n" + 
 		"                            }\r\n" + 
 		"                        ]\r\n" + 
 		"                    }";
 

}
