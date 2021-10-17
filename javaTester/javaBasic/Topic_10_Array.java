package javaBasic;

import java.util.Scanner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Array {
	Scanner scanner;
	
	@BeforeClass
	public void beforeClass() {
		scanner = new Scanner(System.in);
	}	
	
	//@Test
	public void TC_01(){
		int max = 0;
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		int[] array = new int[n];		
		inputNumberIntoArray(array,n);		
		for(int i : array) {
			if (i>max) {
				max = i;
			}
		}
		System.out.println("Giá trị lớn nhất trong mảng là: "+ max);
	}
	
	//@Test
	public void TC_02(){
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		int[] array = new int[n];		
		inputNumberIntoArray(array,n);
		System.out.println("Tổng giá trị đầu và cuối mảng là: "+ (array[0]+array[n-1]));
	}
	
	//@Test
	public void TC_03(){
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		int[] array = new int[n];		
		inputNumberIntoArray(array,n);
		System.out.print("Các số chẵn trong mảng là: ");
		for(int i: array) {
			if(i%2==0) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}
	
	//@Test
	public void TC_04(){
		int sum = 0;
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		int[] array = new int[n];		
		inputNumberIntoArray(array,n);
		System.out.print("Tổng số lẻ trong mảng lớn hơn 0 là: ");
		for(int i: array) {
			if(i%2==1 && i>0) {
				if (sum == 0) {
					System.out.print(i);
				}
				else {
					System.out.print(" + "+i);
				}
				sum+=i;
			}
		}
		System.out.print(" = "+sum);
		System.out.println();
	}
	
	//@Test
	public void TC_05(){
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		int[] array = new int[n];		
		inputNumberIntoArray(array,n);
		System.out.print("Các số trong mảng có giá trị trong khoảng 0 tới 10 là: ");
		for(int i: array) {
			if(i<=10 && i>=0) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}
	
	//@Test
	public void TC_06(){
		int sum = 0;
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		int[] array = new int[n];		
		inputNumberIntoArray(array,n);		
		for(int i: array) {
			sum+=i;
		}
		double average = (double)sum/n;
		System.out.println("Tổng giá trị trong mảng là: "+sum);
		System.out.println("Trung bình cộng trong mảng là: "+average);
	}
	
	@Test
	public void TC_07() {
		System.out.println("Nhập số phần tử của mảng:");
		int n = scanner.nextInt();
		scanner.nextLine();
		Topic_10_Student_Class[] studentList = new Topic_10_Student_Class[n];
		for (Topic_10_Student_Class student:studentList) {
			student = new Topic_10_Student_Class();
			System.out.println("Nhập student ID:");
			student.setID(scanner.nextLine());
			System.out.println("Nhập tên học sinh:");
			student.setName(scanner.nextLine());
			System.out.println("Nhập tuổi học sinh:");
			student.setAge(scanner.nextInt());
			System.out.println("Nhập điểm học sinh:");
			student.setScore(scanner.nextInt());
			scanner.nextLine();
			student.display();
		}
	}
	
	@AfterClass
	public void afterClass() {
		scanner.close();
	}
	
	public void inputNumberIntoArray(int[] array ,int n) {
		for (int i =0; i<n;i++) {
			System.out.println("Nhập giá trị vào mảng:");
			array[i]= scanner.nextInt();
		}
	}
}
