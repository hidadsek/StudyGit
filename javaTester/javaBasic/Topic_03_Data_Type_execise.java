package javaBasic;

import org.testng.annotations.Test;

public class Topic_03_Data_Type_execise {

	@Test
	public void TC_01() {
		int a =6, b =2;
		System.out.println("Số nhập vào a= "+ a+", b= "+b);
		System.out.println("Tổng = "+ (a+b));
		System.out.println("Hiệu = "+ (a-b));
		System.out.println("Tích = "+ (a*b));
		System.out.println("Thương = "+ (a/b));
	}
	
	@Test
	public void TC_02() {
		double length = 7.5, width = 3.8;
		System.out.println("Chiều dài= "+ length+", Chiều rộng= "+width);
		System.out.println("Diện tích =" + (length*width));
	}
	
	@Test
	public void TC_03() {
		String name = "Automation Testing";
		System.out.println("Hello " + name);
	}
}
