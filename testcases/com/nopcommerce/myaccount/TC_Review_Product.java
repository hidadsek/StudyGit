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

import pageObjects.MyProductReviewPageObject;
import pageObjects.ProductPageObject;
import pageObjects.ProductReviewPageObject;
import pageObjects.RegisterPageObject;
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
		registerPage.sleepInSecond(1);
	}
	
	@Test
	public void TC_01_Review_Product() {	
		SearchPageObject searchPage = new SearchPageObject(driver);
		String searchValue = "Build your own computer";	
		searchPage.inputSearch(searchValue);
		searchPage.clickSearch();
		searchPage.selectProduct(searchValue);
		
		ProductPageObject productPage = new ProductPageObject(driver);
		productPage.clickReview();
		
		ProductReviewPageObject productReviewPage = new ProductReviewPageObject(driver);
		String reviewTitle = "Order "+ productReviewPage.getRandomNumber();
		String reviewText = "Testing" + productReviewPage.getRandomNumber();
		String rating = "3";
		productReviewPage.inputReviewTitle(reviewTitle);
		productReviewPage.inputReviewText(reviewText);
		productReviewPage.clickRating(rating);
		productReviewPage.clickSubmitReview();
		
		MyProductReviewPageObject myReviewPage = new MyProductReviewPageObject(driver);
		myReviewPage.openMyReviewPage(driver);
		assertEquals(myReviewPage.getElementText(driver, "//div[@class='review-title']/strong"), reviewTitle);
		assertEquals(myReviewPage.getElementText(driver, "//div[@class='review-text']"), reviewText);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
