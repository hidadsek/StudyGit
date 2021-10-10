package javaBasic;

import java.util.Scanner;

public class Topic_04_Operator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập tên:");
		String name = scanner.nextLine();
		System.out.println("Nhập tuổi:");
		int age = scanner.nextInt();
		
		System.out.println("After 15 years, age of "+name+" will be "+ (age +15));
		System.out.println("Nhập a và b:");
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		System.out.println("Before swapping a = "+a+" and b= "+ b);
		int c = a;
		a = b;
		b = c;
		System.out.println("After swapping a = "+a+" and b= "+ b);
		
		Boolean IsTrue = (a>b)?true:false;
		System.out.println("Kết quả so sánh a > b là:"+IsTrue);
	}

}
