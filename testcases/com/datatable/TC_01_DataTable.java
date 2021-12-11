package com.datatable;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_01_DataTable {
	WebDriver driver;
	DataTableObject dataTable;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}
	
	@Test
	public void TC_01_Navigate_Page() {	
		dataTable = new DataTableObject(driver);
		dataTable.openBrowser(driver,"https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
		dataTable.openPageByNumber("15");
		assertTrue(dataTable.isPageActiveByNumber("15"));
	}
	
	@Test
	public void TC_02_Actions() {
		dataTable = new DataTableObject(driver);
		dataTable.openBrowser(driver,"https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
		String females = "384180";
		String country = "Afghanistan";
		String males = "407120";
		String total = "791300";
		dataTable.inputToHeaderTextboxByName("Country","Afghanistan");
		dataTable.clickToIconByCountry("Afghanistan","edit");
		dataTable.inputFemaleTextbox(females);
		dataTable.inputCountryTextbox(country);;
		dataTable.inputMaleTextbox(males);
		dataTable.inputTotalTextbox(total);
		dataTable.clickOkButton();
		assertTrue(dataTable.isRowValueDisplayed(females,country,males,total));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
