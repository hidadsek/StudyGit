package webdriver;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_HTML_DropdownList_Exercise {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_VerifyCountryHTMLDropdownlist() {
		// Initial elements
		// Page's input value
		String country = "Vietnam";
		String searchResultNumber = "29";
		
		// Page's locator
		By countryLoc = By.id("where_country");
		By searchLoc = By.id("search_loc_submit");

		
		//Truy cập trang
		driver.get("https://www.rode.com/wheretobuy");
		WebElement searchButton = driver.findElement(searchLoc);
		Select selectCountry = new Select(driver.findElement(countryLoc));
		
		//Kiểm tra dropdown không hỗ trợ multi select		
		assertFalse(selectCountry.isMultiple());
		
		//Chọn giá trị Viet Nam
		selectCountry.selectByValue(country);
		
		//Kiểm tra giá trị đã được chọn
		assertEquals(driver.findElement(countryLoc).getAttribute("value"), country);
		
		//click search
		searchButton.click();
		sleepInSecond(1);
		// Kiểm tra số giá trị trả về
		// Hỏi về bug hiện 2 lần search result: We found 11 results within a 10km radius of 29 results within a10km radius of, Vietnam
		//assertTrue(resultText.contains(searchResultNumber));
		List<WebElement> resultText = driver.findElements(By.cssSelector(".result_count>span"));
		for (int i = 0; i < resultText.size(); i++) {
			System.out.println(resultText.get(i).getText());
			if(resultText.get(i).getText().equals(searchResultNumber)) {
				assertTrue(true);
				break;
			}
			if(i == resultText.size()-1) {
				assertTrue(false);
			}
		}
		
		// Print tất cả distributor thuộc VN 
		List<WebElement> resultList = driver.findElements(By.cssSelector(".result_item .store_name"));
		for (WebElement element:resultList) {
			System.out.println(element.getText());
		}
	}
	
	@Test
	public void TC_02_VerifyDOByHTMLDropdownlist() {
		// Initial elements
		//Page value
		String firstName = "Thuc";
		String lastName = "Nguyen";
		String email = "thucTest"+randomNumber()+"@gmail.com";
		String companyName = "Thuc Testing";
		String password = "123456";
		String confirmPassword = "123456";
		String day = "11";
		String month = "5";
		String year = "1995";
		
		//Page Element
		By dayLoc = By.name("DateOfBirthDay");
		By monthLoc = By.name("DateOfBirthMonth");
		By yearLoc = By.name("DateOfBirthYear");
		
		//Truy cập trang
		driver.get("https://demo.nopcommerce.com/register");
		
		//Click Register link
		driver.findElement(By.linkText("Register")).click();
		
		//Bắt element và nhập dữ liệu
		WebElement gendereRadioButton = driver.findElement(By.id("gender-male"));
		WebElement firstNameTextbox = driver.findElement(By.id("FirstName"));
		WebElement lastNameTextbox = driver.findElement(By.id("LastName"));
		WebElement emailTextbox = driver.findElement(By.id("Email"));
		WebElement companyNameTextbox = driver.findElement(By.id("Company"));
		WebElement passwordTextbox = driver.findElement(By.id("Password"));
		WebElement confirmPasswordTextbox = driver.findElement(By.id("ConfirmPassword"));
		WebElement registerButton = driver.findElement(By.id("register-button"));
		Select selectDay = new Select(driver.findElement(dayLoc));
		Select selectMonth = new Select(driver.findElement(monthLoc));
		Select selectYear = new Select(driver.findElement(yearLoc));
		
		gendereRadioButton.click();
		inputText(firstNameTextbox, firstName);
		inputText(lastNameTextbox, lastName);
		inputText(emailTextbox, email);
		inputText(companyNameTextbox, companyName);
		inputText(passwordTextbox, password);
		inputText(confirmPasswordTextbox, confirmPassword);
		
		//Nhập ngày, tháng, năm
		selectDay.selectByValue(day);
		selectMonth.selectByValue(month);
		selectYear.selectByValue(year);
		
		//Vevify số ngày, tháng, năm trong dropdownlist
		List<WebElement> dayList = selectDay.getOptions();
		assertEquals(dayList.size(), 32);
		
		List<WebElement> monthList = selectMonth.getOptions();
		assertEquals(monthList.size(), 13);
		
		List<WebElement> yearList = selectYear.getOptions();
		assertEquals(yearList.size(), 112);
		
		//click Register
		registerButton.click();
		
		//verify đăng ký thành công
		assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		//click My Account link
		driver.findElement(By.linkText("My account")).click();
		
		//Verify value của field DOB
		assertEquals(driver.findElement(dayLoc).getAttribute("value"), day);
		assertEquals(driver.findElement(monthLoc).getAttribute("value"), month);
		assertEquals(driver.findElement(yearLoc).getAttribute("value"), year);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber(){
		Random rand = new Random();
		return rand.nextInt(10000);
	}

	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void inputText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
}