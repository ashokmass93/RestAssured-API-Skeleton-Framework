package com.projectname.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.projectname.files.FilesProvider;

public class PropertyObjectReader {
	
	
public static Properties prop;
	
public  static Properties propReader() 
{
		
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File(FilesProvider.configPropertyFilePath)));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return prop;
	}
	
public  static String userManagement() {
	return propReader().getProperty("UserManagement_BaseURL");		
	}

public  static String fileManagement() {
	return propReader().getProperty("FileManagement_BaseURL");		
	}

public  static String conversion() {
	return propReader().getProperty("Conversion_BaseURL");		
	}

public  static String template() {
	return propReader().getProperty("Template_BaseURL");		
	}
	
	

}
