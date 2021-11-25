package pageObjectInit;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObjext;
import pageUI.HomePageUI;

public class HomePageObjectInit extends BasePage {
private WebDriver driver;
	
	public HomePageObjectInit(WebDriver driver) {
		this.driver = driver;
	}
	
	public RegisterPageObjextInit clickRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickElement(driver, HomePageUI.REGISTER_LINK);
		return new RegisterPageObjextInit(driver);
	}
	
	public void clickLogOutLink() {
		waitForElementClickable(driver, HomePageUI.LOGOUT_LINK);
		clickElement(driver, HomePageUI.LOGOUT_LINK);
	}
	
	public LoginPageObject clickLogInLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickElement(driver, HomePageUI.LOGIN_LINK);
		return new LoginPageObject(driver);
	}
	
	public void clickMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
}
