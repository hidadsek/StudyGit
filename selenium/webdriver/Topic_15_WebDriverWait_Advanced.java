package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_WebDriverWait_Advanced {
	WebDriver driver;
	WebDriverWait wait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		/*
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		*/
		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_02_ImplicitWait_less_than() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}

	//@Test
	public void TC_02_ImplicitWait_enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}
	
	//@Test
	public void TC_02_ImplicitWait_more_than() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}
	
	//@Test
	public void TC_03_Static_Less_Than(){
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		sleepInSecond(2);
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}
	
	//@Test
	public void TC_03_Static_Enough(){
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		sleepInSecond(5);
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}

	//@Test
	public void TC_03_Static_More_Than(){
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		sleepInSecond(10);
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}
	
	//@Test
	public void TC_04_ExplicitWait_Invisible_3s() {
		wait = new WebDriverWait(driver,3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		try {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		} finally {
			System.out.println("End: "+getDateTimeNow());
		}	
	}

	//@Test
	public void TC_04_ExplicitWait_Invisible_5s() {
		wait = new WebDriverWait(driver,5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
	}

	//@Test
	public void TC_05_ExplicitWait_Visible_3s() {
		wait = new WebDriverWait(driver,3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish>h4")));
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		} finally {
			System.out.println("End: "+getDateTimeNow());
		}	
	}

	//@Test
	public void TC_05_ExplicitWait_Visible_5s() {
		wait = new WebDriverWait(driver,5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#start>button")).click();
		System.out.println("Start: "+getDateTimeNow());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish>h4")));
		assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(),"Hello World!");
		System.out.println("End: "+getDateTimeNow());
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
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
