package basic;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import webdriver.Topic_00_Template;

public class Topic_01_DataType {
	public static void main(String[] args) {
		// primitive data type
		
		// char
		char a = 'u';		
		
		//Number
		// byte: -128 --> 127 (1 byte)
		byte b = 127;
		// short: -32768 --> 32767 (2 byte)
		short s = 32767;
		// int: -2147483648 --> 2147483647 (4 byte)
		int i = 2147483647;
		// long (8 byte)
		long l = 2147483647;		
		// Has decimals
		// float
		float f = 30.5F;
		// double
		double d = 300.5;	
		
		// Logic
		// boolean: true/false
		boolean genderStatus = true;
		genderStatus = false;
		
		// reference data type
		// String
		String address = "Ha Noi";
		
		// Array
		String[] studentName = {"An","Hoa","Thuong"};
		int[] studentEbook = {5,10,15};

		// Class
		Topic_00_Template template = null;	
		WebDriver driver = new FirefoxDriver();
		// Abstract
		
		// Inteface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Object
		
		
		// Collection
		
	}

}
