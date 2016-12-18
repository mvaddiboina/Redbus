package com.redbus.testScripts;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redbus.base.GenericLibrary;
import com.redbus.business.methods.RedBusHomePage;
import com.redbus.business.methods.RedBusSearchResultsPage;
import com.redbus.datadriven.ReadExcelFile;
import com.redbus.utilities.TestFailedException;

public class REDBUS002SelectSeatAndPickupLocation extends GenericLibrary{
	 HSSFWorkbook places= null; 
		
		@BeforeMethod
		public void initilizations() throws IOException
		{
			places = ReadExcelFile.loadExcel(new File("C:\\Users\\madhusudhan.vaddiboy\\COWWORKSPACE\\RedbusAutomationProject\\src\\main\\java\\com\\redbus\\testData\\TD001_Search_For_buses.xls"));
		}
		
		@Test
		public void BookATicket() throws TestFailedException, Exception {
		RedBusHomePage home = new RedBusHomePage(driver);
		RedBusSearchResultsPage resultsPage = new RedBusSearchResultsPage(driver);
	     home.invokeRedBusApplication();
	     home.seacrhForBuses(ReadExcelFile.getData(places, "Sheet1", "From_place").toString(),ReadExcelFile.getData(places, "Sheet1", "To_place").toString());
	     home.selectDate("", "FromDate");
	     resultsPage.selectATravelsAndSeatNo("Orange Tours Travels","19");
	     resultsPage.selectBoardingPoint();
		}
}
