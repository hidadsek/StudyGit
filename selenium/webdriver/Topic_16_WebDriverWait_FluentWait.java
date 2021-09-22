package webdriver;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_16_WebDriverWait_FluentWait {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Chrome Driver
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		//driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
	}

	@Test
	public void TC_08_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("javascript_countdown_time")));
		
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(15))
		//Tần số 1s check 1 lần
		.pollingEvery(Duration.ofMillis(100))
		//Nếu gặp exception là find không thấy thì bỏ qua
		.ignoring(NoSuchElementException.class);
		
		fluentWait.until(new Function<WebDriver,Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Time is: "+countdown.getText());
				return countdown.getText().endsWith("00");
			}
		});	
	}

	@Test
	public void TC_09_FluentWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		waitForElementAndClick(By.cssSelector("#start>button"));
		System.out.println("Start: "+getCurrentDate());
		waitForElementToDisplay(By.xpath("//h4[text()='Hello World!']"));
		System.out.println("End: "+getCurrentDate());
	}

	@Test
	public void TC_10_PageAndAjaxLoading() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		isJQueryLoadedSuccess(driver);
		assertEquals(driver.findElement(By.xpath("//table//tr[@class='total']//span")).getText(), "3 month(s)");
		driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).click();
		isJQueryLoadedSuccess(driver);
		driver.findElement(By.cssSelector("input#empsearch_employee_name_empName")).sendKeys("Cecil");
		driver.findElement(By.id("searchBtn")).click();
		isJQueryLoadedSuccess(driver);
		assertEquals(driver.findElement(By.xpath("//tbody//td[3]/a")).getText(), "Cecil");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String getCurrentDate() {
		Date date = new Date();
		return date.toString();
	}
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(15))
		//Tần số 1s check 1 lần
		.pollingEvery(Duration.ofSeconds(1))
		//Nếu gặp exception là find không thấy thì bỏ qua
		.ignoring(NoSuchElementException.class);
		
		WebElement element = fluentWait.until(new Function<WebDriver,WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}
	
	public WebElement getWebElement(By locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(15))
		//Tần số 1s check 1 lần
		.pollingEvery(Duration.ofSeconds(1))
		//Nếu gặp exception là find không thấy thì bỏ qua
		.ignoring(NoSuchElementException.class);
		
		WebElement element = fluentWait.until(new Function<WebDriver,WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public Boolean waitForElementToDisplay(By locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(15))
		//Tần số 1s check 1 lần
		.pollingEvery(Duration.ofSeconds(1))
		//Nếu gặp exception là find không thấy thì bỏ qua
		.ignoring(NoSuchElementException.class);
		
		WebElement element = fluentWait.until(new Function<WebDriver,WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element.isDisplayed();
	}

	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active===0)");
			}
		};
		return wait.until(jQueryLoad);
	}
	
	public boolean isJQueryAndAjaxLoadedSuccess(WebDriver driver) {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active")==0);
				}catch (Exception e){
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return $('.raDiv').is('.visible')").toString().equals("false");
			}
		};
		return wait.until(jQueryLoad)&& wait.until(ajaxIconLoading);
	}
	
	public boolean isJQueryAndPageLoadedSuccess(WebDriver driver) {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active")==0);
				}catch (Exception e){
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return wait.until(jQueryLoad)&& wait.until(jsLoad);
	}
}
