package com.redbus.testScripts;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redbus.base.GenericLibrary;
import com.redbus.business.methods.RedBusHomePage;
import com.redbus.datadriven.ReadExcelFile;
import com.redbus.utilities.TestFailedException;
/**
 * @Script COW001_SJA_CreateUpdateAndWorkFlow
 * @Module SJA
 * @ZephyrID 91584, 91585,91587
 * @Purpose SJA End to End workflow
 * @author Madhusudhan Vaddiboyna
 * @Date: 12-Jan-2016
 * @Script Updater and Date:
 */

public class REDBUS001SearchForBuses extends GenericLibrary {
	// Object Creation - Generic Objects
	
     HSSFWorkbook places= null; 
	
	@BeforeMethod
	public void initilizations() throws IOException
	{
		places = ReadExcelFile.loadExcel(new File("C:\\Users\\madhusudhan.vaddiboy\\COWWORKSPACE\\RedbusAutomationProject\\src\\main\\java\\com\\redbus\\testData\\TD001_Search_For_buses.xls"));
	}
	
	@Test
	public void verifySearchBuses() throws TestFailedException, Exception {
	RedBusHomePage home = new RedBusHomePage(driver);
     home.invokeRedBusApplication();
     home.seacrhForBuses(ReadExcelFile.getData(places, "Sheet1", "From_place").toString(),ReadExcelFile.getData(places, "Sheet1", "To_place").toString());
     home.selectDate("", "FromDate");
	}
}
