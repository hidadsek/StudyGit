package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.LoginPageUI;

public class LoginPageObject extends BasePage {
	LoginPageUI loginPageUI = new LoginPageUI();
	
	public void inputEmail(WebDriver driver, String email) {
		waitForElementVisible(driver, loginPageUI.emailTextboxBy);
		inputIntoElement(driver, loginPageUI.emailTextboxBy, email);
	}
	
	public void inputPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, loginPageUI.passwordTextboxBy);
		inputIntoElement(driver, loginPageUI.passwordTextboxBy, password);
	}
	
	public void clickLogin(WebDriver driver) {
		waitForElementClickable(driver, loginPageUI.loginButtonBy);
		clickElement(driver, loginPageUI.loginButtonBy);
	}
}
