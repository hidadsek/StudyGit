package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.ChangePasswordPageUI;

public class ChangePasswordPageObject extends BasePage {	
	private WebDriver driver;	
	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputOldPassword(String password) {
		waitForElementVisible(driver, ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX);
		inputIntoElement(driver, ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX, password);
	}
	
	public void inputNewPassword(String password) {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX);
		inputIntoElement(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, password);
	}
	
	public void inputConfirmPassword(String password) {
		waitForElementVisible(driver, ChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX);
		inputIntoElement(driver, ChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}
	
	public void clickChangePassword() {
		waitForElementClickable(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickElement(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
	
	public void closeSuccessPopUp() {
		waitForElementClickable(driver, ChangePasswordPageUI.CLOSE_POPUP_BUTTON);
		clickElement(driver, ChangePasswordPageUI.CLOSE_POPUP_BUTTON);
	}
}
