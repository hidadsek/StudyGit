package testNG;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_MultiBrowsers_Parameter {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	// Paremeter được tạo bên xml file và có thể tạo các biến dữ liệu để config cho các lần chạy khác nhau
	// run Suite: TestcasesMultiBrowsers
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run with - "+ browserName);
		if(browserName.equals("firefox")) {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		else if (browserName.equals("chrome")) {
		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		else if (browserName.equals("edge")) {
		// Chrome Driver
		System.setProperty("webdriver.edge.driver", projectPath+"\\driverBrowsers\\msedgedriver.exe");
		driver = new EdgeDriver();
		}
		else {
			throw new RuntimeException("Please input the correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("send2")).click();
		sleepInSecond(1);
		assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
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
