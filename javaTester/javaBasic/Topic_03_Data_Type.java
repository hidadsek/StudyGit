package javaBasic;

import java.util.Scanner;

public class Topic_03_Data_Type {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập số a");
		float a = scanner.nextFloat();
		System.out.println("Nhập số b");
		float b = scanner.nextFloat();
		
		System.out.println("a + b = "+ (a+b));
		System.out.println("a - b = "+ (a-b));
		System.out.println("a * b = "+ (a*b));
		System.out.println("a / b = "+ (a/b));
		a = 7.5F;
		b = 3.8F;		
		System.out.println("Area ="+ (a*b));
		String name = "Automation Testing";
		System.out.println("Hello "+name);
		scanner.close();
	}
}
