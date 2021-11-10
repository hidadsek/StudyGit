package com.nopcommerce.myaccount;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AddressPageObject;
import pageObjects.RegisterPageObjext;

public class TC_Add_New_Address {
	WebDriver driver;
	AddressPageObject addressPage;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		addressPage = new AddressPageObject();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void beforeClass() {
		RegisterPageObjext registerPage = new RegisterPageObjext();
		
		String emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		String password = "123456";
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
		
		addressPage.openAddressPage(driver);
		addressPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_Update_Address() {
		String addressFirstName = "Automation";
		String addressLastName = "FC";
		String addressEmail = "automationnfc@gmail.com";
		String addressCompany = "Automation FBC";
		String addressCountry = "Viet Nam";
		String addressState = "Other";
		String addressCity = "Ho Chi Minh";
		String address1 = "Test 123";
		String address2 = "NTL 456";
		String addressZip = "550000";
		String addressPhoneNumber = "0123456789";
		String addressFaxNumber = "0987456123";
		
		addressPage.clickAddNewButton(driver);
		addressPage.inputFirstName(driver, addressFirstName);
		addressPage.inputLastName(driver, addressLastName);
		addressPage.inputEmail(driver, addressEmail);
		//addressPage.inputCompany(driver, addressCompany);
		addressPage.selectCountry(driver, addressCountry);
		addressPage.selectState(driver, addressState);
		addressPage.inputCity(driver, addressCity);
		addressPage.inputAddress1(driver, address1);
		addressPage.inputAddress2(driver, address2);
		addressPage.inputZip(driver, addressZip);
		addressPage.inputPhoneNumber(driver, addressPhoneNumber);
		//addressPage.inputFaxNumber(driver, addressFaxNumber);
		addressPage.clickSaveButton(driver);
		
		assertEquals(addressPage.getElementText(driver,"//li[@class='name']"), addressFirstName + " "+ addressLastName);
		assertEquals(addressPage.getElementText(driver,"//li[@class='email']"), "Email: "+ addressEmail);
		assertEquals(addressPage.getElementText(driver,"//li[@class='phone']"), "Phone number: "+ addressPhoneNumber);
		//assertEquals(addressPage.getElementText(driver,"//li[@class='fax']"), "Fax number: " +addressFaxNumber);
		//assertEquals(addressPage.getElementText(driver,"//li[@class='company']"), addressCompany);
		assertEquals(addressPage.getElementText(driver,"//li[@class='address1']"), address1);
		assertEquals(addressPage.getElementText(driver,"//li[@class='address2']"), address2);
		assertEquals(addressPage.getElementText(driver,"//li[@class='city-state-zip']"), addressCity+", "+addressZip);
		assertEquals(addressPage.getElementText(driver,"//li[@class='country']"), addressCountry);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
