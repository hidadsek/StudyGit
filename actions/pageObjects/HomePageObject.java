package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openHomePage() {
		openBrowser(driver, "https://demo.nopcommerce.com");
	}
		
	public RegisterPageObject clickRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	public HomePageObject clickLogOutLink() {
		waitForElementClickable(driver, HomePageUI.LOGOUT_LINK);
		clickElement(driver, HomePageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public LoginPageObject clickLogInLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
}
