package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_07_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//Login page value
	String username;
	String password;
	
	//Login page locator
	By usernameTextbox = By.name("uid");
	By passwordTextbox = By.name("password");
	By loginButton = By.name("btnLogin");
	
	//New Customer page value
	String customerID;
	String customerName = "Nguyen Dong Thuc";
	String gender = "male";
	String DOB = "1995-05-11";
	String address = "220 47 Test";
	String city = "Ho Chi Minh";
	String state = "Ho Chi Minh";
	String pin = "089567";
	String mobile = "089756325";
	
	//New and Edit Customer page locator
	By customerNameTextbox = By.name("name");
	By genderRadioButton = By.cssSelector("input[value='m']");
	By DOBTextbox = By.name("dob");
	By addressTextarea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By mobilePhoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By submitButton = By.name("sub");


	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		/*
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		*/
		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Step 1: Access page
		driver.get("http://demo.guru99.com/v4/");
		
		// Step 2.1: Generate account
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("test"+randomNumber()+"@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(1);
		username=driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		// Step 2: Log in và verify homepage
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
				
		
	}

	@Test (invocationCount = 5)
	public void TC_01_CreateNewCustomer() {
		// Step 3: Click chọn link new customer
		driver.findElement(By.linkText("New Customer")).click();
		
		// Step 4: Nhập giá trị của customer mới và click submit
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadioButton).click();
		driver.findElement(DOBTextbox).sendKeys("11051995");
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobilePhoneTextbox).sendKeys(mobile);
		driver.findElement(emailTextbox).sendKeys("thucTesting"+randomNumber()+"@gmail.com");
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
		sleepInSecond(1);
		
		//Step 5: Check Customer đã được tạo thành công. Lưu lại Customer ID
		assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and contains(text(),'Customer Registered Successfully')]")).isDisplayed());
		customerID= driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerID);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int randomNumber(){
		Random rand = new Random();
		return rand.nextInt(10000);
	}
}
