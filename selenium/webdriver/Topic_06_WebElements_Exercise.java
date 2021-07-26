package webdriver;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElements_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By emailCheckBox = By.id("mail");
	By under18RadioButton = By.id("under_18");
	By educationTextArea = By.id("edu");
	
	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Chrome Driver
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_VerifyElementDisplay() {
		// Step 1: Mở website
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Step 1.1 Tạo biến
		WebElement emailTextbox = driver.findElement(emailCheckBox);
		WebElement age = driver.findElement(under18RadioButton);
		WebElement education = driver.findElement(educationTextArea);
		WebElement user5 = driver.findElement(By.xpath("//h5[contains(text(),'User5')]"));
				
		// Step 2: Kiểm tra phần tử có hiện trên trang không
		assertTrue(emailTextbox.isDisplayed());
		assertTrue(age.isDisplayed());
		assertTrue(education.isDisplayed());
		
		// Step 3: Kiểm tra phần tử không hiển thị trên trang
		assertFalse(user5.isDisplayed());
		
		// Step 4: Nhập giá trị và in ra màn hình
		inputValueToElement(emailTextbox,"Automation Testing");
		inputValueToElement(education,"Automation Testing");
		age.click();
		
		isElementDisplayed(emailTextbox);
		isElementDisplayed(age);
		isElementDisplayed(education);
		isElementDisplayed(user5);			
	}
	
	
	public void TC_02_VerifyElementEnabledDisabled() {
		// Step 1: Mở website
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Step 1.1 Tạo biến		
		WebElement emailTextbox = driver.findElement(emailCheckBox);
		WebElement age = driver.findElement(under18RadioButton);
		WebElement education = driver.findElement(educationTextArea);
		WebElement job1 = driver.findElement(By.name("user_job1"));
		WebElement job2 = driver.findElement(By.name("user_job2"));
		WebElement development = driver.findElement(By.id("development"));
		WebElement slider = driver.findElement(By.id("slider-1"));
		
		WebElement passwordTextbox = driver.findElement(By.id("passwordTextbox"));
		WebElement age2 = driver.findElement(By.id("radio-disabled"));
		WebElement biography = driver.findElement(By.id("bio"));
		WebElement job3 = driver.findElement(By.id("job3"));
		WebElement interest = driver.findElement(By.id("check-disbaled"));
		WebElement slider2 = driver.findElement(By.id("slider-2"));
				
		// Step 2: Kiểm tra phần tử có được enabled trên trang không
		assertTrue(emailTextbox.isEnabled());
		assertTrue(age.isEnabled());
		assertTrue(education.isEnabled());
		assertTrue(job1.isEnabled());
		assertTrue(job2.isEnabled());
		assertTrue(slider.isEnabled());
		assertTrue(development.isEnabled());
		
		// Step 3: Kiểm tra phần tử bị disabled trên trang
		assertFalse(passwordTextbox.isEnabled());
		assertFalse(age2.isEnabled());
		assertFalse(biography.isEnabled());
		assertFalse(job3.isEnabled());
		assertFalse(interest.isEnabled());
		assertFalse(slider2.isEnabled());
		
		// Step 4: log message
		isElementEnabled(emailTextbox);
		isElementEnabled(age);
		isElementEnabled(education);
		isElementEnabled(job1);
		isElementEnabled(job2);
		isElementEnabled(slider);
		isElementEnabled(development);
		isElementEnabled(passwordTextbox);
		isElementEnabled(age2);
		isElementEnabled(biography);
		isElementEnabled(job3);
		isElementEnabled(interest);
		isElementEnabled(slider2);

	}
	
	
	public void TC_03_VerifyElementSelected() {
		// Step 1: Mở website
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Step 1.1 Tạo biến
		WebElement age = driver.findElement(under18RadioButton);
		WebElement languageJava = driver.findElement(By.id("java"));
		
		// Step 2: Select age: Under 18 và Language: Java checkbox
		age.click();
		languageJava.click();
		
		// Step 3: Kiểm tra phần tử được selected trên trang
		assertTrue(age.isSelected());
		assertTrue(languageJava.isSelected());
		
		// Step 4: De-Select Language: Java checkbox
		languageJava.click();
		
		// Step 5: Kiểm tra phần tử được de-selected trên trang
		assertFalse(languageJava.isSelected());
		
		// Step 6: log message
		isElementSelected(age);
		isElementSelected(languageJava);
	}
	
	@Test
	public void TC_04_RegisterMailChimp() {
		// Step 1: Mở website
		driver.get("https://login.mailchimp.com/signup/");
		
		// Step 1.1 Tạo biến
		WebElement emailTextbox = driver.findElement(By.id("email"));
		WebElement usernameTextbox = driver.findElement(By.id("new_username"));
		WebElement passwordTextbox = driver.findElement(By.id("new_password"));
		WebElement signUpButton = driver.findElement(By.id("create-account"));
		
		//Step 2: Nhập dữ liệu hợp lệ vào emailTextbox và usernameTextbox
		inputValueToElement(emailTextbox,"nguyendongthuc13@gmail.com");
		inputValueToElement(usernameTextbox,"hidadsek");
		
		//Step 3.1: Nhập pass chỉ có số
		inputValueToElement(passwordTextbox,"123");
		WebElement numberValidation = 
				driver.findElement(By.xpath("//li[contains(@class,'completed') and text()='One number']"));
		assertTrue(numberValidation.isDisplayed());
		assertFalse(signUpButton.isEnabled());
		
		//Step 3.2: Nhập pass chỉ có chữ thường
		inputValueToElement(passwordTextbox,"test");
		WebElement lowercaseValidation = 
				driver.findElement(By.xpath("//li[contains(@class,'completed') and text()='One lowercase character']"));
		assertTrue(lowercaseValidation.isDisplayed());
		assertFalse(signUpButton.isEnabled());
		assertFalse(signUpButton.isEnabled());
		
		//Step 3.3: Nhập pass chỉ có chữ hoa
		inputValueToElement(passwordTextbox,"TEST");
		WebElement uppercaseValidation = 
				driver.findElement(By.xpath("//li[contains(@class,'completed') and text()='One uppercase character']"));
		assertTrue(uppercaseValidation.isDisplayed());
		assertFalse(signUpButton.isEnabled());
		
		//Step 3.4: Nhập pass có chữ special
		inputValueToElement(passwordTextbox,"T&T");
		WebElement specialCharValidation = 
				driver.findElement(By.xpath("//li[contains(@class,'completed') and text()='One special character']"));
		assertTrue(specialCharValidation.isDisplayed());
		assertFalse(signUpButton.isEnabled());
		
		//Step 3.5: Nhập pass có nhiều hơn 8 ký tự
		inputValueToElement(passwordTextbox,"testing1");
		WebElement moreThan8CharValidation = 
				driver.findElement(By.xpath("//li[contains(@class,'completed') and text()='8 characters minimum']"));
		assertTrue(moreThan8CharValidation.isDisplayed());
		assertFalse(signUpButton.isEnabled());
		
		//Step 4: Nhập valid password
		inputValueToElement(passwordTextbox, "Test@1234");
		assertFalse(numberValidation.isDisplayed());
		assertFalse(lowercaseValidation.isDisplayed());
		assertFalse(uppercaseValidation.isDisplayed());
		assertFalse(specialCharValidation.isDisplayed());
		assertFalse(moreThan8CharValidation.isDisplayed());
		assertTrue(driver.findElement(By.cssSelector(".c-media h4")).isDisplayed());
		assertTrue(signUpButton.isEnabled());
		
		//Step 5: Check checkbox được selected
		WebElement newsLetter = driver.findElement(By.id("marketing_newsletter"));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		newsLetter.click();
		assertTrue(newsLetter.isSelected());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void inputValueToElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	public void isElementSelected(WebElement element) {
		if(element.isSelected())
			System.out.println("Element is selected");
		else System.out.println("Element is de-selected");
	}
	public void isElementEnabled(WebElement element) {
		if (element.isEnabled()) 
			System.out.println("Element is enabled");
		else System.out.println("Element is disabled");
	}
	public void isElementDisplayed(WebElement element) {
		if (element.isDisplayed()) 
			System.out.println("Element is displayed");
		else System.out.println("Newbie");
	}
}
