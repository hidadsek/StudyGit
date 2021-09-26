package testNG;

import java.util.ArrayList;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listenerConfig.TestListener;

//@Listeners(TestListener.class)
public class Topic_14_TestListener {
	ArrayList<String> test= new ArrayList<String>();
	
	  @Test
	  public void Account_01_Create_New_Account() {
		  System.out.println("TC 1");
		  test.add("Thuc");
		  System.out.println(test.size());
		  test.clear();
	  }
	  
	  @Test 
	  public void Account_02_View_Account() {
		  System.out.println("TC 2");
		  System.out.println(test.get(0));
	  }
	  
	  @Test (dependsOnMethods = "Account_02_View_Account")
	  public void Account_03_Edit_Account() {
		  System.out.println("TC 3");
		  test.set(0, "Nguyen");
	  }
	  
	  @Test
	  public void Account_04_Delete_Account() {
		 System.out.println("TC 4");
		 test.clear();
	  }
}
