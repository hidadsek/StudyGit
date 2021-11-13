package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	/** 
	 * Open browser
	 * @param driver
	 * @param Url of page
	 */	
	public void openBrowser(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	/** 
	 * Get Title of the current page
	 * @param driver
	 * @return the title of the current page
	 */	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	/** 
	 * Get URL of the current page
	 * @param driver
	 * @return the url of the current page
	 */	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	/** 
	 * Get html page source code of the current page
	 * @param driver
	 * @return the html source text of the current page
	 */	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	/** 
	 * Back to previous page of the web
	 * @param driver
	 */	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
		
	/** 
	 * Forward to next page of the web
	 * @param driver
	 */	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	/** 
	 * Refresh the current page of the web
	 * @param driver
	 */		
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	/** 
	 * Wait for the browser alert to appear
	 * @param driver
	 */		
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	/** 
	 * Confirm the browser alert
	 * @param driver
	 */		
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	/** 
	 * Close the browser alert
	 * @param driver
	 */		
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	/** 
	 * Get the text of the browser alert
	 * @param driver
	 */		
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	/** 
	 * Switch to another tab (only 2 tabs)
	 * @param driver
	 * @param ID of the first/home/default tab
	 */	
	public void switchToWindowNotHomeByID(WebDriver driver,String homeID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(homeID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	/** 
	 * Switch to another tab using window/tab title
	 * @param driver
	 * @param title of the another tab
	 */	
	public void switchToWindowByTitle(WebDriver driver,String windowTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(windowTitle)) {
				break;
			}
		}
	}
	
	/** 
	 * Close all tabs except the first/home/default tab
	 * @param driver
	 * @param ID of the first/home/default tab
	 */	
	public void closeAllWithoutParent(WebDriver driver,String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
		
	/** 
	 * Input text to the browser alert
	 * @param driver
	 */		
	public void inputToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	/** 
	 * Get xpath xpathLocator of the element
	 * @param xpath of the element
	 * @return xpathLocator of the element
	 */	
	private By getByXpath(String xpath) {
		return By.xpath(xpath);
	}
	
	/** 
	 * Find and get the Web Element
	 * @param driver
	 * @param xpathLocator xpathLocator of the element
	 * @return web element found
	 */	
	private WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	/** 
	 * Find and get the list of Web Element
	 * @param driver
	 * @param xpathLocator xpathLocator of elements
	 * @return list of web element found
	 */	
	private List<WebElement> getListElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	/** 
	 * Click element
	 * @param driver
	 * @param xpathLocator of the element
	 */	
	public void clickElement(WebDriver driver, String xpathLocator) {
		getElement(driver,xpathLocator).click();
	}
	
	/**
	 * Input string value into element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param input value inputed into the element
	 */	
	public void inputIntoElement(WebDriver driver, String xpathLocator, String input) {
		WebElement element = getElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(input);
	}
	
	/**
	 * Select Element in the dropdownList
	 * @param driver
	 * @param xpathLocator of the element
	 * @param input visible text you want to select from the list
	 */	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String input) {
		waitForAllElementsToBePresenced(driver, xpathLocator+"/option");
		Select selectDropdownList = new Select(getElement(driver, xpathLocator));
		selectDropdownList.selectByVisibleText(input);
	}
	
	/**
	 * Get text of the selected item in the dropdownList
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public String getItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select selectDropdownList = new Select(driver.findElement(getByXpath(xpathLocator)));
		return selectDropdownList.getFirstSelectedOption().getText();
	}
	
	/**
	 * Get text of the selected item in the dropdownList
	 * @param driver
	 * @param xpathLocator of the dropdown element
	 * @param xpathLocator of the items in dropdown element
	 * @param value you want to select in the dropdown
	 */
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String itemValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		getElement(driver, parentLocator).click();		
		List<WebElement> allItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
		
		for (WebElement item: allItems) {
			if (item.getText().equals(itemValue)) {
				if(!item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInSecond(1);
				}
				item.click();
				break;
			}
		}
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @return element text
	 */	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).getText();
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param attribute tag name of the element
	 * @return attribute value
	 */	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param css tag name of the element
	 * @return css value of
	 */	
	public String getCssValue(WebDriver driver, String xpathLocator, String cssValue) {
		return getElement(driver, xpathLocator).getCssValue(cssValue);
	}
	
	/**
	 * get color of the rgba
	 * @param driver
	 * @return css value as string
	 */	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	/**
	 * Get size of multiple the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @return size of element list-
	 */
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator)).size();
	}
	
	/**
	 * Check the checkbox to be selected before unchecking it
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	/**
	 * Check the checkbox to be unselected before checking it
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	/**
	 * Check the element to be enabled
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}
	
	/**
	 * Check the element to be displayed
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isDisplayed();
	}
	
	/**
	 * Check the element to be selected
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isSelected();
	}
	
	/**
	 * Switch back to default/main frame
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * Switch to Frame/ iFrame
	 * @param driver
	 * @param xpathLocator of the frame
	 */
	public void switchToFrame(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getElement(driver, xpathLocator));
	}
	
	/**
	 * Hover to element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void moveToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, xpathLocator)).perform();
	}
	
	/**
	 * Interact with keyboard
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void sendKeyboardToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, xpathLocator),key).perform();
	}
	
	/**
	 * Inject a java script to web driver
	 * @param driver
	 * @param javaScript script user want to input
	 * @return result after script running (can be void, text, number...)
	 */
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}
	
	/**
	 * Get the innerText property of element
	 * @param driver
	 * @return innerText of Element
	 */
	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	/**
	 * Compare the innerText property of element with expected text
	 * @param driver
	 * @return true/false
	 */
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}
	
	/**
	 * Scroll to the bottom of the page
	 * @param driver
	 */
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * Navigate to another URL
	 * @param driver
	 * @param url of the landed page
	 */
	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	/**
	 * highlight the border of the element red
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	/**
	 * click the element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, xpathLocator));
	}
	
	/**
	 * scroll to the element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
	}
	
	/**
	 * input value to the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param value inputed into element
	 */
	public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, xpathLocator));
	}

	/**
	 * remove attribute of the element in DOM
	 * @param driver
	 * @param xpathLocator of the element
	 * @param attributeRemove attribute tag name of the element that user want to remove
	 */
	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
	}

	/**
	 * check that all jquery and javascript in the page are loaded fully
	 * @param driver
	 * @return true/false
	 */
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	/**
	 * get the validation message of the element (not in DOM)
	 * @param driver
	 * @param xpathLocator of the element
	 * @return validation message text
	 */
	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
	}

	/**
	 * check that the image is loaded fully
	 * @param driver
	 * @param locator of the image
	 * @return true/false
	 */
	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * wait for the element to display
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	/**
	 * wait for all elements to be display
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
		
	/**
	 * wait for the element to be clickable
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
		
	/**
	 * wait for the element to be invisible/no long display
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	/**
	 * wait for all elements to be invisible/no long display
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	public void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, xpathLocator)));
	}
	
	/**
	 * wait for all elements to be presence
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	public void waitForAllElementsToBePresenced(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	/**
	 * Create a random number
	 */	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	/**
	 * Hard wait the test
	 * @param timeoutInSecond waiting time in second
	 */	
	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
