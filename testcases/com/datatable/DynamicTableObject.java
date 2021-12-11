package com.datatable;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DynamicTableObject extends BasePage {

	private WebDriver driver;
	
	public DynamicTableObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputTextboxByRowNumber(String headerName, String rowIndex, String value) {
		int columnIndex = getElementSize(driver, DynamicTableUI.HEADER_NAME_INDEX, headerName)+1;
		waitForElementVisible(driver, DynamicTableUI.TEXTBOX_BY_COLUMN_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
		inputIntoElement(driver, DynamicTableUI.TEXTBOX_BY_COLUMN_ROW_INDEX, value, rowIndex,String.valueOf(columnIndex));
	}

	public void clickIconByRowNumber(String rowIndex, String iconIndex) {
		waitForElementClickable(driver, DynamicTableUI.ICON_BY_ROW_INDEX,rowIndex,iconIndex);
		clickElement(driver, DynamicTableUI.ICON_BY_ROW_INDEX, rowIndex,iconIndex);
	}

}
