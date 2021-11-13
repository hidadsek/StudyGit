package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage {
	
	public void clickRegisterLink(WebDriver driver) {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickElement(driver, HomePageUI.REGISTER_LINK);
	}
	
	public void clickLogOutLink(WebDriver driver) {
		waitForElementClickable(driver, HomePageUI.LOGOUT_LINK);
		clickElement(driver, HomePageUI.LOGOUT_LINK);
	}
}
