package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

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
	}

	//@Test
	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		//.face-content>iframe (css)
		driver.switchTo().frame(driver.findElement(By.cssSelector(".face-content>iframe")));
		assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/../following-sibling::div")).getText(),"168K lượt thích");
		driver.switchTo().defaultContent();
		sleepInSecond(1);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("input[ng-model='login.username']")).sendKeys("John Wick");
		driver.findElement(By.cssSelector("input[ng-model='login.phone']")).sendKeys("0909444555");
		Select serviceOption = new Select(driver.findElement(By.id("serviceSelect")));
		serviceOption.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		//textarea[ng-model='login.content']
		driver.findElement(By.cssSelector("textarea[ng-model='login.content']")).sendKeys("Java Bootcamp");
		//input[ng-click='sendOffline()']
		driver.findElement(By.cssSelector("input[ng-click='sendOffline()']")).click();
		assertEquals(driver.findElement(By.cssSelector("div.profile_field label.logged_in_name ")).getText(), "John Wick");
		assertEquals(driver.findElement(By.cssSelector("div.profile_field label.logged_in_phone")).getText(), "0909444555");
		assertEquals(driver.findElement(By.cssSelector("textarea[name='message']")).getText(), "Java Bootcamp");
		driver.switchTo().defaultContent();
		sleepInSecond(1);
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector(".search-button")).click();
		assertTrue(driver.findElement(By.cssSelector("span.menu-heading")).getText().contains("Excel"));
		List<WebElement> listSearch = driver.findElements(By.cssSelector("ul.k-box-card-list div.content h4"));
		for (WebElement item : listSearch) {
			assertTrue(item.getText().toLowerCase().contains("excel"));
		}
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.name("login_page")));
		driver.findElement(By.name("fldLoginUserId")).sendKeys("D845135");
		driver.findElement(By.cssSelector(".login-btn")).click();
		assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		driver.findElement(By.xpath("//div[@class ='footer-btm']/a[text()='Terms and Conditions']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		assertEquals(allWindows.size(),2);
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
