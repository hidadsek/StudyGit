package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.SearchPageUI;

public class SearchPageObject extends BasePage {
	public SearchPageUI searchPageUI = new SearchPageUI();
	
	public void inputSearch(WebDriver driver, String searchValue) {
		waitForElementVisible(driver, searchPageUI.searchTextboxBy);
		inputIntoElement(driver, searchPageUI.searchTextboxBy,searchValue);
	}
	
	public void clickSearch(WebDriver driver) {
		waitForElementClickable(driver, searchPageUI.searchButtonBy);
		clickElement(driver, searchPageUI.searchButtonBy);
	}
	
	public void selectProduct(WebDriver driver, String searchValue) {
		String xpath = searchPageUI.productTitleBy + searchValue + "')]";
		waitForElementClickable(driver, xpath);
		clickElement(driver, xpath);
	}
}
