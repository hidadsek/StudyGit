package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_AcceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.cssSelector("[onclick='jsAlert()']")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(),"I am a JS Alert");
		alert.accept();
		assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_ConfirmAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.cssSelector("[onclick='jsConfirm()']")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(),"I am a JS Confirm");
		alert.dismiss();
		assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_PromptAlert() {
		String name = "ThucNguyen";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.cssSelector("[onclick='jsPrompt()']")).click();
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(),"I am a JS Prompt");
		alert.sendKeys(name);
		assertEquals(driver.findElement(By.id("result")).getText(), "You entered: "+name);
	}
	
	@Test
	public void TC_04_AuthenticationAlert() {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.cssSelector("[onclick='jsPrompt()']")).click();
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
