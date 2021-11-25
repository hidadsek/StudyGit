package pageObjectInit;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_Register {
	private WebDriver driver;
	private HomePageObjectInit homePage;
	private RegisterPageObjextInit registerPage;
	private String emailAddress;
	private String firstName = "Thuc";
	private String lastName= "Nguyen";
	private String company = "Livegroup";
	private String password = "123456";
	private String confirmPassword= "123456";
	private String day = "5";
	private String month = "May";
	private String year = "1995";
	
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage.openBrowser(driver,"https://demo.nopcommerce.com");
		homePage = new HomePageObjectInit(driver);
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		registerPage = homePage.clickRegisterLink();
		registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
	}
	
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		registerPage = homePage.clickRegisterLink();
		registerPage.inputEmail("Test123");
		registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Sucessfully() {
		registerPage = homePage.clickRegisterLink();
		registerPage.selectMaleGender();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		registerPage.inputCompany(company);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword(confirmPassword);
		registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickLogOutLink();
		homePage.sleepInSecond(1);
	}
	
	@Test
	public void TC_04_Register_Existed_Email() {
		registerPage = homePage.clickRegisterLink();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.inputEmail("test123@gmail.com");
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword(confirmPassword);
		registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getExistingEmailErrorMessage(), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Password_Less_Than_6() {
		registerPage = homePage.clickRegisterLink();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword("12345");
		registerPage.inputConfirmPassword("12345");
		registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:"+"\n"+"must have at least 6 characters");
	}
	
	@Test
	public void TC_06_Incorrect_Confirm_Password() {
		registerPage = homePage.clickRegisterLink();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword("12345");
		registerPage.clickRegisterButton();
		
		assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
