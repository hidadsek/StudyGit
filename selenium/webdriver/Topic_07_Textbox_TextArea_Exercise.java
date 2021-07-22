package webdriver;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//Login page value
	String username = "mngr342759";
	String password = "yqarUjY";
	
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
		
		// Step 2: Log in và verify homepage
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		assertEquals(driver.getCurrentUrl(),"http://demo.guru99.com/v4/manager/Managerhomepage.php");
		assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText().contains(username));
				
		
	}

	@Test	
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
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
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
		assertFalse(driver.findElement(customerNameTextbox).isEnabled());
		assertFalse(driver.findElement(By.name("gender")).isEnabled());
		assertFalse(driver.findElement(DOBTextbox).isEnabled());
		assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"),customerName);
		assertEquals(driver.findElement(addressTextarea).getText(),address);
		assertEquals(driver.findElement(cityTextbox).getAttribute("value"),city);
		assertEquals(driver.findElement(stateTextbox).getAttribute("value"),state);
		assertEquals(driver.findElement(pinTextbox).getAttribute("value"),pin);
		assertEquals(driver.findElement(mobilePhoneTextbox).getAttribute("value"),mobile);
		assertEquals(driver.findElement(emailTextbox).getAttribute("value"),email);
		
		//Step 9: Edit customer với giá trị mới và set
		driver.findElement(addressTextarea).clear();
		driver.findElement(addressTextarea).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(mobilePhoneTextbox).clear();
		driver.findElement(mobilePhoneTextbox).sendKeys(editMobile);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);
		driver.findElement(submitButton).click();
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