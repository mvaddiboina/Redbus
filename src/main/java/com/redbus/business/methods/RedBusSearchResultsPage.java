package com.redbus.business.methods;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.redbus.base.GenericLibrary;
import com.redbus.objectRepository.RedBusSearchResultsRepo;
import com.redbus.utilities.HTMLReports;
import com.redbus.utilities.TestFailedException;

public class RedBusSearchResultsPage extends GenericLibrary {
	// Object Creation - Generic Objects
		public WebDriver driver;
		HTMLReports reports;

		public RedBusSearchResultsPage(WebDriver driver) {
			this.driver = driver;
			reports = new HTMLReports();
		}
		
		/**
		 * @Description Search for the buses
		 * @param fromplace
		 * @param toplace
		 * @throws TestFailedException
		 * @throws Exception
		 */
		public void selectATravelsAndSeatNo(String travlesName,String seatNo) throws TestFailedException, Exception {
			boolean bFlag = false;
			try {  
				 WebElement privateBusList = driver.findElement(RedBusSearchResultsRepo.div_private_buses_list);
				 List<WebElement> buses = privateBusList.findElements(By.tagName("li"));
				    int win = 0;
				    for(int i=0;i<buses.size();i++)
				    {
				    	try{
				    	String busName = buses.get(i).findElement(By.className("BusName")).getText();
				        if(busName.equalsIgnoreCase(travlesName))
				        {
				        	win = i;
				        	buses.get(i).findElement(By.tagName("button")).click();
				        	break;}}
				    	catch(Exception e){}}
				    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				    buses.get(win).findElement(By.xpath("//a[starts-with(@title,'Seat No:"+seatNo+"')]")).click();
				bFlag = true;
			} catch (Exception ex) {
				bFlag = false;
				throw new TestFailedException("Failed to select the specified travels: "+ex);
			} 	
			finally{
				if(bFlag)
					reports.successReport("Select the specified travels", "Successfully selected the specified Travles"+travlesName);
				else
					reports.failureReport("Select the specified travels ", "Failed to select the specified Travles"+travlesName);
			}
		}
			
			
			
		public void selectBoardingPoint() throws TestFailedException, Exception {
				boolean bFlag = false;
				try {  
					Select boradingPoint =new Select(driver.findElement(By.tagName("select")));
				    boradingPoint.selectByIndex(2);
				    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
				    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				    driver.findElement(By.xpath("//a[contains(text(),'No. Not Now')]")).click();
					bFlag = true;
				} catch (Exception ex) {
					bFlag = false;
					throw new TestFailedException("Failed to select the boarding point"+ex);
				} 	
				finally{
					if(bFlag)
						reports.successReport("Select the specified boarding point", "Successfully selected the boarding point");
					else
						reports.failureReport("Select the specified boarding point ", "Failed to select the boarding point");
				}
			
			
			
			
			
			
			

		}
}
