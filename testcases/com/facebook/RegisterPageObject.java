package com.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	public void clickRegisterLink() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_LINK);
		clickElement(driver, RegisterPageUI.REGISTER_LINK);
	}

	public boolean isEmailTextboxDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_TEXTBOX);
	}

	public boolean isConfirmEmailTextboxDisplayed() {
		return isElementDisplayed(driver, RegisterPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public boolean isLoginButtonNotDisplayed() {
		return isElementUndisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
	}
	
	public boolean isLoginButtonDisplayed() {
		return isElementDisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
	}
}
