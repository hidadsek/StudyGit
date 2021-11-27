package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.MyReviewPageUI;

public class MyProductReviewPageObject extends BasePage {
	private WebDriver driver;	
	public MyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
