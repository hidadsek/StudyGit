package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_RadioButton_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	boolean checkboxStatus = false;
	boolean radioButtonStatus = false;

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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_CheckButton() {
		String username = "thucTest"+randomNumber()+"@gmail.com";
		String password = "Test@123";
		By dangNhapButtonBy = By.className("fhs_top_account_login_button");
		By emailPopupTextboxBy = By.cssSelector(".popup-login-content #login_username");
		By passwordPopupTextboxBy = By.cssSelector(".popup-login-content #login_password");
		By dangNhapPopupButtonBy = By.cssSelector(".popup-login-content button.fhs-btn-login");
		//Step 1: Truy cập trang
		driver.get("https://www.fahasa.com/customer/account/create");
		//Step 2: Click Đăng Nhập và check nút đăng nhập trong pop-up bị disabled  
		driver.findElement(dangNhapButtonBy).click();
		assertFalse(driver.findElement(dangNhapPopupButtonBy).isEnabled());
		//Step 3: Input email và password và check nút đăng nhập trong pop-up được enabled
		driver.findElement(emailPopupTextboxBy).sendKeys(username);
		driver.findElement(passwordPopupTextboxBy).sendKeys(password);
		assertTrue(driver.findElement(dangNhapPopupButtonBy).isEnabled());
		//Step 4: refresh page và click lại đăng nhập
		driver.navigate().refresh();
		driver.findElement(dangNhapButtonBy).click();
		//Step 5: Xóa attribute, click Đăng Nhập và check validation 
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(dangNhapPopupButtonBy));
		sleepInSecond(1);
		driver.findElement(dangNhapPopupButtonBy).click();
		assertEquals(driver.findElement(
				By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']")).getText(),
				"Thông tin này không thể để trống");
		assertEquals(driver.findElement(
				By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']")).getText(),
				"Thông tin này không thể để trống");
		
		// Verify login button with background color = RED
		String rgbaColor = driver.findElement(dangNhapPopupButtonBy).getCssValue("background-color");
		System.out.println("RGBA = " + rgbaColor);

		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		System.out.println("Hexa = " + hexaColor);

		assertEquals(hexaColor, "#C92127");
		}

	//@Test
	public void TC_02_CheckDefaultCheckBoxAndRadioButton() {
		//Step 1: Truy cập trang và kiểm tra checkbox
		String checkboxItem = "Dual-zone air conditioning";
		By checkboxBy = By.xpath("//label[@class='k-checkbox-label' and text()='"+checkboxItem+"']//preceding-sibling::input");
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		tickCheckbox(checkboxBy,checkboxBy);
		assertTrue(checkboxStatus);
		untickCheckbox(checkboxBy,checkboxBy);
		assertFalse(checkboxStatus);
		//Step 2: Truy cập trang và kiểm tra radio button
		String radioButtonItem = "2.0 Petrol, 147kW";
		By radioButtonBy = By.xpath("//label[@class='k-radio-label' and text()='"+radioButtonItem+"']//preceding-sibling::input");
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		assertFalse(radioButtonStatus);
		clickRadioButton(radioButtonBy,radioButtonBy);
		assertTrue(radioButtonStatus);	
	}

	//@Test
	public void TC_03_CheckCustomCheckBoxAndRadioButton() {
		//Step 1: Truy cập trang và kiểm tra radio button
		String radioButtonItem = "Summer";
		By radioButtonVerifyBy = By.xpath("//input[@value='"+radioButtonItem+"']");
		By radioButtonClickBy = By.xpath("//span[contains(text(),'"+radioButtonItem+"')]");
		driver.get("https://material.angular.io/components/radio/examples");
		
		WebElement googleOkButton = driver.findElement(By.xpath("//span[@class='mat-button-wrapper' and text()='Ok, Got it']"));
		googleOkButton.click();
		
		clickRadioButton(radioButtonClickBy,radioButtonVerifyBy);
		assertTrue(radioButtonStatus);
		
		clickToElementByJS(By.xpath("//input[@value='Winter']"));
		assertTrue(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());
		
		
		//Step 2: Truy cập trang và kiểm tra checkbox		
		String[] checboxList = {"Checked","Indeterminate"};
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		for (String checkboxItem:checboxList) {
			By checkboxVerifyBy = By.xpath("//span[text()='"+checkboxItem+"']//preceding-sibling::span/input");
			By checkboxClickBy = By.xpath("//span[text()='"+checkboxItem+"']");
			tickCheckbox(checkboxClickBy,checkboxVerifyBy);
			assertTrue(checkboxStatus);
			untickCheckbox(checkboxClickBy,checkboxVerifyBy);
			assertFalse(checkboxStatus);
		}
	}
	
	//@Test
	public void TC_04_CheckSpecialCheckBoxAndRadioButton() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		String city = "Hà Nội";
		WebElement cityRadioButton = driver.findElement(By.xpath("//div[@data-value='"+city+"' and @role='radio']"));
		cityRadioButton.click();
		assertTrue(cityRadioButton.getAttribute("class").contains("isChecked"));
		
		List<WebElement> cityCheckboxList = driver.findElements(By.xpath("//div[starts-with(@data-answer-value,'Quảng')]"));
		for (WebElement cityCheckbox: cityCheckboxList) {
			cityCheckbox.click();
			sleepInSecond(1);
			System.out.println(cityCheckbox.getAttribute("class"));
			assertTrue(cityCheckbox.getAttribute("class").contains("isChecked"));
		}
	}
	
	@Test
	public void TC_07_Live_Guru() {
		driver.get("https://live.demoguru99.com/index.php/backendlogin");
		//user01/guru99com
		
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
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	public void tickCheckbox(By clickBy, By verifyBy) {
		if(!driver.findElement(verifyBy).isSelected()) {
			driver.findElement(clickBy).click();
			sleepInSecond(1);
		}
		checkboxStatus = driver.findElement(verifyBy).isSelected();
	}
	
	public void untickCheckbox(By clickBy, By verifyBy) {
		if(driver.findElement(verifyBy).isSelected()) {
			driver.findElement(clickBy).click();
			sleepInSecond(1);
		}
		checkboxStatus = driver.findElement(verifyBy).isSelected();
	}
	
	public void clickToElementByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
	}
	
	public void clickRadioButton(By clickBy, By verifyBy) {
		if(!driver.findElement(verifyBy).isSelected()) {
			driver.findElement(clickBy).click();
			sleepInSecond(1);
		}
		radioButtonStatus = driver.findElement(verifyBy).isSelected();
	}
}
