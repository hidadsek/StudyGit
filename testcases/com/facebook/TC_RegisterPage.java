package com.facebook;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_RegisterPage {
	WebDriver driver;
	RegisterPageObject registerPage;
	
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		registerPage = new RegisterPageObject(driver);
	}
	
	@Test
	public void TC_01_ElementDisplay() {	
		registerPage.openBrowser(driver, "https://www.facebook.com/");
		registerPage.clickRegisterButton();
		assertTrue(registerPage.isEmailTextboxDisplayed());
	}
	
	@Test
	public void TC_02_Element_Not_Display() {
		registerPage.openBrowser(driver, "https://www.facebook.com/");
		registerPage.clickRegisterButton();
		assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
	}
	
	@Test
	public void TC_03_Element_Not_Found_In_DOM() {
		registerPage.openBrowser(driver, "https://www.facebook.com/login.php");
		registerPage.clickRegisterLink();
		assertTrue(registerPage.isLoginButtonNotDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
