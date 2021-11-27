package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.MyReviewPageUI;

public class BackInStockSubscriptionPageObject extends BasePage {
	private WebDriver driver;	
	public BackInStockSubscriptionPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
