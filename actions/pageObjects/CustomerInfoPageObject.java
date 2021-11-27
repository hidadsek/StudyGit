package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUI.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	public CustomerInfoPageUI customerInfoPageUI = new CustomerInfoPageUI();
	public CustomerInfoPageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectMaleGender() {
		waitForElementClickable(driver, customerInfoPageUI.maleRadioButtonBy);
		clickElement(driver,customerInfoPageUI.maleRadioButtonBy);
	}
	
	public void selectFemaleGender() {
		waitForElementClickable(driver, customerInfoPageUI.femaleRadioButtonBy);
		clickElement(driver,customerInfoPageUI.femaleRadioButtonBy);
	}
	
	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, customerInfoPageUI.firstNameTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.firstNameTextboxBy, firstName);
	}
	
	public void inputLastName(String lastName) {
		waitForElementVisible(driver, customerInfoPageUI.lastNameTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.lastNameTextboxBy, lastName);
	}
	
	public void selectDay(String day) {
		waitForElementVisible(driver, customerInfoPageUI.dayDropdownListBy);
		selectItemInDefaultDropdown(driver,customerInfoPageUI.dayDropdownListBy, day);
	}
	
	public void selectMonth(String month) {
		waitForElementVisible(driver, customerInfoPageUI.monthDropdownListBy);
		selectItemInDefaultDropdown(driver,customerInfoPageUI.monthDropdownListBy, month);
	}
	
	public void selectYear(String year) {
		waitForElementVisible(driver, customerInfoPageUI.yearDropdownListBy);
		selectItemInDefaultDropdown(driver,customerInfoPageUI.yearDropdownListBy, year);
	}
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, customerInfoPageUI.emailTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.emailTextboxBy, email);
	}
	
	public void inputCompany(String company) {
		waitForElementVisible(driver, customerInfoPageUI.companyTextboxBy);
		inputIntoElement(driver,customerInfoPageUI.companyTextboxBy, company);
	}
	
	public void selectNewsletter() {
		waitForElementClickable(driver, customerInfoPageUI.newsletterCheckboxBy);
		checkToDefaultCheckboxRadio(driver, customerInfoPageUI.newsletterCheckboxBy);
	}
	
	public void clickSave() {
		waitForElementClickable(driver, customerInfoPageUI.saveButtonBy);
		clickElement(driver, customerInfoPageUI.saveButtonBy);
	}
}
