package javaBasic;

public class Topic_02_And_Or {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean a = true;
		boolean b = false;
		boolean c = true;
		boolean d = false;
		// AND
		System.out.println("Phép tính AND");
		//true & true = true
		System.out.println(a&c);
		
		//true & false = false
		System.out.println(a&b);
		
		//false & true = false
		System.out.println(b&c);
		
		//false & false = false
		System.out.println(b&d);
		
		//OR
		System.out.println("Phép tính OR");
		//true | true = true
		System.out.println(a|c);
		
		//true & false = true
		System.out.println(a|b);
		
		//false & true = true
		System.out.println(b|c);
		
		//false & false = false
		System.out.println(b|d);
	}

}