package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.AddressPageUI;

public class AddressPageObject extends BasePage {
	public AddressPageUI addressPageUI = new AddressPageUI();
	
	public void openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, addressPageUI.addressLinkBy);
		clickElement(driver, addressPageUI.addressLinkBy);
	}
	
	public void clickAddNewButton(WebDriver driver) {
		waitForElementClickable(driver, addressPageUI.addButtonBy);
		clickElement(driver, addressPageUI.addButtonBy);
	}
	
	public void inputFirstName(WebDriver driver, String firstName) {
		waitForElementVisible(driver, addressPageUI.firstNameTextboxBy);
		inputIntoElement(driver,addressPageUI.firstNameTextboxBy, firstName);
	}
	
	public void inputLastName(WebDriver driver, String lastName) {
		waitForElementVisible(driver, addressPageUI.lastNameTextboxBy);
		inputIntoElement(driver,addressPageUI.lastNameTextboxBy, lastName);
	}
	
	public void inputEmail(WebDriver driver, String email) {
		waitForElementVisible(driver, addressPageUI.emailTextboxBy);
		inputIntoElement(driver,addressPageUI.emailTextboxBy, email);
	}
	
	public void inputCompany(WebDriver driver, String company) {
		waitForElementVisible(driver, addressPageUI.companyTextboxBy);
		inputIntoElement(driver,addressPageUI.companyTextboxBy, company);
	}

	public void selectCountry(WebDriver driver, String country) {
		waitForElementVisible(driver, addressPageUI.countryDropdownListBy);
		selectItemInDefaultDropdown(driver,addressPageUI.countryDropdownListBy, country);
	}
	
	public void selectState(WebDriver driver, String state) {
		waitForElementVisible(driver, addressPageUI.stateDropdownListBy);
		selectItemInDefaultDropdown(driver,addressPageUI.stateDropdownListBy, state);
	}
	
	public void inputCity(WebDriver driver, String city) {
		waitForElementVisible(driver, addressPageUI.cityTextboxBy);
		inputIntoElement(driver,addressPageUI.cityTextboxBy, city);
	}
	
	public void inputAddress1(WebDriver driver, String address) {
		waitForElementVisible(driver, addressPageUI.address1TextboxBy);
		inputIntoElement(driver,addressPageUI.address1TextboxBy, address);
	}
	
	public void inputAddress2(WebDriver driver, String address) {
		waitForElementVisible(driver, addressPageUI.address2TextboxBy);
		inputIntoElement(driver,addressPageUI.address2TextboxBy, address);
	}
	
	public void inputZip(WebDriver driver, String zip) {
		waitForElementVisible(driver, addressPageUI.zipTextboxBy);
		inputIntoElement(driver,addressPageUI.zipTextboxBy, zip);
	}
	
	public void inputPhoneNumber(WebDriver driver, String phoneNumber) {
		waitForElementVisible(driver, addressPageUI.phoneTextboxBy);
		inputIntoElement(driver,addressPageUI.phoneTextboxBy, phoneNumber);
	}
	
	public void inputFaxNumber(WebDriver driver, String faxNumber) {
		waitForElementVisible(driver, addressPageUI.companyTextboxBy);
		inputIntoElement(driver,addressPageUI.companyTextboxBy, faxNumber);
	}
	
	public void clickSaveButton(WebDriver driver) {
		waitForElementClickable(driver, addressPageUI.saveButtonBy);
		clickElement(driver, addressPageUI.saveButtonBy);
	}
}
