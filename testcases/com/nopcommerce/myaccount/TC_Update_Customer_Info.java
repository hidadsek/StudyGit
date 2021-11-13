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

import pageObjects.CustomerInfoPageObject;
import pageObjects.RegisterPageObjext;

public class TC_Update_Customer_Info {
	WebDriver driver;
	CustomerInfoPageObject customerInfoPage;
	String firstName;
	String lastName;
	String company;
	String day;
	String month;
	String year;
	String emailAddress;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		customerInfoPage = new CustomerInfoPageObject();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void beforeClass() {
		RegisterPageObjext registerPage = new RegisterPageObjext();
		
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";		
		firstName = "Thuc";
		lastName= "Nguyen";
		company = "Livegroup";
		String password = "123456";
		String confirmPassword= "123456";
		day = "5";
		month = "May";
		year = "1995";
		
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
		registerPage.clickRegisterButton(driver);
		assertEquals(registerPage.getElementText(driver,"//*[@class='result']"), "Your registration completed");
		registerPage.clickElement(driver, "//div[@class ='header-links']//a[text()='My account']");
		registerPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_01_Update_Customer_Info() {
		firstName = "Automation";
		lastName = "FC";
		day="1";
		month="January";
		year="1990";
		emailAddress = "automationfc.vn@gmail.com";
		company ="Automation FC";
		
		customerInfoPage.selectFemaleGender(driver);
		customerInfoPage.inputFirstName(driver, firstName);
		customerInfoPage.inputLastName(driver, lastName);
		customerInfoPage.selectDay(driver, day);
		customerInfoPage.selectMonth(driver, month);
		customerInfoPage.selectYear(driver, year);
		customerInfoPage.inputEmail(driver, emailAddress);
		customerInfoPage.inputCompany(driver, company);
		customerInfoPage.clickSave(driver);
		
		assertTrue(customerInfoPage.isElementSelected(driver, customerInfoPage.customerInfoPageUI.femaleRadioButtonBy));
		assertEquals(customerInfoPage.getElementAttribute(driver, customerInfoPage.customerInfoPageUI.firstNameTextboxBy, "value"),firstName);
		assertEquals(customerInfoPage.getElementAttribute(driver, customerInfoPage.customerInfoPageUI.lastNameTextboxBy, "value"),lastName);
		assertEquals(customerInfoPage.getItemInDefaultDropdown(driver, customerInfoPage.customerInfoPageUI.dayDropdownListBy),day);
		assertEquals(customerInfoPage.getItemInDefaultDropdown(driver, customerInfoPage.customerInfoPageUI.monthDropdownListBy),month);
		assertEquals(customerInfoPage.getItemInDefaultDropdown(driver, customerInfoPage.customerInfoPageUI.yearDropdownListBy),year);
		assertEquals(customerInfoPage.getElementAttribute(driver, customerInfoPage.customerInfoPageUI.emailTextboxBy, "value"),emailAddress);
		assertEquals(customerInfoPage.getElementAttribute(driver, customerInfoPage.customerInfoPageUI.companyTextboxBy, "value"),company);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
