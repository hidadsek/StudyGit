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
import pageObjects.RegisterPageObject;

public class TC_Add_New_Address {
	WebDriver driver;
	AddressPageObject addressPage;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		addressPage = new AddressPageObject(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void beforeClass() {
		RegisterPageObject registerPage = new RegisterPageObject(driver);
		
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
		
		addressPage.clickAddNewButton();
		addressPage.inputFirstName(addressFirstName);
		addressPage.inputLastName(addressLastName);
		addressPage.inputEmail(addressEmail);
		//addressPage.inputCompany(driver, addressCompany);
		addressPage.selectCountry(addressCountry);
		addressPage.selectState(addressState);
		addressPage.inputCity(addressCity);
		addressPage.inputAddress1(address1);
		addressPage.inputAddress2(address2);
		addressPage.inputZip(addressZip);
		addressPage.inputPhoneNumber(addressPhoneNumber);
		//addressPage.inputFaxNumber(driver, addressFaxNumber);
		addressPage.clickSaveButton();
		
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
