package com.datatable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DataTableObject extends BasePage {
	WebDriver driver;
	public DataTableObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openPageByNumber(String pageNumber) {
		waitForElementClickable(driver,DataTableUI.PAGING_BY_NUMBER, pageNumber);
		clickElement(driver,DataTableUI.PAGING_BY_NUMBER, pageNumber);
	}
	
	public boolean isPageActiveByNumber(String pageNumber) {
		waitForElementVisible(driver,DataTableUI.PAGING_BY_NUMBER_ACTIVE, pageNumber);
		return isElementDisplayed(driver,DataTableUI.PAGING_BY_NUMBER_ACTIVE, pageNumber);
	}

	public void inputToHeaderTextboxByName(String header, String value) {
		waitForElementClickable(driver,DataTableUI.HEADER_TEXTBOX_BY_NAME, header);
		inputIntoElement(driver,DataTableUI.HEADER_TEXTBOX_BY_NAME,value,header);
		sendKeyboardToElement(driver, DataTableUI.HEADER_TEXTBOX_BY_NAME, Keys.ENTER, header);
	}

	public void clickToIconByCountry(String countryName, String iconAction) {
		waitForElementClickable(driver,DataTableUI.ICON_BY_COUNTRY,countryName, iconAction);
		clickElement(driver,DataTableUI.ICON_BY_COUNTRY,countryName, iconAction);
	}

	public boolean isRowValueDisplayed(String females, String country, String males, String total) {
		waitForElementVisible(driver,DataTableUI.ROW_VALUE, females,country,males,total);
		return isElementDisplayed(driver,DataTableUI.ROW_VALUE, females,country,males,total);
	}
	
	public void inputFemaleTextbox(String females) {
		waitForElementClickable(driver,DataTableUI.FEMALES_TEXTBOX);
		inputIntoElement(driver,DataTableUI.FEMALES_TEXTBOX,females);
	}
	
	public void inputCountryTextbox(String country) {
		waitForElementClickable(driver,DataTableUI.COUNTRY_TEXTBOX);
		inputIntoElement(driver,DataTableUI.COUNTRY_TEXTBOX,country);
	}
	
	public void inputMaleTextbox(String males) {
		waitForElementClickable(driver,DataTableUI.MALES_TEXTBOX);
		inputIntoElement(driver,DataTableUI.MALES_TEXTBOX,males);
	}
	
	public void inputTotalTextbox(String total) {
		waitForElementClickable(driver,DataTableUI.TOTAL_TEXTBOX);
		inputIntoElement(driver,DataTableUI.TOTAL_TEXTBOX,total);
	}
	
	public void clickOkButton() {
		waitForElementClickable(driver,DataTableUI.OK_BUTTON);
		clickElement(driver,DataTableUI.OK_BUTTON);
	}
}
