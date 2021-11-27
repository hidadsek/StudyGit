package com.switchpage;

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
import pageObjects.CustomerInfoPageObject;
import pageObjects.DownloadableProductPageObject;
import pageObjects.HomePageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.OrderPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class TC_01_NopCommerce_SwitchPage {
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void beforeClass() {
		homePage = PageGeneratorManager.getHomePage(driver);
		String emailAddress = "test"+ homePage.getRandomNumber()+"@gmail.com";		
		String firstName = "Thuc";
		String lastName= "Nguyen";
		String company = "Livegroup";
		String password = "123456";
		String confirmPassword= "123456";
		String day = "5";
		String month = "May";
		String year = "1995";
		
		homePage.openHomePage();
		registerPage = homePage.clickRegisterLink();
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
		registerPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_Navigate_Page() {	
		CustomerInfoPageObject customerInfoPage = registerPage.clickMyAccountLink(driver);
		AddressPageObject addressPage = customerInfoPage.openAddressPage(driver);
		OrderPageObject orderPage = addressPage.openOrderPage(driver);
		DownloadableProductPageObject downloadableProductPage = orderPage.openDownloadableProductPage(driver);
		MyProductReviewPageObject myProductReviewPage = downloadableProductPage.openMyProductReviewPage(driver);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
