package javaBasic;

import java.util.ArrayList;
import java.util.Scanner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Switch_Case {
	Scanner scanner;
	
	@BeforeClass
	public void beforeClass() {
		scanner = new Scanner(System.in);
	}	
	
	//@Test
	public void TC_01() {
		System.out.println("Nhập số");
		int n = scanner.nextInt();
		convertNumberToEnglish(n);	
	}
	
	//@Test
	public void TC_02() {
		System.out.println("Nhập số a");
		int a = scanner.nextInt();
		System.out.println("Nhập số b");
		int b = scanner.nextInt();
		calculateSimpleEquation(a, b, "+");	
		calculateSimpleEquation(a, b, "-");	
		calculateSimpleEquation(a, b, "*");	
		calculateSimpleEquation(a, b, "/");	
		calculateSimpleEquation(a, b, "%");	
		calculateSimpleEquation(a, b, "!");	
	}
	
	@Test
	public void TC_03() {
		ArrayList<Integer> s = new ArrayList<>();
		for (int i =1; i<13;i++) {
			s.add(i);
		}
		for(int i:s) {
			geNumberOfDateInMonth(i);
		}
		
		System.out.println("Nhập tháng:");
		int month = scanner.nextInt();
		geNumberOfDateInMonth(month);
	}
	
	@AfterClass
	public void afterClass() {
		scanner.close();
	}
	
	public void convertNumberToEnglish(int number) {
		switch (number) {
			case 1:
				System.out.println("One");
				break;
			case 2:
				System.out.println("Two");
				break;
			case 3:
				System.out.println("Three");
				break;
			case 4:
				System.out.println("Four");
				break;
			case 5:
				System.out.println("Five");
				break;
			case 6:
				System.out.println("Six");
				break;
			case 7:
				System.out.println("Seven");
				break;
			case 8:
				System.out.println("Eight");
				break;
			case 9:
				System.out.println("Nine");	
				break;
			case 10:
				System.out.println("Ten");
				break;
			default:
				System.out.println("Other numbers");
		}
	}
	
	public void calculateSimpleEquation(int a, int b, String ptinh) {
		switch (ptinh) {
			case "+":
				System.out.println("Kết quả: "+(a+b));
				break;
			case "-":
				System.out.println("Kết quả: "+(a-b));
				break;
			case "*":
				System.out.println("Kết quả: "+(a*b));
				break;
			case "/":
				System.out.println("Kết quả: "+(a/b));
				break;
			case "%":
				System.out.println("Kết quả: "+(a%b));
				break;
			default:
				System.out.println("Phép tính không tồn tại");
		}
	}
	
	public void geNumberOfDateInMonth(int month) {
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				System.out.println("Tháng "+month+" có 31 ngày");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("Tháng "+month+" có 30 ngày");
				break;
			case 2:
				System.out.println("Tháng "+month+" có 28 ngày");
				break;
			default:
				System.out.println("Tháng "+month+" không tồn tại");
		}
	}
}
