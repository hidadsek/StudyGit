package javaBasic;

import java.util.Scanner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_While_Do {

	Scanner scanner;
	
	@BeforeClass
	public void beforeClass() {
		scanner = new Scanner(System.in);
	}	
	
	//@Test
	public void TC_01(){
		int n =0;
		String message = "Y";
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập n:");
			n = scanner.nextInt();
			if(n<=100) {
				message = "N";
			}
		}
		
		while (n<=100) {
			if(n%2 == 0) {
				System.out.print(n+" ");
			}
			n++;
		}
		System.out.println();
	}
	
	//@Test
	public void TC_02(){
		int a =0, b =0;
		String message = "Y";
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập a:");
			a = scanner.nextInt();
			
			System.out.println("Nhập b:");
			b = scanner.nextInt();
			if(a<=b) {
				message = "N";
			}
		}
		
		while (a<b) {
			if(a%3 == 0 && a%5 == 0) {
				System.out.print(a+" ");
			}
			a++;
		}
		System.out.println();
	}
	
	//@Test
	public void TC_03(){
		int n =0, sum = 0;
		String message = "Y";
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập n:");
			n = scanner.nextInt();
			if(n>=0) {
				message = "N";
			}
		}
		
		while (n>0) {
			if(n == 1) {
				sum+=n;
				System.out.print(n+" ");
			}
			else if(n%2 == 1) {
				sum+=n;
				System.out.print(n+" + ");
			}
			n--;
		}
		System.out.print("= "+sum);
		System.out.println();
	}
	
	//@Test
	public void TC_04(){
		int a =0, b =0;
		String message = "Y";
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập a:");
			a = scanner.nextInt();
			
			System.out.println("Nhập b:");
			b = scanner.nextInt();
			if(a<=b) {
				message = "N";
			}
		}
		
		while (a<b) {
			if(a%3 == 0) {
				System.out.print(a+" ");
			}
			a++;
		}
		System.out.println();
	}
	
	//@Test
	public void TC_05(){
		int n =0;
		long multiple = 1;
		String message = "Y";
		while (message.equalsIgnoreCase("Y")) {
			System.out.println("Nhập n:");
			n = scanner.nextInt();
			if(n>=0) {
				message = "N";
			}
		}
		while (n>0) {
			multiple*=n;
			if(n==1) {
				System.out.print(n+" ");
			}
			else {
				System.out.print(n+" * ");
			}
			n--;
		}
		System.out.print("= "+multiple);
		System.out.println();
	}
	
	@Test
	public void TC_06(){
		int n =2;
		int sum = 0;
		while (n<=10) {			
			if(n==10) {
				System.out.print(n+" ");
				sum+=n;
			}
			else if (n%2 == 0) {
				System.out.print(n+" + ");
				sum+=n;
			}
			n++;
		}
		System.out.print("= "+sum);
		System.out.println();
	}
	
	@AfterClass
	public void afterClass() {
		scanner.close();
	}
}
