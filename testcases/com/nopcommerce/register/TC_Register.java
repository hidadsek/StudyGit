package com.nopcommerce.register;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObjext;

public class TC_Register {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObjext registerPage;
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
		homePage = new HomePageObject();
		registerPage = new RegisterPageObjext();
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com");
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		homePage.clickRegisterLink(driver);
		registerPage.clickRegisterButton(driver);
		
		assertEquals(registerPage.getFirstNameErrorMessage(driver), "First name is required.");
		assertEquals(registerPage.getLastNameErrorMessage(driver), "Last name is required.");
		assertEquals(registerPage.getEmailErrorMessage(driver), "Email is required.");
		assertEquals(registerPage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		assertEquals(registerPage.getConfirmPasswordErrorMessage(driver), "Password is required.");
	}
	
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickRegisterLink(driver);
		registerPage.inputEmail(driver, "Test123");
		registerPage.clickRegisterButton(driver);
		
		assertEquals(registerPage.getEmailErrorMessage(driver), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Sucessfully() {
		homePage.clickRegisterLink(driver);
		registerPage.selectMaleGender(driver);
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.selectDay(driver, day);
		registerPage.selectMonth(driver, month);
		registerPage.selectYear(driver, year);
		registerPage.inputCompany(driver, company);
		registerPage.inputEmail(driver, emailAddress);
		registerPage.inputPassword(driver, password);
		registerPage.inputConfirmPassword(driver, confirmPassword);
		registerPage.clickRegisterButton(driver);
		
		assertEquals(registerPage.getSuccessMessage(driver), "Your registration completed");
		
		homePage.clickLogOutLink(driver);
		registerPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_04_Register_Existed_Email() {
		homePage.clickRegisterLink(driver);
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.inputEmail(driver, "test123@gmail.com");
		registerPage.inputPassword(driver, password);
		registerPage.inputConfirmPassword(driver, confirmPassword);
		registerPage.clickRegisterButton(driver);
		
		assertEquals(registerPage.getExistingEmailErrorMessage(driver), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Password_Less_Than_6() {
		homePage.clickRegisterLink(driver);
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.inputEmail(driver, emailAddress);
		registerPage.inputPassword(driver, "12345");
		registerPage.inputConfirmPassword(driver, "12345");
		registerPage.clickRegisterButton(driver);
		
		assertEquals(registerPage.getPasswordErrorMessage(driver), "Password must meet the following rules: must have at least 6 characters");
	}
	
	@Test
	public void TC_06_Incorrect_Confirm_Password() {
		homePage.clickRegisterLink(driver);
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.inputEmail(driver, emailAddress);
		registerPage.inputPassword(driver, password);
		registerPage.inputConfirmPassword(driver, "12345");
		registerPage.clickRegisterButton(driver);
		
		assertEquals(registerPage.getConfirmPasswordErrorMessage(driver), "The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
