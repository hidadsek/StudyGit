package com.datatable;

public class DynamicTableUI {
	public static final String HEADER_NAME_INDEX = "//th[text()='%s']/preceding-sibling::th"; 
	public static final String TEXTBOX_BY_COLUMN_ROW_INDEX = "//tr[%s]/td[%s]/input"; 
	public static final String ICON_BY_ROW_INDEX = "//tr[%s]//td[contains(@id,'tblAppendGrid_$rowButton')]/div[@class='field has-addons']//button[@title = '%s']"; 

}
