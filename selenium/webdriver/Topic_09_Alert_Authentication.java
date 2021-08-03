package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert_Authentication {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String chormeAuthoIT = projectPath+"\\authoAutoIT\\authen_chrome.exe";
	String firefoxAuthoIT = projectPath+"\\authoAutoIT\\authen_firefox.exe";
	String username ="admin";
	String password ="admin";
	String url = "http://the-internet.herokuapp.com/basic_auth";

	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		/*
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		*/
		// Chrome Driver
	}

	//@Test
	public void TC_01_AuthenticationAlertChrome() {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get(getUrlWithUserNameAndPass(url,username,password));
		String message ="Congratulations! You must have the proper credentials.";
		assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains(message));
		driver.quit();
	}

	//@Test
	public void TC_02_AuthenticationAlertFirefox() {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get(getUrlWithUserNameAndPass(url,username,password));
		String message ="Congratulations! You must have the proper credentials.";
		assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains(message));
		driver.quit();
	}

	//@Test
	public void TC_03_AuthenticationAlertEdge() {
		System.setProperty("webdriver.edge.driver", projectPath+"\\driverBrowsers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get(getUrlWithUserNameAndPass(url,username,password));	
		String message ="Congratulations! You must have the proper credentials.";
		assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains(message));
		driver.quit();
	}
	//@Test
	public void TC_04_AuthenticationAlertChromeAutoIT() throws IOException {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		Runtime.getRuntime().exec(new String[] {chormeAuthoIT,username,password});
		driver.get(url);
		String message ="Congratulations! You must have the proper credentials.";
		assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains(message));
		driver.quit();
	}

	@Test
	public void TC_05_AuthenticationAlertFirefoxAutoIT() throws IOException {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		Runtime.getRuntime().exec(new String[] {firefoxAuthoIT,username,password});
		driver.get(url);
		sleepInSecond(10);
		String message ="Congratulations! You must have the proper credentials.";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'"+message+"')]")));
		assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+message+"')]")).isDisplayed());
		driver.quit();
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getUrlWithUserNameAndPass(String url, String username, String password) {
		String[] hrefValue = url.split("//");
		url = hrefValue[0]+ username+":"+password+"@"+hrefValue[1];
		return url;
	}
}
