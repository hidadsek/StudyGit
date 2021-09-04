package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String picture1 = "test_02.png";
	String filePath1 = projectPath+File.separator+"uploadFile"+File.separator+picture1;
	String picture2 = "Test_01.jpg";
	String filePath2 = projectPath+File.separator+"uploadFile"+File.separator+picture2;

	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		/*
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		*/
		// Chrome Driver
	}

	@Test
	public void TC_01_UploadWithSendKeyWithChrome() {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(filePath1);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(filePath2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement startButton:startButtons) {
			startButton.click();
			sleepInSecond(1);	
		}
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
	}

	@Test
	public void TC_01_UploadWithSendKeyWithFireFox() {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(filePath1);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(filePath2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement startButton:startButtons) {
			startButton.click();
			sleepInSecond(1);	
		}
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
	}

	@Test
	public void TC_01_UploadMultipleWithSendKeyWithChrome() {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(filePath1+"\n"+filePath2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement startButton:startButtons) {
			startButton.click();
			sleepInSecond(1);	
		}
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
	}
	
	@Test
	public void TC_01_UploadMultipleWithSendKeyWithEdge() {
		System.setProperty("webdriver.edge.driver", projectPath+"\\driverBrowsers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(filePath1+"\n"+filePath2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement startButton:startButtons) {
			startButton.click();
			sleepInSecond(1);	
		}
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name']/a[text()='"+picture1+"']")).isDisplayed());
	}

	@AfterMethod
	public void afterMethod() {
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
