package com.projectname.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {
	
	/**
	 * @usage : For Extent report
	 *
	 */	
	public static String PROJECT_NAME="";
	public static String OS=System.getProperty("os.name");
	public static String HOST_NAME=hostName();
	public static String ENV="";
	public static String USER_NAME="";
	
	
	
public static String  hostName() {
	try {
		return InetAddress.getLocalHost().getHostName();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "";
}


}
