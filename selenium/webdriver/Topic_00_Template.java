package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	
	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// precondition
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox
		//System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		// Mở chrome
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Mở application lên (AUT / SUT)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		driver.manage().window().maximize();
		 
	}
	
	// step
	@Test
	public void TC_01(){
		// Single Element
		driver.findElement(By.className(null)).click();
		driver.findElement(By.className(null)).getText();
		
		//find element: tìm element
		//By.xxx: vs locator nào
		//Action:.click(), .getText(),...
		
		// Multiple Elements
		List<WebElement> buttons = driver.findElements(By.className(null));
		buttons.get(0);
		
	}
	
	// postcondition - clean up data
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
