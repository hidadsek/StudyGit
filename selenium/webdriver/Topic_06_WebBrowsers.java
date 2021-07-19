package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowsers {
	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// precondition
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Mở application lên (AUT / SUT)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");	
		 
	}
	
	public void TC_01(){
		// Đóng 1 tab
		driver.close();
		
		// Đóng trình duyệt
		driver.quit();
		
		// Lấy ID tab/window hiện tại
		String windowHandle = driver.getWindowHandle();
		
		// Lấy ID tất cả các tab/window
		Set<String> windowHadles = driver.getWindowHandles();
		
		//Chuyển qua tab/window khác
		driver.switchTo().window(windowHandle);
		
		// Lấy URL hiện tại
		driver.getCurrentUrl();
		
		// Lấy ra tất cả các element trong xpath
		List<WebElement> textBoxes = driver.findElements(By.xpath(""));
		
		// Trả về HTML source của page hiện tại
		driver.getPageSource();
		
		// Trả về Title
		driver.getTitle();
		
		// Get và xóa Cookie của page
		driver.manage().deleteAllCookies();
		
		//Build Framework: Get ra log của browser		
		driver.manage().logs().getAvailableLogTypes();
		
		// Wait cho việc tìm element (findElement, findElements)
		// WebDriverWait
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Wait cho 1 page để load
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Wait cho 1 script chạy thành công
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Mở browser full màn hình
		driver.manage().window().maximize();
		
		// Mở browser và maximize màn hình
		driver.manage().window().fullscreen();

		// Lấy ra vị trí hiện tại của browser
		driver.manage().window().getPosition();
		
		// Set Browser tại 1 địa điểm nào đó
		driver.manage().window().setPosition(new Point(0,0));
		
		// Lấy Size của window
		driver.manage().window().getSize();
		driver.manage().window().getSize();
		
		// Navigate
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");
		
		// Windows/tab
		// Alert
		// Frame/ Iframe
		driver.switchTo().alert();
		driver.switchTo().frame(1);
		driver.switchTo().window("");//
	}
	
	// postcondition - clean up data
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
