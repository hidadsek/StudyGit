package com.datatable;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_02_DynamicDataTable {
	WebDriver driver;
	DynamicTableObject dynamicTable;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}
	
	//@Test
	public void TC_01_Input_Textbox_In_Table() {	
		dynamicTable = new DynamicTableObject(driver);
		dynamicTable.openBrowser(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		dynamicTable.inputTextboxByRowNumber("Company","3","Apple");
		dynamicTable.inputTextboxByRowNumber("Contact Person","1","Thuc Nguyen");
		dynamicTable.inputTextboxByRowNumber("Order Placed","2","10");
	}
	
	@Test
	public void TC_02_Click_Icon_At_Row() {	
		dynamicTable = new DynamicTableObject(driver);
		dynamicTable.openBrowser(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		dynamicTable.inputTextboxByRowNumber("Order Placed","2","10");
		dynamicTable.clickIconByRowNumber("2","Move Up");
	}
}
