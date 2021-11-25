package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class HomePageFactory extends BasePageFactory {
	private WebDriver driver;
	
	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='header-links']//a[text()='Register']")
	WebElement registerLink;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='header-links']//a[text()='Log in']")
	WebElement loginLink;
	
	@CacheLookup
	@FindBy(xpath = "//a[text()='Log out']")
	WebElement logoutLink;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='header-links']//a[text()='My account']")
	WebElement myAccountLink;
	
	public void clickRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickElement(registerLink);
	}
	
	public void clickLogOutLink() {
		waitForElementClickable(driver, logoutLink);
		clickElement(logoutLink);
	}
	
	public void clickLogInLink() {
		waitForElementClickable(driver, loginLink);
		clickElement(loginLink);
	}
	
	public void clickMyAccountLink() {
		waitForElementClickable(driver, myAccountLink);
		clickElement(myAccountLink);
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(myAccountLink);
	}
}
