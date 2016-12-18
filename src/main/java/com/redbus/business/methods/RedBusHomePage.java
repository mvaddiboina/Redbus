package com.redbus.business.methods;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.redbus.base.GenericLibrary;
import com.redbus.objectRepository.RedBusHomePageRepo;
import com.redbus.utilities.HTMLReports;
import com.redbus.utilities.TestFailedException;

public class RedBusHomePage extends GenericLibrary {

	// Object Creation - Generic Objects
	public WebDriver driver;
	HTMLReports reports;

	public RedBusHomePage(WebDriver driver) {
		this.driver = driver;
		reports = new HTMLReports();
	}

	/**
	 * @Description login to application with valid credentials
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void invokeRedBusApplication() throws TestFailedException, Exception{
		boolean flag = false;
		try {
			//Invoke Web Application
			invokeWebApplication("https://www.redbus.in/");			
			flag = true;
		} catch (Exception e) {
			throw new TestFailedException("Failed to navigate to the Redbus URL");
		}
		finally{
			if(flag)
				reports.successReport("Navigate to the Redbus URL","Successfully navigated to the Redbus URL");
			else
				reports.failureReport("Navigate to the Redbus URL","Failed to navigate to the Redbus URL");
		}
	}
	
	/**
	 * @Description Search for the buses
	 * @param fromplace
	 * @param toplace
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void seacrhForBuses(String fromplace, String toplace) throws TestFailedException, Exception {
		boolean bFlag = false;
		try {  
			type(RedBusHomePageRepo.txt_from_place,fromplace);
			type(RedBusHomePageRepo.txt_to_place,toplace);
			bFlag = true;
		} catch (Exception ex) {
			bFlag = false;
			throw new TestFailedException("Unable to search for the buses");
		} 	
		finally{
			if(bFlag)
				reports.successReport("Search for the buses", "Successfully got the results between "+fromplace+" and "+toplace);
			else
				reports.failureReport("Search for the buses ", "Failed to get the results between "+fromplace+" and "+toplace);
		}

	}
	
	
	/**
	 * @Description select the journey date
	 * @param journeydate
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void selectDate(String journeyDate,String dateType) throws TestFailedException, Exception {
		boolean bFlag = false;
		try {  
		click(RedBusHomePageRepo.btn_bus_search);
		Thread.sleep(5000);
		WebElement bigCalendar = driver.findElement(By.id("rbcal_txtOnwardCalendar"));
		
		if(dateType.equals("FromDate"))
		{
			WebElement fromCalendar = bigCalendar.findElement(By.xpath("//*[@id='rbcal_txtOnwardCalendar']/table[1]"));
			selectDateFromTable(fromCalendar);
		}
		else
		{
			WebElement ToCalendar = bigCalendar.findElement(By.xpath("//*[@id='rbcal_txtOnwardCalendar']/table[2]"));
			selectDateFromTable(ToCalendar);	
		}
		click(RedBusHomePageRepo.btn_bus_search);
			bFlag = true;
		} catch (Exception ex) {
			bFlag = false;
			throw new TestFailedException("Unable to select the date from the calendar: "+ex);
		} 	
		finally{
			if(bFlag)
				reports.successReport("Select the date", "Successfully selected the date");
			else
				reports.failureReport("Select the date", "Failed to select the date");
		}

	}
	
	public void selectDateFromTable(WebElement table)
	{
		List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		
		for(int i=0;i<rows.size();i++)
		{
			boolean stopSearch = false;
			List<WebElement> cols =rows.get(i).findElements(By.tagName("td"));
			
			for(int j=0;j<cols.size();j++)
			{
		
				if((cols.get(j).getText()).equals("20"))
				{
					cols.get(j).click();
					stopSearch = true;
					break;
				}
			
			}
			if(stopSearch){break;}
		}
	}
	
}
