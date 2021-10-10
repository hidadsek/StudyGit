package javaBasic;

import java.util.Scanner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Loop {
	Scanner scanner;
	
	@BeforeClass
	public void beforeClass() {
		scanner = new Scanner(System.in);
	}	
	
	//@Test
	public void TC_01() {
		System.out.println("Nhập n:");
		int n = scanner.nextInt();
		for(int i =1;i<=n;i++) {
			if (i ==n) {
				System.out.println(i);
			}
			else {
				System.out.print(i+" ");
			}
			
		}
	}
	
	//@Test
	public void TC_02() {
		int a =1, b=0;
		while (a>b) {
			System.out.println("Nhập a:");
			a = scanner.nextInt();
			System.out.println("Nhập b:");
			b = scanner.nextInt();
		}
		for(int i =a;i<=b;i++) {
			if (i ==b) {
				System.out.println(i);
			}
			else {
				System.out.print(i+" ");
			}		
		}
	}
	
	//@Test
	public void TC_03() {
		int sum = 0, n=0;
		while(n<1 || n>10) {
		System.out.println("Nhập n:");
		n = scanner.nextInt();
		}
		for(int i =1;i<=n;i++) {
			if (i%2 == 0) {
				sum +=i;
			}	
		}
		System.out.println("Tổng các số chẳn là:"+sum);
	}
	
	//@Test
	public void TC_04() {
		int a =1, b=0, sum =0;
		String result="";
		while (a>b) {
			System.out.println("Nhập a:");
			a = scanner.nextInt();
			System.out.println("Nhập b:");
			b = scanner.nextInt();
		}
		for(int i =a;i<=b;i++) {
			if (i==b) {
				result= result+" "+i;
			}
			else {
				result= result+" "+i+" +";
			}
			sum+=i;	
		}
		
		System.out.println("Tổng các số từ "+a+" đến "+b+"="+result+" = "+sum);
	}
	
	//@Test
	public void TC_05() {
		int sum = 0, n=0;
		String result="";
		System.out.println("Nhập n:");
		n = scanner.nextInt();
		for(int i =1;i<=n;i++) {
			if (i%2 == 1) {
				if (n-i < 2) {
					result= result+" "+i;
				}
				else {
					result= result+" "+i+" +";
				}
				sum+=i;
			}	
		}
		System.out.println("Tổng các số lẻ là:"+result+" = "+sum);
	}
	
	//@Test
	public void TC_06() {
		int a =1, b=0, sum =0;
		String result="";
		while (a>b) {
			System.out.println("Nhập a:");
			a = scanner.nextInt();
			System.out.println("Nhập b:");
			b = scanner.nextInt();
		}
		for(int i =a;i<=b;i++) {
			if (i%3==0) {
				if (b-i <3) {
					result= result+" "+i;
				}
				else {
					result= result+" "+i+" +";
				}
				sum+=i;	
			}
		}
		
		System.out.println("Tổng các số từ "+a+" đến "+b+"="+result+" = "+sum);
	}
	
	@Test
	public void TC_07() {
		int multiple = 1, n=0;
		String result="";
		System.out.println("Nhập n:");
		n = scanner.nextInt();
		for(int i =1;i<=n;i++) {
			if (i==n) {
				result= result+" "+i;
			}
			else {
				result= result+" "+i+" *";
			}
			multiple*=i;
		}
		System.out.println("Giai thừa của "+n+" là:"+result+" = "+multiple);
	}
	
	@AfterClass
	public void afterClass() {
		scanner.close();
	}
}
