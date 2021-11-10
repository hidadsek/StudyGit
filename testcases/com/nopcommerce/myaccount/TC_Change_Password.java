package com.nopcommerce.myaccount;

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

import pageObjects.ChangePasswordPageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObjext;

public class TC_Change_Password {
	WebDriver driver;
	ChangePasswordPageObject changePasswordPage;
	String password;
	String emailAddress;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		changePasswordPage = new ChangePasswordPageObject();
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
		registerPage.clickElement(driver, "//div[@class ='header-links']//a[text()='My account']");
		changePasswordPage.openChangePasswordPage(driver);
		changePasswordPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_01_Change_Password() {
		String newPassword = "123457";
		
		changePasswordPage.inputOldPassword(driver, password);
		changePasswordPage.inputNewPassword(driver, newPassword);
		changePasswordPage.inputConfirmPassword(driver, newPassword);
		changePasswordPage.clickChangePassword(driver);
		
		assertEquals(changePasswordPage.getElementText(driver,"//div[@class='bar-notification success']/p"), "Password was changed");
		
		changePasswordPage.closeSuccessPopUp(driver);
		changePasswordPage.sleepInSecond(1);
		changePasswordPage.clickElement(driver, "//div[@class='header-links']//a[text()='Log out']");
		
		LoginPageObject loginPage = new LoginPageObject();
		loginPage.clickElement(driver, "//div[@class='header-links']//a[text()='Log in']");
		loginPage.inputEmail(driver, emailAddress);
		loginPage.inputPassword(driver, password);
		loginPage.clickLogin(driver);
		assertTrue(loginPage.getElementAttribute(driver, "//div[contains(@class,'validation-summary-errors')]","innerText")
				.contains("Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect"));
		
		loginPage.inputEmail(driver, emailAddress);
		loginPage.inputPassword(driver, newPassword);
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
