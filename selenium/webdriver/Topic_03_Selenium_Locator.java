package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {
	
	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;
	
	// precondition
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox
		driver = new FirefoxDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Mở application lên (AUT / SUT)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");		 
	}
	
	// step

	public void TC_01_FindElement(){
		
	}
	

	public void TC_02_ID() {
		driver.findElement(By.id("send2")).click();
		
		//Verify
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
	}
	
	@Test
	public void TC_03_ClassName() {
		driver.navigate().refresh();
		driver.findElement(By.className("validate-password")).sendKeys("123456789");		
	}
	

	public void TC_04_Name() {
		driver.navigate().refresh();		
		driver.findElement(By.name("send")).click();
		//Verify
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
	}
	
	public void TC_05_LinkText() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("http://live.demoguru99.com/index.php/customer/account/forgotpassword/"));
	}
	

	public void TC_06_PartialLinkText() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		driver.findElement(By.partialLinkText("Password?")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("http://live.demoguru99.com/index.php/customer/account/forgotpassword/"));
	}
	
	public void TC_07_TagName() {
		List<WebElement> loginPageLinks= driver.findElements(By.tagName("a"));
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}
	
	@Test
	public void TC_08_CssSelector() {
		driver.findElement(By.cssSelector("#email")).sendKeys("thucnguyen@.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("thucnguyen@.com");
	}
	@Test
	public void TC_09_Xpath() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thucnguyen@.com");
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("thucnguyen@.com");		
	}
	
	
	// postcondition - clean up data
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
