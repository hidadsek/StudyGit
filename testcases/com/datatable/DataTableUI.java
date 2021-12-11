package com.datatable;

public class DataTableUI {
	public static final String PAGING_BY_NUMBER = "//a[@class='qgrd-pagination-page-link' and text() = '%s']"; 
	public static final String PAGING_BY_NUMBER_ACTIVE = "//a[@class='qgrd-pagination-page-link active' and text() = '%s']";
	public static final String HEADER_TEXTBOX_BY_NAME = "//div[@class='qgrd-header-text' and text()='%s']/..//following-sibling::input";
	public static final String ICON_BY_COUNTRY = "//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-%s-row-btn']";
	public static final String ICON_BY_MALES = "//td[@data-key='males' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-%s-row-btn']";
	public static final String ICON_BY_FEMALES = "//td[@data-key='females' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-%s-row-btn']";
	public static final String ICON_BY_TOTAL = "//td[@data-key='total' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-%s-row-btn']";
	public static final String ROW_VALUE = "//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
	
	public static final String FEMALES_TEXTBOX ="//input[@name='females']";
	public static final String COUNTRY_TEXTBOX ="//input[@name='country']";
	public static final String AGE_TEXTBOX ="//input[@name='age']";
	public static final String MALES_TEXTBOX ="//input[@name='males']";
	public static final String YEAR_TEXTBOX ="//input[@name='year']";
	public static final String TOTAL_TEXTBOX ="//input[@name='total']";
	public static final String OK_BUTTON ="//input[@value='OK']";
}