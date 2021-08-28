package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Javascript_Injector {
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_JavascriptExecutor() {
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
		sleepInSecond(1);
		assertEquals(jsExecutor.executeScript("return document.domain"), "live.demoguru99.com");
		assertEquals(jsExecutor.executeScript("return document.URL"), "http://live.demoguru99.com/");
		jsExecutor.executeScript("arguments[0].click()",driver.findElement(By.xpath("//nav//a[text()='Mobile']")));
		sleepInSecond(1);
		addItemToCart("Samsung Galaxy");
		assertEquals(jsExecutor.executeScript("return arguments[0].innerText",driver.findElement(By.cssSelector("li.success-msg span"))),
				"Samsung Galaxy was added to your shopping cart.");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@class='footer']//a[text()='Customer Service']")));
		sleepInSecond(1);
		assertEquals(jsExecutor.executeScript("return document.title"), "Customer Service");
		String email = "nguyendongthuc13@gmail.com";
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector("#newsletter")));
		jsExecutor.executeScript("arguments[0].value = '"+email+"'", driver.findElement(By.cssSelector("#newsletter")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button[title='Subscribe']")));
		sleepInSecond(1);
		assertEquals(jsExecutor.executeScript("return arguments[0].innerText",driver.findElement(By.cssSelector("li.success-msg span"))),
				"Thank you for your subscription.");
		jsExecutor.executeScript("window.location='http://demo.guru99.com/v4/'");
		assertEquals(jsExecutor.executeScript("return document.domain"), "demo.guru99.com");
	}

	//@Test
	public void TC_02_HTML5Validation() {
		driver.get("https://automationfc.github.io/html5/index.html");	
		WebElement submitButton = driver.findElement(By.cssSelector("[name='submit-btn']"));
		WebElement nameTextbox = driver.findElement(By.cssSelector("#fname"));
		WebElement passwordTextbox = driver.findElement(By.cssSelector("#pass"));
		WebElement emailTextbox = driver.findElement(By.cssSelector("#em"));
		WebElement selectAddress = driver.findElement(By.cssSelector("select"));
		String name = "Test";
		String password = "Test";
		String email = "test";
		submitButton.click();
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", nameTextbox),"Please fill out this field.");
		nameTextbox.sendKeys(name);
		submitButton.click();
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", passwordTextbox),"Please fill out this field.");
		passwordTextbox.sendKeys(password);
		submitButton.click();
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", emailTextbox),"Please fill out this field.");
		emailTextbox.sendKeys(email);
		submitButton.click();
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", emailTextbox),
				"Please include an '@' in the email address. '"+email+"' is missing an '@'.");
		email = "test@123.com";
		emailTextbox.clear();
		emailTextbox.sendKeys(email);
		submitButton.click();
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", selectAddress),"Please select an item in the list.");
	}

	//@Test
	public void TC_03_HTML5Validation() {
		driver.get("https://login.ubuntu.com/");
		WebElement logInButton = driver.findElement(By.cssSelector("button[data-qa-id='login_button']"));
		WebElement emailTextbox = driver.findElement(By.cssSelector("input[name='email']"));
		String email ="test";
		jsExecutor.executeScript("arguments[0].click()", logInButton);
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", emailTextbox),"Please fill out this field.");
		emailTextbox.sendKeys(email);
		jsExecutor.executeScript("arguments[0].click()", logInButton);
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", emailTextbox),
				"Please include an '@' in the email address. '"+email+"' is missing an '@'.");
		
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		logInButton = driver.findElement(By.cssSelector("button[value='Đăng ký']"));
		emailTextbox = driver.findElement(By.cssSelector("input[data-validation='email']"));
		driver.findElement(By.cssSelector("#lastName")).sendKeys("thuc");
		driver.findElement(By.cssSelector("#firstName")).sendKeys("nguyen");
		jsExecutor.executeScript("arguments[0].click()", logInButton);
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", emailTextbox),"Please fill out this field.");
		emailTextbox.sendKeys(email);
		jsExecutor.executeScript("arguments[0].click()", logInButton);
		assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage", emailTextbox),
				"Please include an '@' in the email address. '"+email+"' is missing an '@'.");
	}
	
	@Test
	public void TC_04_CreateAccount() {
		String firstName = "Thuc";
		String lastName = "Nguyen";
		String email = "ThucNguyenTest"+randomNumber()+"@gmail.com";
		String password ="123456";
		String confirmPassword = "123456"; 
		jsExecutor.executeScript("document.location = 'http://live.demoguru99.com'");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector(".buttons-set a.button")));
		jsExecutor.executeScript("arguments[0].value = '"+firstName+"'", driver.findElement(By.cssSelector("#firstname")));
		jsExecutor.executeScript("arguments[0].value = '"+lastName+"'", driver.findElement(By.cssSelector("#lastname")));
		jsExecutor.executeScript("arguments[0].value = '"+email+"'", driver.findElement(By.cssSelector("#email_address")));
		jsExecutor.executeScript("arguments[0].value = '"+password+"'", driver.findElement(By.cssSelector("#password")));
		jsExecutor.executeScript("arguments[0].value = '"+confirmPassword+"'", driver.findElement(By.cssSelector("#confirmation")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button[title='Register']")));
		sleepInSecond(1);
		assertEquals(jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(By.cssSelector(".success-msg span"))),
				"Thank you for registering with Main Website Store.");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']")));
		assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='You are now logged out']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/../p[text()='You have logged out and will be redirected to our homepage in 5 seconds.']")).isDisplayed());
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void addItemToCart(String item) {
		WebElement selectAction = driver.findElement(By.xpath(""
				+ "//h2//a[@title='"+item+"']/../following-sibling::div[@class='actions']/button[@title='Add to Cart']"));
		jsExecutor.executeScript("arguments[0].click()", selectAction);
		sleepInSecond(1);
	}

	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
}
