package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowsers_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	public By MyAccountLink = By.cssSelector(".footer a[title = 'My Account']");
	public By CreateAccountButton = By.cssSelector("a[title = 'Create an Account']");
	
	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_VerifyURL() {
		//Step 1: Truy cập web
		driver.get("http://live.demoguru99.com");
		
		//Step 2: Click MY ACCOUNT
		driver.findElement(MyAccountLink).click();
		
		//Step 3: Verify URL
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Step 4: Click CREATE AN ACCOUNT
		driver.findElement(CreateAccountButton).click();
		
		//Step 5: Verify URL
		String createPageURL = driver.getCurrentUrl();
		Assert.assertEquals(createPageURL, "http://live.demoguru99.com/index.php/customer/account/create/");				
	}

	@Test
	public void TC_02_VerifyTitle() {
		//Step 1: Truy cập web
		driver.get("http://live.demoguru99.com");
		
		//Step 2: Click MY ACCOUNT
		driver.findElement(MyAccountLink).click();
		
		//Step 3: Verify Title
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
		//Step 4: Click CREATE AN ACCOUNT
		driver.findElement(CreateAccountButton).click();
		
		//Step 5: Verify Title
		String createPageTitle = driver.getTitle();
		Assert.assertEquals(createPageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_NavigateFunction() {
		//Step 1: Truy cập web
		driver.get("http://live.demoguru99.com");
		
		//Step 2: Click MY ACCOUNT
		driver.findElement(MyAccountLink).click();

		//Step 3: Click CREATE AN ACCOUNT
		driver.findElement(CreateAccountButton).click();
		
		//Step 4: Verify URL
		String createPageURL = driver.getCurrentUrl();
		Assert.assertEquals(createPageURL, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		//Step 5: Back to Log in page
		driver.navigate().back();
		
		//Step 6: Verify URL
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Step 7: Forward to Register page
		driver.navigate().forward();
		
		//Step 8: Verify URL
		Assert.assertEquals(createPageURL, "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_04_VerifyPageSource() {
		//Step 1: Truy cập web
		driver.get("http://live.demoguru99.com");
		
		//Step 2: Click MY ACCOUNT
		driver.findElement(MyAccountLink).click();
		
		//Step 3: Verify Text
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Login or Create an Account"));
		
		//Step 4: Click CREATE AN ACCOUNT
		driver.findElement(CreateAccountButton).click();
		
		//Step 5: Verify Text
		pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
