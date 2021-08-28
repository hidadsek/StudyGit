package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_UserInteraction {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
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
		action = new Actions(driver) ;
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	
	public void TC_01_HoverToShowTooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();
		assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	//@Test
	public void TC_02_HoverToShowMenu() {
		driver.get("https://www.myntra.com/");
		selectItemInMenu("Kids", "Home & Bath");
		assertTrue(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']//span[text() ='Kids Home Bath']")).isDisplayed());
		assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(),"Kids Home Bath");
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		driver.get("https://hn.telio.vn/");
	}
	
	//@Test
	public void TC_04_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		action.clickAndHold(driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='1']")))
		.moveToElement(driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='4']"))).release().perform();
		assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(),4);		
	}
	
	//@Test
	public void TC_05_ClickAndSelect() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberList = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).click(numberList.get(0)).click(numberList.get(2))
		.click(numberList.get(5)).click(numberList.get(10)).keyUp(Keys.CONTROL).perform();
		assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(),4);		
	}
	
	//@Test
	public void TC_06_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleClickButton = driver.findElement(By.cssSelector("button[ondblclick='doubleClickMe()']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView()", doubleClickButton);
		action.doubleClick(doubleClickButton).perform();
		assertEquals(driver.findElement(By.cssSelector("button[ondblclick='doubleClickMe()'] + p")).getText(), "Hello Automation Guys!");
	}
	
	
	//@Test
	public void TC_07_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickButton = driver.findElement(By.cssSelector("span.btn-neutral"));
		WebElement optionSelection = driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')]//span[text()='Quit']"));
		action.contextClick(rightClickButton).moveToElement(optionSelection).perform();
		assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')]//span[text()='Quit']")).isDisplayed());
		action.click(optionSelection).perform();
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		sleepInSecond(1);
		assertFalse(optionSelection.isDisplayed());
	}
	
	//@Test
	public void TC_08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallObject = driver.findElement(By.id("draggable"));
		WebElement bigObject = driver.findElement(By.id("droptarget"));
		assertEquals(bigObject.getText(), "Drag the small circle here.");
		action.dragAndDrop(smallObject, bigObject).perform();
		assertEquals(bigObject.getText(), "You did great!");
	}
	
	@Test
	public void TC_07_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String javascriptPath = projectPath+"\\dragAndDrop\\drag_and_drop_helper.js";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";
		jsExecutor.executeScript(java_script);
		Thread.sleep(3000);
		assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		sleepInSecond(3);
		// B to A
		jsExecutor.executeScript(java_script);
		Thread.sleep(3000);
		assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
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
	
	public void selectItemInMenu(String menuItem, String item) {
		String menuXpath = "//nav[@class='desktop-navbar']//div[@class='desktop-navLink']/a[text()='"+menuItem+"']";
		String itemXpath = "//ul[@class='desktop-navBlock']//a[text()='"+item+"']";
		action.moveToElement(driver.findElement(By.xpath(menuXpath))).perform();
		driver.findElement(By.xpath(itemXpath)).click();
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

}
