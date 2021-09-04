package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_File_Advanced {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait;
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

	//@Test
	public void TC_02_UploadWithAutoITChrome() throws IOException {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		String chromeAutoITPath = projectPath+"\\authoAutoIT\\Chorme_Upload.exe";
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {chromeAutoITPath,filePath1});
		sleepInSecond(2);
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {chromeAutoITPath,filePath2});
		sleepInSecond(2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
	}

	//@Test
	public void TC_02_UploadWithAutoITFirefox() throws IOException {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		String firefoxAutoITPath = projectPath+"\\authoAutoIT\\Firefox_Upload.exe";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {firefoxAutoITPath,filePath1});
		sleepInSecond(2);
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {firefoxAutoITPath,filePath2});
		sleepInSecond(2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
	}

	//@Test
	public void TC_02_UploadMultipleWithAutoITEdge() throws IOException {
		System.setProperty("webdriver.edge.driver", projectPath+"\\driverBrowsers\\msedgedriver.exe");
		driver = new EdgeDriver();
		String chromeAutoITPath = projectPath+"\\authoAutoIT\\chromeUploadMultiple.exe";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {chromeAutoITPath,filePath1,filePath2});
		sleepInSecond(2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
	}
	
	//@Test
	public void TC_02_UploadMultipleWithAutoITFirefox() throws IOException {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		String firefoxAutoITPath = projectPath+"\\authoAutoIT\\firefoxUploadMultiple.exe";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {firefoxAutoITPath,filePath1,filePath2});
		sleepInSecond(2);
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture2+"']")).isDisplayed());
	}
	
	//@Test
	public void TC_03_UploadWithRobotChrome() throws IOException, AWTException {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		
		//Specify image location
		StringSelection select = new StringSelection(filePath1);
		
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
		assertTrue(driver.findElement(By.xpath("//table//p[@class='name' and text()='"+picture1+"']")).isDisplayed());	
		}

	@Test
	public void TC_04_UploadFileChrome() throws AWTException {
		System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://gofile.io/uploadFiles");
		driver.findElement(By.cssSelector("#rowUploadButton button.uploadButton")).click();
		uploadFileUsingKeyboard(filePath1);
		WebElement successLabel= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		assertEquals(successLabel.getText(),"Your files have been successfully uploaded");
		driver.findElement(By.id("rowUploadSuccess-showFiles")).click();
		assertTrue(driver.findElement(By.xpath("//div[@id='rowFolder-table']//a/span[text()='"+picture1+"']")).isDisplayed());
		
		//driver.navigate().refresh();
		driver.findElement(By.cssSelector("#rowFolder button.uploadButton")).click();
		uploadFileUsingKeyboard(filePath2);
		assertTrue(driver.findElement(By.xpath("//div[@id='rowFolder-table']//a/span[text()='"+picture2+"']")).isDisplayed());
	}

	@Test
	public void TC_04_UploadFileFireFox() throws AWTException {
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://gofile.io/uploadFiles");
		driver.findElement(By.cssSelector("#rowUploadButton button.uploadButton")).click();
		uploadFileUsingKeyboard(filePath1);
		WebElement successLabel= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		assertEquals(successLabel.getText(),"Your files have been successfully uploaded");
		driver.findElement(By.id("rowUploadSuccess-showFiles")).click();
		assertTrue(driver.findElement(By.xpath("//div[@id='rowFolder-table']//a/span[text()='"+picture1+"']")).isDisplayed());
		
		//driver.navigate().refresh();
		driver.findElement(By.cssSelector("#rowFolder button.uploadButton")).click();
		uploadFileUsingKeyboard(filePath2);
		assertTrue(driver.findElement(By.xpath("//div[@id='rowFolder-table']//a/span[text()='"+picture2+"']")).isDisplayed());	}


	@AfterMethod
	public void afteMethod() {
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
