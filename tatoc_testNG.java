package qainfotech.qainfotech;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.Cookie;

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
	@Test
	public void testBasicCourseLink()
	{
		driver.findElement(By.linkText("Basic Course")).click();
		WebElement heading=driver.findElement(By.tagName("h1"));
		//WebElement heading=driver.findElement(By.xpath("//*[text()[contains(.,'Grid Gate')]]"));
		String s1=heading.getText();
		Assert.assertEquals(s1,"Grid Gate");
	}
	@Test(dependsOnMethods= {"testBasicCourseLink"})
	public void checkGreenbox()
	{
		Assert.assertEquals(driver.findElement(By.className("greenbox")).isDisplayed(), true);
		driver.findElement(By.className("greenbox")).click();
		String expectedUrl = "http://10.0.1.86/tatoc/basic/frame/dungeon";

		Assert.assertEquals(expectedUrl, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
	}
	@Test(dependsOnMethods= {"checkGreenbox"})
	public void checkFrameDungeon()
	{
		driver.switchTo().frame("main");
		Assert.assertTrue(driver.findElement(By.id("answer")).isDisplayed(), "Box1 is displayed");
		WebElement b1=driver.findElement(By.xpath("//*[text()[contains(.,'Box 1')]]"));
		int flag=0;
		while(flag==0)
		{
			String s1 = b1.getAttribute("class");
			driver.switchTo().frame("child");
			WebElement b2=driver.findElement(By.xpath("//*[text()[contains(.,'Box 2')]]"));
			String s2 = b2.getAttribute("class");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			if(s1.equals(s2))
			{
				flag=1;
				WebElement pro=driver.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]"));
				pro.click();
			}
			else
			{
				WebElement re=driver.findElement(By.xpath("//*[text()[contains(.,'Repaint Box 2')]]"));
				re.click();
			}
		}
		driver.switchTo().defaultContent();
		String expectedUrl = "http://10.0.1.86/tatoc/basic/drag";
		Assert.assertEquals(expectedUrl, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
	}
	@Test(dependsOnMethods= {"checkFrameDungeon"})
	public void checkDragAround()
	{
		Assert.assertEquals(driver.findElement(By.id("dropbox")).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.id("dragbox")).isDisplayed(), true);
		WebElement drag=driver.findElement(By.id("dragbox"));
		WebElement drop=driver.findElement(By.id("dropbox"));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(drag, drop).perform();
		WebElement pro1=driver.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]"));
		pro1.click();
		String expectedUrl = "http://10.0.1.86/tatoc/basic/windows";
		Assert.assertEquals(expectedUrl, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
	}
	@Test(dependsOnMethods= {"checkDragAround"})
	public void checkPopupWindows()
	{
		WebElement heading=driver.findElement(By.tagName("h1"));
		String s1=heading.getText();
		Assert.assertEquals(s1,"Popup Windows");
		WebElement launch=driver.findElement(By.xpath("//*[text()[contains(.,'Launch Popup Window')]]"));
		launch.click();
		ArrayList windowsList =new ArrayList(driver.getWindowHandles());
		String window1= ((String)windowsList.get(1));
		driver.switchTo().window(window1);
		String expectedUrl = "http://10.0.1.86/tatoc/basic/windows/popup";
		Assert.assertEquals(expectedUrl, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
		driver.findElement(By.id("name")).sendKeys("Prakhar");
		driver.findElement(By.id("submit")).click();
		String window0= ((String)windowsList.get(0));
		driver.switchTo().window(window0);
		String expected_Url = "http://10.0.1.86/tatoc/basic/windows#";
		Assert.assertEquals(expected_Url, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
		/* String window0= ((String)windowsList.get(0));
		 driver.switchTo().window(window0);*/
		driver.findElement(By.linkText("Proceed")).click();
		String expectedUrl1 = "http://10.0.1.86/tatoc/basic/cookie";
		Assert.assertEquals(expectedUrl1, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
	}
	@Test(dependsOnMethods= {"checkPopupWindows"})
	public void checkCookieHandling()
	{
		WebElement heading=driver.findElement(By.tagName("h1"));
		String s1=heading.getText();
		Assert.assertEquals(s1,"Cookie Handling");
		driver.findElement(By.linkText("Generate Token")).click();
		String Token = driver.findElement(By.id("token")).getText();
		System.out.println(Token);
		String substring1=Token.substring(7);
		Cookie name = new Cookie("Token", substring1);
		driver.manage().addCookie(name);
		driver.findElement(By.linkText("Proceed")).click();
		String expectedUrl1 = "http://10.0.1.86/tatoc/end";
		Assert.assertEquals(expectedUrl1, driver.getCurrentUrl(), "Didn't navigate to correct webpage");
	}

}
