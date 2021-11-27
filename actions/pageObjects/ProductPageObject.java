package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.ProductPageUI;

public class ProductPageObject extends BasePage {
	private WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}	
	public void clickReview() {
		waitForElementClickable(driver, ProductPageUI.ADD_REVIEW_BUTTON);
		clickElement(driver, ProductPageUI.ADD_REVIEW_BUTTON);
	}
}