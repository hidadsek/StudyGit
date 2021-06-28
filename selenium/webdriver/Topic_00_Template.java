package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	
	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;
	
	// precondition
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox
		driver = new FirefoxDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Mở application lên (AUT / SUT)
		driver.get("http://demo.guru99.com/v4/");
		 
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
