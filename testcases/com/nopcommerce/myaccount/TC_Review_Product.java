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

import pageObjects.MyReviewPageObject;
import pageObjects.ProductPageObject;
import pageObjects.ProductReviewPageObject;
import pageObjects.RegisterPageObjext;
import pageObjects.SearchPageObject;

public class TC_Review_Product {
	WebDriver driver;	
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
		registerPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_01_Review_Product() {	
		SearchPageObject searchPage = new SearchPageObject();
		String searchValue = "Build your own computer";	
		searchPage.inputSearch(driver, searchValue);
		searchPage.clickSearch(driver);
		searchPage.selectProduct(driver, searchValue);
		
		ProductPageObject productPage = new ProductPageObject();
		productPage.clickReview(driver);
		
		ProductReviewPageObject productReviewPage = new ProductReviewPageObject();
		String reviewTitle = "Order "+ productReviewPage.getRandomNumber();
		String reviewText = "Testing" + productReviewPage.getRandomNumber();
		String rating = "3";
		productReviewPage.inputReviewTitle(driver, reviewTitle);
		productReviewPage.inputReviewText(driver, reviewText);
		productReviewPage.clickRating(driver, rating);
		productReviewPage.clickSubmitReview(driver);
		productReviewPage.clickElement(driver, "//div[@class ='header-links']//a[text()='My account']");
		
		MyReviewPageObject myReviewPage = new MyReviewPageObject();
		myReviewPage.openMyReviewPage(driver);
		assertEquals(myReviewPage.getElementText(driver, "//div[@class='review-title']/strong"), reviewTitle);
		assertEquals(myReviewPage.getElementText(driver, "//div[@class='review-text']"), reviewText);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
