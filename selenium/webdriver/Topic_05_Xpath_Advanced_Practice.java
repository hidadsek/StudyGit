package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Advanced_Practice {
	
	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String firstName = "John";
	String middleName = "Ce";
	String lastName = "Wick";
	String email = "ThucTest"+ randomNumber() + "@gmail.com";
	String password ="Test@123";
	String confirmPassword = "Test@123";
	String fullName =firstName+" "+middleName+" "+lastName;


	// configuration
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox
		//System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		// Mở chrome
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		driver.manage().window().maximize();
	}
	
	// precondition
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");			 
	}

	@Test
	public void TC_01_Create_New_Account(){
		//Click My Account link --> "Create an Account" Link
		driver.findElement(By.xpath("//div[@class ='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		//Input registration values
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys(middleName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(confirmPassword);
		
		//Click Register button
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		//Check "Success message"
		assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(),
				"Thank you for registering with Main Website Store.");
		
		//Check name: Last + Middle + First Name and email
		String contactInformation = driver.findElement(By.xpath(
				"//h3[text()='Contact Information']//parent::div//following-sibling::div/p")).getText();
		assertTrue(contactInformation.contains(fullName));
		assertTrue(contactInformation.contains(email));		
	}
	
	@Test
	public void TC_02_Login_Account(){
		//Click My Account link --> "Create an Account" Link
		driver.findElement(By.xpath("//div[@class ='footer']//a[text()='My Account']")).click();
		
		//Input username and password and click log in button
		//thucTest01@gmail.com/123456
		driver.findElement(By.name("login[username]")).sendKeys(email);
		driver.findElement(By.name("login[password]")).sendKeys(password);
		driver.findElement(By.name("send")).click();
		
		//Check "My Dashboard"
		assertEquals(driver.findElement(By.cssSelector(".page-title>h1")).getText(),
				"MY DASHBOARD");
		assertEquals(driver.findElement(By.cssSelector(".dashboard .welcome-msg strong")).getText(),
				"Hello, "+fullName+"!");
		
		//Check name: Last + Middle + First Name and email
		String contactInformation = driver.findElement(By.xpath(
				"//h3[text()='Contact Information']//parent::div//following-sibling::div/p")).getText();
		assertTrue(contactInformation.contains(fullName));
		assertTrue(contactInformation.contains(email));		
	}
	
	// postcondition - clean up data
	@AfterMethod
	public void afterMethod() {
		// Log out
		driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
		driver.findElement(By.linkText("Log Out")).click();
	}
	
	// postcondition - clean up data
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
}
