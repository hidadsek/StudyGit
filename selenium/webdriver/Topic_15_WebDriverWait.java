package webdriver;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_WebDriverWait {
	WebDriver driver;
	WebDriverWait wait;
	String projectPath = System.getProperty("user.dir");
	
	By creatNewAccountButtonBy= By.xpath("//a[text()='Tạo tài khoản mới']");
	By emailTextBoxBy = By.cssSelector("input[name='reg_email__']");
	By confirmEmailTextBoxBy = By.name("reg_email_confirmation__");

	@BeforeClass
	public void beforeClass() {
		// Firefox Driver
		System.setProperty("webdriver.gecko.driver", projectPath+"\\driverBrowsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Chrome Driver
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\driverBrowsers\\chromedriver.exe");
		//driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Check_Visible_Invisible() {
		driver.get("https://www.facebook.com/");
		driver.findElement(creatNewAccountButtonBy).click();
		
		//Trường hợp 1: Visible: Hiện trên UI và DOM
		WebElement emailTextbox = driver.findElement(emailTextBoxBy);	
		emailTextbox.sendKeys("automationFC@gmail.com");
		WebElement confirmEmailTextbox  = driver.findElement(confirmEmailTextBoxBy);
		wait.until(ExpectedConditions.visibilityOf(confirmEmailTextbox));
		assertTrue(confirmEmailTextbox.isDisplayed());
		
		//Trường hợp 2: Invisible: Biến mất trên UI. 
		// Còn trong DOM
		emailTextbox.clear();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.name("reg_email_confirmation__"))));		
		assertFalse(confirmEmailTextbox.isDisplayed());
		// Không có trong DOM. Đợi cả wait và implicit timeout
		// Không nên dùng để tính toán khi chuyển trạng thái từ có trong DOM sang mất khỏi DOM vì có thể tính toán lỗi do UI mất nhưng DOM còn 
		// Không nên dùng khi reload lại page vì element đã mất trang thái
		// Nên dùng staleness cho case refresh và mất khỏi DOM
		driver.findElement(By.xpath("//div[text()='Đăng ký']/../preceding-sibling::img")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.name("reg_email_confirmation__"))));
		System.out.println("Element có trong DOM = "+ driver.findElements(By.name("reg_email_confirmation__")).size());
		
		driver.navigate().refresh();
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.name("reg_email_confirmation__"))));
		System.out.println("Element có trong DOM = "+ driver.findElements(By.name("reg_email_confirmation__")).size());
	}

	//@Test
	public void TC_01_Check_Staleness() {
		driver.get("https://www.facebook.com/");
		driver.findElement(creatNewAccountButtonBy).click();	
		
		//Trường hợp 3: Staleness: Biến mất trên DOM.
		//Sẽ đợi khi Element biến mất khỏi DOM 
		//Nhưng nhanh hơn Invisible vì chỉ cần đợi đúng wait timeout, không đợi implicit timeout
		WebElement emailTextbox = driver.findElement(emailTextBoxBy);	
		emailTextbox.sendKeys("automationFC@gmail.com");	
		WebElement confirmEmailTextbox  = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextBoxBy));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/../preceding-sibling::img")).click();
		wait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
		System.out.println("Element có trong DOM = "+ driver.findElements(By.name("reg_email_confirmation__")).size());
	}
	
	//@Test
	public void TC_01_Check_Presence() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();		
		//Trường hợp 4: Presence: Chỉ cần có trong DOM, không quan tâm có hiện trên UI hay không
		// Không có trên UI	
		driver.findElement(creatNewAccountButtonBy).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextBoxBy));
		assertFalse(driver.findElement(By.name("reg_email_confirmation__")).isDisplayed());
		
		// Có trên UI
		driver.findElement(emailTextBoxBy).sendKeys("automationFC@gmail.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
		assertTrue(driver.findElement(By.name("reg_email_confirmation__")).isDisplayed());
	}
	@Test
	public void TC_01_FindElement() {
		//Case 1: Tìm thấy 1 Element
		driver.get("https://www.facebook.com");
		System.out.println("Start: "+getDateTimeNow());
		driver.findElement(By.id("email")).sendKeys("automationFC@gmail.com");
		System.out.println("End: "+getDateTimeNow());
		
		//Case 1.1: Đang tìm thì thấy Element
		driver.findElement(creatNewAccountButtonBy).click();
		driver.findElement(emailTextBoxBy).sendKeys("automationFC@gmail.com");
		System.out.println("Start: "+getDateTimeNow());
		driver.findElement(confirmEmailTextBoxBy).sendKeys("automationFC@gmail.com");
		System.out.println("End: "+getDateTimeNow());
		
		//Case 2: Không tìm thấy Element
		driver.get("https://www.facebook.com");
		System.out.println("Start: "+getDateTimeNow());
		try {
		//driver.findElement(By.id("xxx")).isDisplayed();
		} 
		finally{
		System.out.println("End: "+getDateTimeNow());}
		
		//Case 3: Tìm thấy nhiều Element - chọn element đầu tiên
		driver.get("https://www.facebook.com");
		System.out.println("Start: "+getDateTimeNow());
		System.out.println(driver.findElement(By.cssSelector("a[title]")).getText());
		System.out.println("End: "+getDateTimeNow());
	}
	
	//@Test
	public void TC_01_FindElements() {
		//Case 1: Tìm thấy 1 Element - kết quả = 1
		driver.get("https://www.facebook.com");
		System.out.println("Start: "+getDateTimeNow());
		System.out.println(driver.findElements(By.id("email")).size());
		System.out.println("End: "+getDateTimeNow());
		
		//Case 2: Không tìm thấy Element- kết quả = 0
		driver.get("https://www.facebook.com/home.php");
		System.out.println("Start: "+getDateTimeNow());
		System.out.println(driver.findElements(By.cssSelector("xxx")).size());
		System.out.println("End: "+getDateTimeNow());
		
		//Case 3: Tìm thấy nhiều Element - kết quả = tổng số element
		driver.get("https://www.facebook.com/home.php");
		System.out.println("Start: "+getDateTimeNow());
		System.out.println(driver.findElements(By.cssSelector("a[title]")).size());
		System.out.println("End: "+getDateTimeNow());
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
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
