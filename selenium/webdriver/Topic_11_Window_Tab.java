package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

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
		jsExecutor= (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_OpenAndCloseTabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//get ID của active tab/Window hiện tại
		String homeID = driver.getWindowHandle();
		driver.findElement(By.linkText("GOOGLE")).click();
		//Switch vào tab có ID khác tab hiện tại nào đó
		switchToWindowNotHomeByID(homeID);
		assertEquals(driver.getTitle(), "Google");
		driver.findElement(By.cssSelector("form input[type='text']")).sendKeys("Testing");
		assertEquals(driver.findElement(By.cssSelector("form input[type='text']")).getAttribute("value"), "Testing");
		//Switch vào tab cũ
		driver.switchTo().window(homeID);
		//click Facebook link
		driver.findElement(By.linkText("FACEBOOK")).click();
		//Switch vào tab Facebook
		switchToWindowByTitle("Facebook");
		assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		driver.findElement(By.name("login")).click();
		assertEquals(driver.findElement(By.xpath("//form//input[@id='email']/following-sibling::div/a/..")).getAttribute("textContent"),
		"Email hoặc số di động bạn nhập không kết nối với tài khoản nào. Hãy tìm tài khoản của bạn và đăng nhập.");
		//Switch vào tab Google
		switchToWindowByTitle("Google");
		assertEquals(driver.getTitle(), "Google");
		//Switch vào tab cũ
		driver.switchTo().window(homeID);
		//click Facebook link
		driver.findElement(By.linkText("TIKI")).click();
		//Switch vào tab Tiki
		switchToWindowByTitle("Tiki");
		assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		//Close all but home window
		closeAllButHomeTab(homeID);
		driver.switchTo().window(homeID);
		assertEquals(driver.getWindowHandles().size(),1);
		assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}

	//@Test
	public void TC_02_SwitchToFrameAndTab() {
		driver.get("https://kyna.vn/");
		String homePageTitle = driver.getTitle();
		String homeID = driver.getWindowHandle();
		List<WebElement> popup = driver.findElements(By.cssSelector(".fancybox-skin>a"));
		if(popup.size()>0) {
			popup.get(0).click();
			sleepInSecond(1);
		}
		WebElement facebookIframe = driver.findElement(By.cssSelector("div.face-content"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(false)", facebookIframe);
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		driver.findElement(By.cssSelector("a[title='Kyna.vn']")).click();
		switchToWindowByTitle("Kyna.vn | Facebook");
		assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/kyna.vn/"));
		closeAllButHomeTab(homeID);
		switchToWindowByTitle(homePageTitle);
		assertEquals(driver.getWindowHandles().size(),1);
		assertTrue(driver.getCurrentUrl().contains("https://kyna.vn/"));
		assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
	}

	@Test
	public void TC_03_OpenNewWindow() {
		driver.get("http://live.demoguru99.com/index.php/");
		String basePage = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		selectAction("Sony Xperia", "Add to Compare");
		assertTrue(driver.findElement(By.
				xpath("//li[@class='success-msg']//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		selectAction("Samsung Galaxy", "Add to Compare");
		assertTrue(driver.findElement(By.
				xpath("//li[@class='success-msg']//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.cssSelector("ol#compare-items~div.actions>button")).click();
		sleepInSecond(1);
		switchToWindowNotHomeByID(basePage);
		assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		sleepInSecond(1);
		driver.switchTo().window(basePage);
		driver.findElement(By.cssSelector("ol#compare-items~div.actions>a")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		assertTrue(driver.findElement(By.
				xpath("//li[@class='success-msg']//span[text()='The comparison list was cleared.']")).isDisplayed());
		
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
	
	public void selectAction(String item, String action) {
		driver.findElement(By.xpath(""
				+ "//h2//a[@title='"+item+"']/../following-sibling::div[@class='actions']//a[text()='"+action+"']")).click();
		sleepInSecond(1);
	}
	
	public void switchToWindowNotHomeByID(String homeID) {
		//get ID của tất cả tab/Window đang có
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(homeID)) {
				driver.switchTo().window(id);
			}
		}
	}
		
	public void switchToWindowByTitle(String windowTitle) {
		//get ID của tất cả tab/Window đang có
		Set<String> allIDs = driver.getWindowHandles();
		//Duyệt từng window/tab bằng vòng lặp
		for (String id: allIDs) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(windowTitle)) {
				break;
			}
		}
	}
	
	public void closeAllButHomeTab(String homeID) {
		//get ID của tất cả tab/Window đang có
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(homeID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
	}
}
