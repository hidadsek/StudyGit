package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.RegisterPageUI;

public class RegisterPageObjext extends BasePage {
	RegisterPageUI pageUI = new RegisterPageUI();
		
	public void selectMaleGender(WebDriver driver) {
		waitForElementClickable(driver, pageUI.maleRadioButtonBy);
		clickElement(driver,pageUI.maleRadioButtonBy);
	}
	
	public void selectFemaleGender(WebDriver driver) {
		waitForElementClickable(driver, pageUI.femaleRadioButtonBy);
		clickElement(driver,pageUI.femaleRadioButtonBy);
	}
	
	public void inputFirstName(WebDriver driver, String firstName) {
		waitForElementVisible(driver, pageUI.firstNameTextboxBy);
		inputIntoElement(driver,pageUI.firstNameTextboxBy, firstName);
	}
	
	public void inputLastName(WebDriver driver, String lastName) {
		waitForElementVisible(driver, pageUI.lastNameTextboxBy);
		inputIntoElement(driver,pageUI.lastNameTextboxBy, lastName);
	}
	
	public void selectDay(WebDriver driver, String day) {
		waitForElementVisible(driver, pageUI.dayDropdownListBy);
		selectItemInDefaultDropdown(driver,pageUI.dayDropdownListBy, day);
	}
	
	public void selectMonth(WebDriver driver, String month) {
		waitForElementVisible(driver, pageUI.monthDropdownListBy);
		selectItemInDefaultDropdown(driver,pageUI.monthDropdownListBy, month);
	}
	
	public void selectYear(WebDriver driver, String year) {
		waitForElementVisible(driver, pageUI.yearDropdownListBy);
		selectItemInDefaultDropdown(driver,pageUI.yearDropdownListBy, year);
	}
	
	public void inputEmail(WebDriver driver, String email) {
		waitForElementVisible(driver, pageUI.emailTextboxBy);
		inputIntoElement(driver,pageUI.emailTextboxBy, email);
	}
	
	public void inputCompany(WebDriver driver, String company) {
		waitForElementVisible(driver, pageUI.companyTextboxBy);
		inputIntoElement(driver,pageUI.companyTextboxBy, company);
	}
	
	public void selectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, pageUI.newsletterCheckboxBy);
		checkToDefaultCheckboxRadio(driver, pageUI.newsletterCheckboxBy);
	}
	
	public void unSelectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, pageUI.newsletterCheckboxBy);
		uncheckToDefaultCheckboxRadio(driver, pageUI.newsletterCheckboxBy);
	}
	
	public void inputPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, pageUI.passwordTextboxBy);
		inputIntoElement(driver, pageUI.passwordTextboxBy, password);
	}
	
	public void inputConfirmPassword(WebDriver driver, String password) {
		waitForElementVisible(driver, pageUI.confirmPasswordTextboxBy);
		inputIntoElement(driver, pageUI.confirmPasswordTextboxBy, password);
	}
	
	public void clickRegister(WebDriver driver) {
		waitForElementVisible(driver, pageUI.registerButtonBy);
		clickElement(driver, pageUI.registerButtonBy);
	}
}
