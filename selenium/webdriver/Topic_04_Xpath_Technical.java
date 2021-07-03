package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Technical {
	
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
	}
	
	// step
	@Test
	public void TC_01_Login_Empty_Email_And_Password(){
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		driver.findElement(By.xpath("//input[@title='Email Address']")).clear();
		driver.findElement(By.xpath("//input[@title='Password']")).clear();
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");	
		assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("123123@123.123");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	@Test
	public void TC_03_Login_Invalid_Pass() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_Login_Incorrect_Email() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}
	// postcondition - clean up data
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
