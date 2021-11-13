package com.nopcommerce.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObjext;

public class TC_Login {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	String emailAddress;
	String password;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPageObject(driver);
		homePage = new HomePageObject(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage.openBrowser(driver,"https://demo.nopcommerce.com");
	}
	
	@BeforeClass
	public void beforeClass() {
		RegisterPageObjext registerPage = new RegisterPageObjext(driver);
		
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		password = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		homePage.clickRegisterLink();
		registerPage.selectMaleGender();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		registerPage.inputCompany (company);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword(confirmPassword);
		registerPage.clickRegisterButton();
		homePage.clickLogOutLink();
	}
	
	@Test
	public void TC_01_Login_Empty() {
		homePage.clickLogInLink();
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}
	
	@Test
	public void TC_02_Login_Invalid_Email() {
		homePage.clickLogInLink();
		loginPage.inputEmail("Test");
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");
	}
	
	@Test
	public void TC_03_Login_Not_Registered_Email() {
		homePage.clickLogInLink();
		loginPage.inputEmail("Testing123@yopmail.com");
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"No customer account found");
	}
	
	@Test
	public void TC_04_Login_With_Empty_Password() {
		homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.clickLoginButton();
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");	
	}
	
	@Test
	public void TC_05_Login_With_Wrong_Password() {
		homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword("123457");
		loginPage.clickLoginButton();
		
		assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again."+"\n"+"The credentials provided are incorrect");
	}
	
	@Test
	public void TC_06_Login_Successfully() {
		homePage.clickLogInLink();
		loginPage.inputEmail(emailAddress);
		loginPage.inputPassword(password);
		loginPage.clickLoginButton();
		
		homePage.clickMyAccountLink();
		assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
