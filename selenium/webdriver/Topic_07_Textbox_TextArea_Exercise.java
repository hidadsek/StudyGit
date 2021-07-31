package webdriver;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_Exercise {
	//Set Up WebDriver
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
	String email = "thucTesting"+randomNumber()+"@gmail.com";
	
	//Edit Customer page value
	String editAddress = "220 Binh Tan";
	String editCity = "Ha Noi";
	String editState = "Ha Noi";
	String editPin = "123456";
	String editMobile = "01234567";
	String editEmail = "thucEdit"+randomNumber()+"@gmail.com";
	
	//New and Edit Customer page locator
	By customerNameTextboxBy = By.name("name");
	By genderRadioButtonBy = By.cssSelector("input[value='m']");
	By DOBTextboxBy = By.name("dob");
	By addressTextareaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By mobilePhoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By submitButtonBy = By.name("sub");


	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Chrome Driver
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Step 1: Access page
		driver.get("http://demo.guru99.com/v4/");
		String loginURL = driver.getCurrentUrl();
		
		// Step 1.1: Get username and password
		driver.findElement(By.linkText("here")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		username= driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		password= driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
				
		// Step 2: Log in và verify homepage
		driver.get(loginURL);
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		assertEquals(driver.getCurrentUrl(),"http://demo.guru99.com/v4/manager/Managerhomepage.php");
		assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText().contains(username));
				
		
	}

	@Test	
	public void TC_01_CreateNewCustomer() {
		// Step 3: Click chọn link new customer
		driver.findElement(By.linkText("New Customer")).click();
		
		// Step 4: Nhập giá trị của customer mới và click submit
		driver.findElement(customerNameTextboxBy).sendKeys(customerName);
		driver.findElement(genderRadioButtonBy).click();
		
		WebElement DOBTextbox = driver.findElement(DOBTextboxBy);
		JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
		jExecutor.executeScript("arguments[0].removeAttribute('type')", DOBTextbox);
		sleepInSecond(3);
		// For chrome
		driver.findElement(DOBTextboxBy).sendKeys("05/11/1995");
		// For Firefox
		//driver.findElement(DOBTextboxBy).sendKeys("1995-05-11");
		
		driver.findElement(addressTextareaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(mobilePhoneTextboxBy).sendKeys(mobile);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButtonBy).click();
		sleepInSecond(1);
		
		//Step 5: Check Customer đã được tạo thành công. Lưu lại Customer ID
		assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and contains(text(),'Customer Registered Successfully')]")).isDisplayed());
		customerID= driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerID);
		
		//Step 6: Verify tất cả value sau khi tạo xong
		assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DOB);
		assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobile);
		assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	}

	@Test
	public void TC_02_EditCustomer() {
		//Step 7: Click Edit Customer link
		driver.findElement(By.linkText("Edit Customer")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Step 8: Verify các field bị disabled và giá trị của Customer Name và Address
		assertFalse(driver.findElement(customerNameTextboxBy).isEnabled());
		assertFalse(driver.findElement(By.name("gender")).isEnabled());
		assertFalse(driver.findElement(DOBTextboxBy).isEnabled());
		assertEquals(driver.findElement(customerNameTextboxBy).getAttribute("value"),customerName);
		assertEquals(driver.findElement(addressTextareaBy).getText(),address);
		assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"),city);
		assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"),state);
		assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"),pin);
		assertEquals(driver.findElement(mobilePhoneTextboxBy).getAttribute("value"),mobile);
		assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"),email);
		
		//Step 9: Edit customer với giá trị mới và set
		driver.findElement(addressTextareaBy).clear();
		driver.findElement(addressTextareaBy).sendKeys(editAddress);
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(mobilePhoneTextboxBy).clear();
		driver.findElement(mobilePhoneTextboxBy).sendKeys(editMobile);
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		driver.findElement(submitButtonBy).click();
		sleepInSecond(1);
		
		//Step 10: Check Customer đã được edit thành công.
		assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and contains(text(),'Customer details updated Successfully')]")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editMobile);
		assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	}

	@AfterClass
	public void afterClass() {
		//clean up data: delete customer
		driver.findElement(By.linkText("Delete Customer")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		driver.switchTo().alert().accept();
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