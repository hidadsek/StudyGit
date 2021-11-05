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

import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObjext;

public class TC_Login {
	WebDriver driver;
	LoginPageObject loginPage;
	String emailAddress;
	String password;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPageObject();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void beforeClass() {
		RegisterPageObjext registerPage = new RegisterPageObjext();
		
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		password = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
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
		registerPage.clickRegister(driver);
		
		assertEquals(registerPage.getElementText(driver,"//*[@class='result']"), "Your registration completed");
		System.out.println(emailAddress);
		registerPage.clickElement(driver, "//a[text()='Log out']");
		registerPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_01_Login_Empty() {
		loginPage.openBrowser(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage.clickLogin(driver);
		
		assertEquals(loginPage.getElementText(driver, "//span[@id='Email-error']"), "Please enter your email");
	}
	
	@Test
	public void TC_02_Login_Invalid_Email() {
		loginPage.openBrowser(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage.inputEmail(driver, "Test");
		loginPage.clickLogin(driver);
		
		assertEquals(loginPage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_03_Login_Not_Registered_Email() {
		loginPage.openBrowser(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage.inputEmail(driver, "Testing123@yopmail.com");
		loginPage.clickLogin(driver);
		
		assertTrue(loginPage.getElementAttribute(driver, "//div[contains(@class,'validation-summary-errors')]","textContent")
				.contains("Login was unsuccessful. Please correct the errors and try again.No customer account found"));
	}
	
	@Test
	public void TC_04_Login_With_Empty_Password() {
		loginPage.openBrowser(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage.inputEmail(driver, emailAddress);
		loginPage.clickLogin(driver);
		System.out.println(loginPage.getElementAttribute(driver, "//div[contains(@class,'validation-summary-errors')]","textContent"));
		assertTrue(loginPage.getElementAttribute(driver, "//div[contains(@class,'validation-summary-errors')]","innerText")
				.contains("Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect"));	
	}
	
	@Test
	public void TC_05_Login_With_Wrong_Password() {
		loginPage.openBrowser(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage.inputEmail(driver, emailAddress);
		loginPage.inputPassword(driver, "123457");
		loginPage.clickLogin(driver);
		
		assertTrue(loginPage.getElementAttribute(driver, "//div[contains(@class,'validation-summary-errors')]","innerText")
				.contains("Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect"));
	}
	
	@Test
	public void TC_06_Login_Successfully() {
		loginPage.openBrowser(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
		loginPage.inputEmail(driver, emailAddress);
		loginPage.inputPassword(driver, password);
		loginPage.clickLogin(driver);
		
		loginPage.clickElement(driver, "//div[@class ='header-links']//a[text()='My account']");
		assertTrue(loginPage.getElementText(driver, "//div[@class ='page-title']/h1")
				.contains("My account - Customer info"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
