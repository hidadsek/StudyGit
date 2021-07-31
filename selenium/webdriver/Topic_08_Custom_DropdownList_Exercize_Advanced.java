package webdriver;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_DropdownList_Exercize_Advanced {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
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
		jsExecutor = (JavascriptExecutor)driver;
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_Angular() {
		driver.get("https://valor-software.com/ng2-select/");
		WebElement cityDropdownlist = driver.findElement(By.className("ui-select-container"));
		By cityLoc = By.cssSelector("ul[role ='menu'] a>div");
		String city = "Manchester";
		selectItemInElement(cityDropdownlist, cityLoc, city);
		assertEquals(cityDropdownlist.getAttribute("innerText"), city);	
	}

	
	public void TC_02_EditableDropdownList() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		WebElement slideDropdownList = driver.findElement(By.cssSelector("#slide-place>input"));
		String slide = "Audi";
		enterAndTabInElement(slideDropdownList, slide);
		assertEquals(slideDropdownList.getAttribute("value"), slide);
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		WebElement countryDropdownList = driver.findElement(By.cssSelector("[class='search']"));
		String country = "Andorra";
		enterAndTabInElement(countryDropdownList, country);
		assertEquals(driver.findElement(By.cssSelector("[class='search']+div")).getAttribute("textContent"), country);
	}

	@Test
	public void TC_03_MultipleDropdownList() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		WebElement multipleSelect = driver.findElement(By.xpath("(//button[contains(@class,'ms-choice')])[1]"));
		By multipleLoc = By.xpath("(//div[contains(@class,'ms-drop')])[1]//li");
		By selectedLoc = By.xpath("(//div[contains(@class,'ms-drop')])[1]//li[@class = 'selected']");
		//{"January","February","May", "July"}
		String[] selectList = {"[Select all]"};
		selectMultipleInDropdownList(multipleSelect, multipleLoc, selectedLoc, selectList);		
		assertTrue(areElementSelected(multipleSelect, multipleLoc, selectedLoc, selectList));		
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
	
	public void selectItemInElement(WebElement element, By locator, String itemValue) {
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
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInSecond(1);
				}
				item.click();
				break;
			}
		}
	}
	public void enterAndTabInElement(WebElement element, String itemValue){
		element.sendKeys(itemValue);
		element.sendKeys(Keys.ENTER);
		sleepInSecond(1);
	}
	
	public void selectMultipleInDropdownList(WebElement element, By locator, By selectedLocator, String[] listItem) {
		element.click();
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		for (WebElement itemElement:allItems) {
			for (String item:listItem) {
				if(itemElement.getText().equals(item)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", itemElement);
					sleepInSecond(1);
					itemElement.click();
					break;
				}
			}
			List<WebElement> selectedElement = driver.findElements(selectedLocator);
			if (selectedElement.size()==listItem.length)
				break;
		}
	}
	public boolean areElementSelected(WebElement element, By locator, By selectedLocator, String[] listItem) {
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		List<WebElement> selectedElement = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectedLocator));
		String resultText = element.getAttribute("innerText");
		System.out.println(resultText);
		
		if (selectedElement.size()+2==allItems.size()) {
			if (resultText.equals("All selected")) {
				return true;
			}
		}
		else if (selectedElement.size()==listItem.length) {
			if(listItem.length<4) {
			  for(String item:listItem) {
				  if (!resultText.contains(item)) {
					  return false;
				  }
			  }
			  return true;
			}
			else {
				String expectedText = selectedElement.size()+" of 12 selected";
				if(resultText.equals(expectedText)) {
					return true;
				}
			}
		}
		return false;
	}
}