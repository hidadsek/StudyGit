package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.ProductPageUI;

public class ProductPageObject extends BasePage {
	public ProductPageUI productPageUI = new ProductPageUI();
	
	public void clickReview(WebDriver driver) {
		waitForElementClickable(driver, productPageUI.addReviewLinkBy);
		clickElement(driver, productPageUI.addReviewLinkBy);
	}
}