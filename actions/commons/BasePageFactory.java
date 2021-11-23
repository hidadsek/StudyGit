package commons;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	
	public void clickElement(WebElement element) {
		element.click();
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	public void inputIntoElement(WebElement element, String input) {
		element.clear();
		element.sendKeys(input);
	}
	
	public void selectItemInDefaultDropdown(WebElement element, String input) {
		Select selectDropdownList = new Select(element);
		selectDropdownList.selectByVisibleText(input);
	}
	
	public String getItemInDefaultDropdown(WebElement element) {
		Select selectDropdownList = new Select(element);
		return selectDropdownList.getFirstSelectedOption().getText();
	}
	
	public void waitForAllElementsInvisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void waitForElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
}
