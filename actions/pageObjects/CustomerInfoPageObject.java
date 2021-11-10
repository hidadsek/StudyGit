package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUI.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	public CustomerInfoPageUI customerInfoPageUI = new CustomerInfoPageUI();
	
	public void openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, customerInfoPageUI.customerInfoLinkBy);
		clickElement(driver,customerInfoPageUI.customerInfoLinkBy);
	}
	
	public void selectMaleGender(WebDriver driver) {
		waitForElementClickable(driver, customerInfoPageUI.maleRadioButtonBy);
		clickElement(driver,customerInfoPageUI.maleRadioButtonBy);
	}
	
	public void selectFemaleGender(WebDriver driver) {
		waitForElementClickable(driver, customerInfoPageUI.femaleRadioButtonBy);
		clickElement(driver,customerInfoPageUI.femaleRadioButtonBy);
	}
	
	public void inputFirstName(WebDriver driver, String firstName) {
		waitForElementVisible(driver, customerInfoPageUI.firstNameTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.firstNameTextboxBy, firstName);
	}
	
	public void inputLastName(WebDriver driver, String lastName) {
		waitForElementVisible(driver, customerInfoPageUI.lastNameTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.lastNameTextboxBy, lastName);
	}
	
	public void selectDay(WebDriver driver, String day) {
		waitForElementVisible(driver, customerInfoPageUI.dayDropdownListBy);
		selectItemInDefaultDropdown(driver,customerInfoPageUI.dayDropdownListBy, day);
	}
	
	public void selectMonth(WebDriver driver, String month) {
		waitForElementVisible(driver, customerInfoPageUI.monthDropdownListBy);
		selectItemInDefaultDropdown(driver,customerInfoPageUI.monthDropdownListBy, month);
	}
	
	public void selectYear(WebDriver driver, String year) {
		waitForElementVisible(driver, customerInfoPageUI.yearDropdownListBy);
		selectItemInDefaultDropdown(driver,customerInfoPageUI.yearDropdownListBy, year);
	}
	
	public void inputEmail(WebDriver driver, String email) {
		waitForElementVisible(driver, customerInfoPageUI.emailTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.emailTextboxBy, email);
	}
	
	public void inputCompany(WebDriver driver, String company) {
		waitForElementVisible(driver, customerInfoPageUI.companyTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.companyTextboxBy, company);
	}
	
	public void selectNewsletter(WebDriver driver) {
		waitForElementClickable(driver, customerInfoPageUI.newsletterCheckboxBy);
		checkToDefaultCheckboxRadio(driver, customerInfoPageUI.newsletterCheckboxBy);
	}
	
	public void clickSave(WebDriver driver) {
		waitForElementClickable(driver, customerInfoPageUI.saveButtonBy);
		clickElement(driver, customerInfoPageUI.saveButtonBy);
	}
}
