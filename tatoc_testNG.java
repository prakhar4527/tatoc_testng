package qainfotech.qainfotech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class tatoc_testNG
{
	WebDriver driver;
	@BeforeTest
	public void Launch_tatoc()
	{
	//	System.setProperty("webdriver.chrome.driver","/usr/share/applications");
		this.driver=new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		Assert.assertEquals("Welcome - T.A.T.O.C", driver.getTitle());
	}
	/*@Test
	public void testCaseVerifyHomePage() {
		driver= new  ChromeDriver();
		driver.navigate().to("http://10.0.1.86/tatoc");
		
	}*/
	
	
	
	
}
