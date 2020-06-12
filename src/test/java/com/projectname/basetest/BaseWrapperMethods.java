package com.projectname.basetest;

import java.util.Map;

import io.restassured.response.Response;

public interface BaseWrapperMethods {
	
	
	/**
	 * 
	 * @param unformattedJson
	 * @return
	 * @description 
	 */
	public String formattedJson(String unformattedJson);
	
	/**
	 * 
	 * @return
	 */
	public String baseURI();
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> getHeader();
	
	/**
	 * 
	 * @return
	 */
	public static String getSaltString() {
		return null;
	}
	
		/**
		 * 
		 * @param doubleQuoatedString
		 * @return
		 */
	public String removeDoubleQuotes(String doubleQuoatedString);
	
	/**
	 * 
	 * @param bodyPayLoadJson
	 * @param pathParameter
	 * @return
	 */
	public Response getPOST(String bodyPayLoadJson, String pathParameter);
	
	
	/**
	 * 
	 * @param pathParameter
	 * @return
	 */
	public Response getGET(String pathParameter);
	
	/**
	 * 
	 * @param bodyPayLoadJson
	 * @param pathParameter
	 * @return
	 */
	public Response getPUT(String bodyPayLoadJson, String pathParameter);
	
	/**
	 * 
	 * @param pathParameter
	 * @return
	 */
	public Response getDELETE(String pathParameter);
	
	
	
	
	
	

}
