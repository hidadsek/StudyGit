package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputPassword(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		inputIntoElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void clickLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	
	public String getEmailErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getLoginErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
	}
}
