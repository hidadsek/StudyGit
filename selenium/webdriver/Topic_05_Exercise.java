package webdriver;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Exercise {

	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String name, emailAddress, password, phone;
	
	By nameTextBoxBy = By.id("txtFirstname");
	By emailTextBoxBy = By.id("txtEmail");
	By confirmEmailTextBoxBy = By.id("txtCEmail");
	By passTextBoxBy = By.id("txtPassword");
	By confirmPassTextBoxBy = By.id("txtCPassword");
	By phoneTextBoxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//div[@class ='field_btn']/button");
	
	String projectPath = System.getProperty("user.dir");
	
	// precondition
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Mở application lên (AUT / SUT)		 
		
		name = "Thuc Nguyen";
		emailAddress = "automation@gmail.net";
		password = "123456";
		phone = "0987666555";
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Empty(){
		//Step:
		//1. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//2. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertTrue(driver.findElement(By.id("txtPhone-error")).getText().contains("Vui lòng nhập số điện thoại."));		
	}
	
	@Test
	public void TC_02_Invalid_Email(){
		//Step:
		//1. Input valid data to name, password, "confirm password" and phone field
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys(password);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		
		//2. Input invalid data to email and "confirm email"
		driver.findElement(emailTextBoxBy).sendKeys("123@123.234@");
		driver.findElement(confirmEmailTextBoxBy).sendKeys("123@123.234@");
		
		//3. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//4. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_03_Incorrect_Confirm_Email(){
		//Step:
		//1. Input valid data to name, email, password, "confirm password" and phone field		
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys(password);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		
		//2. Input incorrect "confirm email"
		driver.findElement(confirmEmailTextBoxBy).sendKeys("automation@gmail.com");
		
		//3. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//4. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Password_Less_Than_6_Chars(){
		//Step:
		//1. Input valid data to name, email,"confirm email" and phone field	
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		
		//2. Input invalid password and "confirm password": less than 6 characters
		driver.findElement(passTextBoxBy).sendKeys("123");
		driver.findElement(confirmPassTextBoxBy).sendKeys("123");
		
		//3. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//4. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Incorrect_Confirm_Password(){
		//Step:
		//1. Input valid data to name, email, "confirm email", password and phone field
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		
		//2. Input incorrect "confirm password"
		driver.findElement(confirmPassTextBoxBy).sendKeys("123457");
		
		//3. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//4. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Invalid_Phone(){
		//Step:
		//1. Input valid data to name, email, "confirm email", password and "confirm password" fields
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys("123457");
		
		//2. Input invalid phone : email format
		driver.findElement(phoneTextBoxBy).sendKeys(emailAddress);
		
		//3. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//4. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập con số");
		
		//5. Input invalid phone : less than 10
		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys("098766655");
		
		//6. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//7. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		//5.1. Input invalid phone : more than 11
		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys("098766655678");
		
		//6.1. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//7.1. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

		//8. Input invalid phone : not start with 09-03-012-016-018-019
		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys("1987666555");
		
		//9. Click button: ĐĂNG KÝ
		driver.findElement(registerButtonBy).click();
		
		//10. Verify error messages
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
