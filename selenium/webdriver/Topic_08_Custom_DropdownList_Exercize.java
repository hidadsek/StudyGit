package webdriver;


import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_DropdownList_Exercize {
	WebDriver driver;
	
	// Wait
	WebDriverWait explicitWait;
	
	// Inject javascript code
	JavascriptExecutor jsExecuter;
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
		explicitWait = new WebDriverWait (driver,15);
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_JQuery() {
		// Open website
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// Initial values
		WebElement numberDropdownList = driver.findElement(
				By.xpath("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]"));
		By numberListLoc = By.xpath("//ul[@id='number-menu']//div");
		String number = "19";
		// Click value
		selectItemInDropdownlist(numberDropdownList, numberListLoc,number);
		// Assert value
		assertEquals(driver.findElement(By.id("number")).getAttribute("value"),number);
	}

	@Test
	public void TC_02_ReactJS() {
		// Open website
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		// Initial values
		WebElement listDropdownList = driver.findElement(By.cssSelector("[role='listbox']"));
		By listLoc = By.xpath("//div[contains(@class,'visible menu transition')]//span");
		String name = "Justen Kitsune";
		// Click value
		selectItemInDropdownlist(listDropdownList, listLoc, name);
		// Assert value
		assertEquals(listDropdownList.getAttribute("innerText"),name);
	}

	@Test
	public void TC_03_VueJS() {
		// Open website
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		// Initial values
		WebElement itemDropdownList = driver.findElement(By.className("btn-group"));
		By listLoc = By.cssSelector("#app .btn-group a");
		String item = "Third Option";
		// Click value
		selectItemInDropdownlist(itemDropdownList, listLoc, item);
		// Assert value
		assertEquals(itemDropdownList.getAttribute("innerText"),item);
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
	public void selectItemInDropdownlist(WebElement element, By locator, String itemValue) {
		//Click element
		element.click();
		
		//Chờ tất cả item load xong và ra 1 list item
		List<WebElement> allItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
		//Tìm item cần chọn. Duyệt qua từng item
		for (WebElement item: allItems) {
			if (item.getText().equals(itemValue)) {
				//Click value nếu nó hiển thị
				if(!item.isDisplayed()) {
				//Nếu không, scroll tới giá trị
				jsExecuter.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				}
				item.click();
				break;
			}
		}
	}
}