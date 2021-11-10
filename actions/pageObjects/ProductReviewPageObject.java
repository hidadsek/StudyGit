package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.ProductReviewPageUI;

public class ProductReviewPageObject extends BasePage {
	public ProductReviewPageUI productReviewPage = new ProductReviewPageUI();
	
	public void inputReviewTitle(WebDriver driver, String reviewTitle) {
		waitForElementVisible(driver, productReviewPage.reviewTitleTextboxBy);
		inputIntoElement(driver, productReviewPage.reviewTitleTextboxBy, reviewTitle);
	}
	
	public void inputReviewText(WebDriver driver, String reviewText) {
		waitForElementVisible(driver, productReviewPage.reviewTextAreaBy);
		inputIntoElement(driver, productReviewPage.reviewTextAreaBy, reviewText);
	}
	
	public void clickRating(WebDriver driver, String rating) {
		String xpath = productReviewPage.valueRadioButtonBy+ rating + "']";
		waitForElementVisible(driver, xpath);
		clickElement(driver, xpath);
	}
	
	public void clickSubmitReview(WebDriver driver) {
		waitForElementVisible(driver, productReviewPage.submitReviewButtonBy);
		clickElement(driver, productReviewPage.submitReviewButtonBy);
	}
}
