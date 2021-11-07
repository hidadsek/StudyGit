package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.RegisterPageUI;

public class RegisterPageObjext extends BasePage {
	public RegisterPageUI registerPageUI = new RegisterPageUI();
		
	public void selectMaleGender(WebDriver driver) {
		waitForElementClickable(driver, registerPageUI.maleRadioButtonBy);
		clickElement(driver,registerPageUI.maleRadioButtonBy);
	}
	
	public void selectFemaleGender(WebDriver driver) {
		waitForElementClickable(driver, registerPageUI.femaleRadioButtonBy);
		clickElement(driver,registerPageUI.femaleRadioButtonBy);
	}
	
	public void inputFirstName(WebDriver driver, String firstName) {
		waitForElementVisible(driver, registerPageUI.firstNameTextboxBy);
		inputIntoElement(driver,registerPageUI.firstNameTextboxBy, firstName);
	}
	
	public void inputLastName(WebDriver driver, String lastName) {
		waitForElementVisible(driver, registerPageUI.lastNameTextboxBy);
		inputIntoElement(driver,registerPageUI.lastNameTextboxBy, lastName);
	}
	
	public void selectDay(WebDriver driver, String day) {
		waitForElementVisible(driver, registerPageUI.dayDropdownListBy);
		selectItemInDefaultDropdown(driver,registerPageUI.dayDropdownListBy, day);
	}
	
	public void selectMonth(WebDriver driver, String month) {
		waitForElementVisible(driver, registerPageUI.monthDropdownListBy);
		selectItemInDefaultDropdown(driver,registerPageUI.monthDropdownListBy, month);
	}
	
	public void selectYear(WebDriver driver, String year) {
		waitForElementVisible(driver, registerPageUI.yearDropdownListBy);
		selectItemInDefaultDropdown(driver,registerPageUI.yearDropdownListBy, year);
	}
	
	public void inputEmail(WebDriver driver, String email) {
		waitForElementVisible(driver, registerPageUI.emailTextboxBy);
		inputIntoElement(driver,registerPageUI.emailTextboxBy, email);
	}
	
	public void inputCompany(WebDriver driver, String company) {
		waitForElementVisible(driver, registerPageUI.companyTextboxBy);
		inputIntoElement(driver,registerPageUI.companyTextboxBy, company);
	}
	
	public void selectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, registerPageUI.newsletterCheckboxBy);
		checkToDefaultCheckboxRadio(driver, registerPageUI.newsletterCheckboxBy);
	}
	
	public void unSelectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, registerPageUI.newsletterCheckboxBy);
		uncheckToDefaultCheckboxRadio(driver, registerPageUI.newsletterCheckboxBy);
	}
	
	public void inputPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, registerPageUI.passwordTextboxBy);
		inputIntoElement(driver, registerPageUI.passwordTextboxBy, password);
	}
	
	public void inputConfirmPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, registerPageUI.confirmPasswordTextboxBy);
		inputIntoElement(driver, registerPageUI.confirmPasswordTextboxBy, password);
	}
	
	public void clickRegister(WebDriver driver) {
		waitForElementVisible(driver, registerPageUI.registerButtonBy);
		clickElement(driver, registerPageUI.registerButtonBy);
	}
}
