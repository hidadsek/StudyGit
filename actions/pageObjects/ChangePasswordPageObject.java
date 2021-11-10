package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.ChangePasswordPageUI;

public class ChangePasswordPageObject extends BasePage {
	public ChangePasswordPageUI changePasswordPageUI = new ChangePasswordPageUI();
	
	public void openChangePasswordPage (WebDriver driver) {
		waitForElementClickable(driver, changePasswordPageUI.changePasswordLinkBy);
		clickElement(driver, changePasswordPageUI.changePasswordLinkBy);
	}
	
	public void inputOldPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, changePasswordPageUI.oldPasswordTextboxBy);
		inputIntoElement(driver, changePasswordPageUI.oldPasswordTextboxBy, password);
	}
	
	public void inputNewPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, changePasswordPageUI.newPasswordTextboxBy);
		inputIntoElement(driver, changePasswordPageUI.newPasswordTextboxBy, password);
	}
	
	public void inputConfirmPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, changePasswordPageUI.confirmPasswordTextboxBy);
		inputIntoElement(driver, changePasswordPageUI.confirmPasswordTextboxBy, password);
	}
	
	public void clickChangePassword(WebDriver driver) {
		waitForElementClickable(driver, changePasswordPageUI.confirmPasswordTextboxBy);
		clickElement(driver, changePasswordPageUI.changePasswordButtonBy);
	}
	
	public void closeSuccessPopUp(WebDriver driver) {
		waitForElementClickable(driver, changePasswordPageUI.closePopupButtonBy);
		clickElement(driver, changePasswordPageUI.closePopupButtonBy);
	}
}
