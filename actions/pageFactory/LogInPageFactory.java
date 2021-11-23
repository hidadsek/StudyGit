package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LogInPageFactory extends BasePageFactory  {
private WebDriver driver;
	
	public LogInPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id ='Email']")
	WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@id ='Password']")
	WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[text()='Log in']")
	WebElement loginButton;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	WebElement emailErrorText;
	
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	WebElement loginErrorText;
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, emailTextbox);
		inputIntoElement(emailTextbox, email);
	}
	
	public void inputPassword(String password) {
		waitForElementVisible(driver, passwordTextbox);
		inputIntoElement(passwordTextbox, password);
	}
	
	public void clickLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickElement(loginButton);
	}
	
	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorText);
		return getElementText(emailErrorText);
	}
	
	public String getLoginErrorMessage() {
		waitForElementVisible(driver, loginErrorText);
		return getElementText(loginErrorText);
	}
}
