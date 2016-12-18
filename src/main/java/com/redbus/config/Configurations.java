package com.redbus.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.redbus.base.DriverScript;



public class Configurations
{    
	public String Browser = getConfigData("Browser");	

	public String URL = getConfigData("URL");

	public String testDataFilePath = getConfigData("TestDataFilePath");

	public String pageLoadTimeOut = getConfigData("pageLoadTimeOut");
	public String implicitWaitForElement = getConfigData("implicitWaitForElement");

	public String UserName = getConfigData("UserName");
	public String Password = getConfigData("Password");

	public final String sTakeScreenshot = getConfigData("TakeScreenshot");
	public final String sScriptPassResult = getConfigData("ScriptPassResult");
	public final String sResultFolder = getConfigData("ResultFolder");

	public String getConfigData(String sKey) {

		Properties prop = new Properties();
		InputStream input = null;
		String data = null;
		try {
			if(DriverScript.sIsMaven.equalsIgnoreCase("true"))
			{
				/* Code to read excel data present within the jar file. */
				input = new BufferedInputStream(getClass().getResourceAsStream("/config/config.properties"));

			}
			else
				input = new FileInputStream("C:\\Users\\madhusudhan.vaddiboy\\COWWORKSPACE\\RedbusAutomationProject\\src\\main\\java\\com\\redbus\\config\\config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			data = prop.getProperty(sKey);
			if(data!=null)
				data = data.trim();
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
			return data;
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}