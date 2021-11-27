package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.ProductReviewPageUI;

public class ProductReviewPageObject extends BasePage {
	private WebDriver driver;
	
	public ProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputReviewTitle(String reviewTitle) {
		waitForElementVisible(driver, ProductReviewPageUI.REVIEW_TITLE_TEXTBOX);
		inputIntoElement(driver, ProductReviewPageUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
	}
	
	public void inputReviewText(String reviewText) {
		waitForElementVisible(driver, ProductReviewPageUI.REVIEW_TEXTAREA);
		inputIntoElement(driver, ProductReviewPageUI.REVIEW_TEXTAREA, reviewText);
	}
	
	public void clickRating(String rating) {
		String xpath = ProductReviewPageUI.VALUE_RADIOBUTTON+ rating + "']";
		waitForElementVisible(driver, xpath);
		clickElement(driver, xpath);
	}
	
	public void clickSubmitReview() {
		waitForElementVisible(driver, ProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
		clickElement(driver, ProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
	}
}
