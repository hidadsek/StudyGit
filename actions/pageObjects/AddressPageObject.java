package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.AddressPageUI;

public class AddressPageObject extends BasePage {
	private WebDriver driver;	
	public AddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAddNewButton() {
		waitForElementClickable(driver, AddressPageUI.ADD_BUTTON);
		clickElement(driver, AddressPageUI.ADD_BUTTON);
	}
	
	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, AddressPageUI.FIRSTNAME_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void inputLastName(String lastName) {
		waitForElementVisible(driver, AddressPageUI.LASTNAME_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.LASTNAME_TEXTBOX, lastName);
	}
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, AddressPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputCompany(String company) {
		waitForElementVisible(driver, AddressPageUI.COMPANY_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.COMPANY_TEXTBOX, company);
	}

	public void selectCountry(String country) {
		waitForElementVisible(driver, AddressPageUI.COUNTRY_DROPDOWN);
		selectItemInDefaultDropdown(driver,AddressPageUI.COUNTRY_DROPDOWN, country);
	}
	
	public void selectState(String state) {
		waitForElementVisible(driver, AddressPageUI.STATE_DROPDOWN);
		selectItemInDefaultDropdown(driver,AddressPageUI.STATE_DROPDOWN, state);
	}
	
	public void inputCity(String city) {
		waitForElementVisible(driver, AddressPageUI.CITY_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.CITY_TEXTBOX, city);
	}
	
	public void inputAddress1(String address) {
		waitForElementVisible(driver, AddressPageUI.ADDRESS_1_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.ADDRESS_1_TEXTBOX, address);
	}
	
	public void inputAddress2(String address) {
		waitForElementVisible(driver, AddressPageUI.ADDRESS_2_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.ADDRESS_2_TEXTBOX, address);
	}
	
	public void inputZip(String zip) {
		waitForElementVisible(driver, AddressPageUI.ZIP_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.ZIP_TEXTBOX, zip);
	}
	
	public void inputPhoneNumber(String phoneNumber) {
		waitForElementVisible(driver, AddressPageUI.PHONE_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.PHONE_TEXTBOX, phoneNumber);
	}
	
	public void inputFaxNumber(String faxNumber) {
		waitForElementVisible(driver, AddressPageUI.FAX_TEXTBOX);
		inputIntoElement(driver,AddressPageUI.FAX_TEXTBOX, faxNumber);
	}
	
	public void clickSaveButton() {
		waitForElementClickable(driver, AddressPageUI.SAVE_BUTTON);
		clickElement(driver, AddressPageUI.SAVE_BUTTON);
	}
}
