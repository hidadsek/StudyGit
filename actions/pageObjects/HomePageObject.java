package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickElement(driver, HomePageUI.REGISTER_LINK);
	}
	
	public void clickLogOutLink() {
		waitForElementClickable(driver, HomePageUI.LOGOUT_LINK);
		clickElement(driver, HomePageUI.LOGOUT_LINK);
	}
	
	public void clickLogInLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickElement(driver, HomePageUI.LOGIN_LINK);
	}
	
	public void clickMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
}
