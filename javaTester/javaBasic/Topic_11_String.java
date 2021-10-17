package javaBasic;

import java.util.Scanner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_String {
	Scanner scanner;
	
	@BeforeClass
	public void beforeClass() {
		scanner = new Scanner(System.in);
	}	
	
	//@Test
	public void TC_01() {
		System.out.println("Input string");
		String s = scanner.nextLine();
		countStringUpperCase(s);
	}
	
	//@Test
	public void TC_02() {
		System.out.println("Input string");
		String s = scanner.nextLine();
		int count =0;
		for (char a : s.toCharArray()) {
			if((a=='a' || a == 'A')) {
				count++;
			}
		}
		System.out.println("Number of value a in string: "+count);
		System.out.println(s.contains("Testing"));
		System.out.println(s.startsWith("Automation"));
		System.out.println(s.endsWith("Online"));
		System.out.println(s.indexOf("Tutorials"));
		System.out.println(s.replace("Online","Offline"));
		System.out.println("Number of value in string: "+s.length());
	}
	
	@Test
	public void TC_03() {
		System.out.println("Input string");
		String s = scanner.nextLine();
		System.out.println(reverseString(s));
	}
	
	@AfterClass
	public void afterClass() {
		scanner.close();
	}
	
	public void countStringUpperCase(String str) {
		int count =0;
		for(int i =0; i<str.length();i++) {
			if (Character.isUpperCase(str.charAt(i))){
				count++;
			}
		}
		System.out.println("The number of uppercase character "+count);
	}
	
	public String reverseString(String str) {
		String reverse ="";
		for(int i =str.length()-1; i>=0;i--) {
			reverse+= str.charAt(i);
		}
		return reverse;
	}
}
