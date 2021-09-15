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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_WebDriverWait_ExplicitFluentWait {
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
		/* Add Extension/plugin
		File file = new File(projectPath+"")
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(file);
		driver = new ChromeDriver(options);
		*/
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_06_ExplicitWait() {
		By dateLabelBy = By.cssSelector("fieldset span.label");
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		WebElement dateTimePicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		WebElement dateLabel = driver.findElement(dateLabelBy);
		assertEquals(dateLabel.getText(),"No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='10']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rcSelected ")));
		assertEquals(driver.findElement(dateLabelBy).getText(),driver.findElement(By.cssSelector(".rcSelected")).getAttribute("title"));
	}

	@Test
	public void TC_07_ExplicitWait() throws AWTException {
		String picture1 = "test_02.png";
		String filePath1 = projectPath+File.separator+"uploadFile"+File.separator+picture1;
		driver.get("https://gofile.io/uploadFiles");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#rowUploadButton button.uploadButton"))).click();
		uploadFileUsingKeyboard(filePath1);
		WebElement successLabel= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		assertEquals(successLabel.getText(),"Your files have been successfully uploaded");
		driver.findElement(By.id("rowUploadSuccess-showFiles")).click();
		assertTrue(driver.findElement(By.xpath("//div[@id='rowFolder-table']//a/span[text()='"+picture1+"']")).isDisplayed());
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		
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
	
	public void uploadFileUsingKeyboard(String filePath) throws AWTException {
		//Specify image location
		StringSelection select = new StringSelection(filePath);
		
		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select,null);
		
		Robot robot = new Robot();
		sleepInSecond(1);
		
		// Crt V and click Enter
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(2);
	}
}
