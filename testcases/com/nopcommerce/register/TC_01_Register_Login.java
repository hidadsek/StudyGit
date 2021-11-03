package com.nopcommerce.register;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.RegisterPageObjext;

public class TC_01_Register_Login {
	WebDriver driver;
	RegisterPageObjext registerPage;
	String emailAddress;
	String firstName = "Thuc";
	String lastName= "Nguyen";
	String company = "Livegroup";
	String password = "123456";
	String confirmPassword= "123456";
	String day = "5";
	String month = "May";
	String year = "1995";
	
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		registerPage = new RegisterPageObjext();
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Register_Empty_Data() {
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
		registerPage.clickRegister(driver);
		
		assertEquals(registerPage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		assertEquals(registerPage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		assertEquals(registerPage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		assertEquals(registerPage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		assertEquals(registerPage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}
	
	
	//@Test
	public void TC_02_Register_Invalid_Email() {
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
		registerPage.inputEmail(driver, "Test123");
		registerPage.clickRegister(driver);
		
		assertEquals(registerPage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	//@Test
	public void TC_03_Register_Sucessfully() {
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
		
		registerPage.clickElement(driver, "//a[text()='Log out']");
		registerPage.sleepInSecond(1);
	}
	
	//@Test
	public void TC_04_Register_Existed_Email() {
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.inputEmail(driver, "test123@gmail.com");
		registerPage.inputPassword(driver, password);
		registerPage.inputConfirmPassword(driver, confirmPassword);
		registerPage.clickRegister(driver);
		
		assertEquals(registerPage.getElementText(driver, "//div[contains(@class,'validation-summary-errors')]//li"), "The specified email already exists");
	}
	
	//@Test
	public void TC_05_Password_Less_Than_6() {
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.inputEmail(driver, emailAddress);
		registerPage.inputPassword(driver, "12345");
		registerPage.inputConfirmPassword(driver, "12345");
		registerPage.clickRegister(driver);
		
		assertEquals(registerPage.getElementAttribute(driver, "//span[@id='Password-error']","textContent"), "Password must meet the following rules: must have at least 6 characters");
	}
	
	@Test
	public void TC_06_Incorrect_Confirm_Password() {
		registerPage.openBrowser(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
		registerPage.inputFirstName(driver, firstName);
		registerPage.inputLastName(driver, lastName);
		registerPage.inputEmail(driver, emailAddress);
		registerPage.inputPassword(driver, password);
		registerPage.inputConfirmPassword(driver, "12345");
		registerPage.clickRegister(driver);
		
		assertEquals(registerPage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
