package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_PopUp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait wait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Chrome Driver
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,30);
		action = new Actions(driver);
	}

	//@Test
	public void TC_01_FixedPopUp() {
		driver.get("https://ngoaingu24h.vn/");
		WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fb-panel")));
		sleepInSecond(3);
		assertTrue(popup.isDisplayed());
		driver.findElement(By.className("button-close")).click();
		assertFalse(popup.isDisplayed());
		
		driver.findElement(By.className("login_")).click();
		//#modal-login-v1 .modal-body
		assertTrue(driver.findElement(By.cssSelector("#modal-login-v1 .modal-body")).isDisplayed());
		driver.findElement(By.id("account-input")).sendKeys("automationfc");
		driver.findElement(By.id("password-input")).sendKeys("automationfc");
		driver.findElement(By.className("btn-login-v1")).click();
		assertTrue(driver.findElement(By.xpath
				("//div[@id='modal-login-v1']//div[contains(@class,'error-login-panel') and contains(text(),'Tài khoản không tồn tại!')]")).isDisplayed());
		driver.findElement(By.cssSelector("#modal-login-v1 button.close")).click();
		assertFalse(driver.findElement(By.cssSelector("#modal-login-v1 .modal-body")).isDisplayed());
	}

	//@Test
	public void TC_02_RandomPopUpInDOM() {
		driver.get("https://blog.testproject.io/");
		WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.mailch-wrap")));
		if(popup.isDisplayed()) {
			System.out.println("Pop-up displays");
			driver.findElement(By.cssSelector("div.mailch-wrap>#close-mailch")).click();
			sleepInSecond(2);
		}
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
		//driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		action.sendKeys(Keys.ENTER).perform();
		sleepInSecond(2);
		
		List<WebElement> articleList = driver.findElements(By.cssSelector(".post-title>a"));
		for(WebElement article:articleList) {
			assertTrue(article.getText().contains("Selenium"));
		}
	}

	@Test
	public void TC_03_RandomPopUpNotInDOM() {
		driver.get("https://shopee.vn/");
		List<WebElement> popup = driver.findElements(By.cssSelector(".shopee-popup__container"));
		if (popup.size()>0){
			System.out.println("Pop-up displays");
			if(popup.get(0).isDisplayed()){
				driver.findElement(By.cssSelector(".shopee-popup__container div.shopee-popup__close-btn")).click();
				sleepInSecond(2);
			}
		}
		else System.out.println("Pop-up does NOT display");
		WebElement searchTextbox= driver.findElement(By.cssSelector(".shopee-searchbar-input__input"));
		searchTextbox.sendKeys("TEST");
		assertEquals(searchTextbox.getAttribute("value"), "TEST");
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
}
