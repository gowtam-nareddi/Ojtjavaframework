package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
	
	public static WebDriver driver;
	
	//Launch browser and url
	public static String launchApp(String url)
	{
		String res = "";
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Evening\\ERP_Stock\\CommonDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
		if(driver.findElement(By.id("btnsubmit")).isDisplayed())
		{
			res = "Application Launch Success";
		} else 
		{
			res = "Application launch Fail";
		}
		return res;
	}
	
	//Login method
	public static String verifyLogin(String username, String password) throws Throwable
	{
		String res = "";
		
		WebElement objuser = driver.findElement(By.name("username"));
		objuser.clear();
		Thread.sleep(3000);
		objuser.sendKeys(username);
		WebElement objpass = driver.findElement(By.name("password"));
		objpass.clear();
		Thread.sleep(3000);
		objpass.sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		Thread.sleep(3000);
		if(driver.findElement(By.id("logout")).isDisplayed())
		{
			res = "Login Success";
		} else 
		{
			res = "Login Fail";
		}
		return res;
	}
	
	//Logout method
	public static void verifyLogout()
	{
		driver.close();
	}
	
	//Method for supplier creation
		public static String verifySuppiler(String sname,String address,String city,String country,String cperson,
				String pnumber,String email,String mnumber,String notes ) throws Throwable
		{
			String res="";
			driver.findElement(By.linkText("Suppliers")).click();
			driver.findElement(By.xpath("//div[@class='panel-heading ewGridUpperPanel']//a[@class='btn btn-default ewAddEdit ewAdd btn-sm']")).click();
			
			String expdata=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
			driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
			driver.findElement(By.name("x_Address")).sendKeys(address);
			driver.findElement(By.name("x_City")).sendKeys(city);
			driver.findElement(By.name("x_Country")).sendKeys(country);
			driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
			driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
			driver.findElement(By.name("x__Email")).sendKeys(email);
			driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
			driver.findElement(By.name("x_Notes")).sendKeys(notes);
			driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
			Thread.sleep(2000);
			if(!driver.findElement(By.id("psearch")).isDisplayed())
			driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(expdata);
			Thread.sleep(2000);
			driver.findElement(By.id("btnsubmit")).click();
			Thread.sleep(2000);
			String actdata=driver.findElement(By.id("el1_a_suppliers_Supplier_Number")).getText();
			System.out.println(expdata+"  "+actdata);
			if(actdata.equals(expdata)){
				res="Pass";
			}else{
				res="Fail";
			}
			return res;
			}
	
}
