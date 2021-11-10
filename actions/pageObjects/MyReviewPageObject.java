package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.MyReviewPageUI;

public class MyReviewPageObject extends BasePage {
	public MyReviewPageUI myReviewPageUI = new MyReviewPageUI();
	
	public void openMyReviewPage(WebDriver driver) {
		waitForElementClickable(driver, myReviewPageUI.myReviewLinkBy);
		clickElement(driver, myReviewPageUI.myReviewLinkBy);
	}
}
