package javaBasic;

import java.util.ArrayList;
import java.util.Scanner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_If_Else {
	Scanner scanner;
	
	@BeforeClass
	public void beforeClass() {
		scanner = new Scanner(System.in);
	}
	
	//@Test
	public void TC_01() {		
		System.out.println("Nhập số n:");
		int n = scanner.nextInt();
		String result = (n%2 == 0)?"n là số chẵn":"n là số lẻ";
		System.out.println(result);
	}
	
	//@Test
	public void TC_02() {
		System.out.println("Nhập số a:");
		int a = scanner.nextInt();
		System.out.println("Nhập số b:");
		int b = scanner.nextInt();
		if (a>b) {
			System.out.println("a lớn hơn b");
		}
		else if (a<b) {
			System.out.println("a bé hơn b");
		}
		else { System.out.println("a bằng b");}
	}
	
	//@Test
	public void TC_03() {
		scanner.nextLine();
		System.out.println("Nhập tên a:");
		String a = scanner.nextLine();
		System.out.println("Nhập tên b:");
		String b = scanner.nextLine();
		String result = (a.equals(b))?"2 người cùng tên":"2 người khác tên";
		System.out.println(result);
	}
	
	//@Test
	public void TC_04() {
		String message = "Y";
		int a =0;
		ArrayList<Integer> s = new ArrayList<>();
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập số a:");
			a = scanner.nextInt();
			s.add(a);
			scanner.nextLine();
			System.out.println("Muốn nhập tiếp:");
			message = scanner.nextLine();
		}
		for(int i:s) {
			System.out.print(i+";");
			a = (i>a)?i:a;
		}
		System.out.println();
		System.out.println(a);
	}
	
	//@Test
	public void TC_05() {		
		System.out.println("Nhập số n:");
		int n = scanner.nextInt();
		if((n>=10)&&(n<=100)) {
			System.out.println(n+" nằm trong khoảng từ [10] đến [100]");
		}
		else {
			System.out.println(n+" không nằm trong khoảng từ [10] đến [100]");
		}
	}
	
	//@Test
	public void TC_06() {
		String message = "Y";
		String mark= "không xếp loại";
		double a =0;
		ArrayList<Double> s = new ArrayList<>();
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập số a:");
			a = scanner.nextDouble();
			if(a>=0 && a<=10) {
				s.add(a);
			}
			else {System.out.println("Điểm nhập không đúng. Vui lòng nhập từ 0 đến 10");}
			scanner.nextLine();
			System.out.println("Muốn nhập tiếp:");
			message = scanner.nextLine();
		}
		for(double i:s) {
			if (i<5) {
				mark = "D";
			}
			else if (i>=5 && i<7.5) {
				mark = "C";
			}
			else if (i>=7.5 && i<8.5) {
				mark = "B";
			}
			else {
				mark = "A";
			}
			System.out.println("Học sinh có điểm "+i+" xếp loại: "+ mark);
		}
	}
	
	@Test
	public void TC_07() {
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
	
	static public void geNumberOfDateInMonth (int month) {
		if(month<8) {
			if (month == 2) {
				System.out.println("Tháng "+month+" có 28 ngày");
			}
			else if (month%2 == 1) {
				System.out.println("Tháng "+month+" có 31 ngày");
			}
			else {
				System.out.println("Tháng "+month+" có 30 ngày");
			}
		}
		if (month>=8 && month<13) {
			if (month%2 == 0) {
				System.out.println("Tháng "+month+" có 31 ngày");
			}
			else {
				System.out.println("Tháng "+month+" có 30 ngày");
			}
		}
		else {
			System.out.println("Tháng "+month+" không tồn tại");
		}
	}
}
