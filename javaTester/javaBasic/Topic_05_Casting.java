package javaBasic;

public class Topic_05_Casting {
	int x =5;
	int y = 10;
	public static void main(String[] args) {
		int a =15;
		long b = a;
		float c= b;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		c = 15.1F;
		int s = (int)c;
		System.out.println(s);
		Topic_05_Casting testA = new Topic_05_Casting();
		Topic_05_Casting testB = new Topic_05_Casting();
		testA.x = 15;
		testA.y = 30;
		System.out.println("x = "+testA.x+"; y = "+testA.y);
		System.out.println("x = "+testB.x+"; y = "+testB.y);
		testB = testA;
		System.out.println("x = "+testA.x+"; y = "+testA.y);
		System.out.println("x = "+testB.x+"; y = "+testB.y);
	}

}
