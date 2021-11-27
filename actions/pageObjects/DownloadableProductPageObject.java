package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DownloadableProductPageObject extends BasePage {
	private WebDriver driver;	
	public DownloadableProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
