package DriverFactory;


import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.emitter.Emitable;

import com.gargoylesoftware.css.util.ThrowCssExceptionErrorHandler;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDriven {
	
	WebDriver driver;
	@BeforeTest
	public void setUp() throws Throwable
	{
		String launch = ERP_Functions.launchApp("http://webapp.qedge.com/");
		Reporter.log(launch, true);
		String login=ERP_Functions.verifyLogin("admin","master");
	}
	
	@Test
	public void supplierCreation() throws Throwable
	{
		//accessing excel util methods
		ExcelFileUtil xl = new ExcelFileUtil();
		
		//row count
		int rc = xl.rowCount("Suppiler");
		int cc = xl.colCount("Suppiler");
		Reporter.log("no of rows are::"+rc+"    "+"no of coumns are::"+cc, true);
		for(int i=1;i<=rc;i++)
		{
			String sname = xl.getCellData("Suppiler", i, 0);
			String address = xl.getCellData("Suppiler", i, 1);
			String city = xl.getCellData("Suppiler", i, 2);
			String country = xl.getCellData("Suppiler", i, 3);
			String cperson = xl.getCellData("Suppiler", i, 4);
			String pnumber = xl.getCellData("Suppiler", i, 5);
			String email = xl.getCellData("Suppiler", i, 6);
			String mnumber = xl.getCellData("Suppiler", i, 7);
			String notes = xl.getCellData("Suppiler", i, 8);
			
			//call suppiler creation method
			String Results = ERP_Functions.verifySuppiler(sname, address, city, country, cperson, pnumber,email, mnumber, notes);
			Reporter.log(Results, true);
			
			xl.setCellData("Suppiler", i, 9, Results);
				
		}
				
	}
	
	@AfterTest
	public void tearDown()
	{
		ERP_Functions.verifyLogout();
	}
	
}
