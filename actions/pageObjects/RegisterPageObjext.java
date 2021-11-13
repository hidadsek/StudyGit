package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.RegisterPageUI;

public class RegisterPageObjext extends BasePage {
		
	public void selectMaleGender(WebDriver driver) {
		waitForElementClickable(driver, RegisterPageUI.MALE_RADIO_BUTTON);
		clickElement(driver,RegisterPageUI.MALE_RADIO_BUTTON);
	}
	
	public void selectFemaleGender(WebDriver driver) {
		waitForElementClickable(driver, RegisterPageUI.FEMALE_RADIO_BUTTON);
		clickElement(driver,RegisterPageUI.FEMALE_RADIO_BUTTON);
	}
	
	public void inputFirstName(WebDriver driver, String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		inputIntoElement(driver,RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}
	
	public void inputLastName(WebDriver driver, String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		inputIntoElement(driver,RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}
	
	public void selectDay(WebDriver driver, String day) {
		waitForElementVisible(driver, RegisterPageUI.DAY_DROPDOWN);
		selectItemInDefaultDropdown(driver,RegisterPageUI.DAY_DROPDOWN, day);
	}
	
	public void selectMonth(WebDriver driver, String month) {
		waitForElementVisible(driver, RegisterPageUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver,RegisterPageUI.MONTH_DROPDOWN, month);
	}
	
	public void selectYear(WebDriver driver, String year) {
		waitForElementVisible(driver, RegisterPageUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver,RegisterPageUI.YEAR_DROPDOWN, year);
	}
	
	public void inputEmail(WebDriver driver, String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver,RegisterPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputCompany(WebDriver driver, String company) {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
		inputIntoElement(driver,RegisterPageUI.COMPANY_TEXTBOX, company);
	}
	
	public void selectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
	}
	
	public void unSelectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
		uncheckToDefaultCheckboxRadio(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
	}
	
	public void inputPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		inputIntoElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void inputConfirmPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		inputIntoElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	public void clickRegisterButton(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_BUTTON);
		clickElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	public String getFirstNameErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}
	
	
	public String getLastNameErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}
	
	public String getEmailErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getPasswordErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}
	
	public String getConfirmPasswordErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}
	
	public String getExistingEmailErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementAttribute(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE,"textContent");
	}
	
	public String getSuccessMessage(WebDriver driver) {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}
	
}
