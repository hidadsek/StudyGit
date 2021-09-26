package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Integration {
	
  @Test(groups = {"integration","regression"})
  public void TC_01() {
	  String studentName = "Lê Văn Nam";
	  int studentPrice= 1;
	  System.out.println("Run TC 01");
	  // Verify so sánh 2 giá trị bằng nhau (2 giá trị cùng kiểu dữ liệu)
	  Assert.assertEquals(studentName,"Lê Văn Nam");
	  Assert.assertNotEquals(studentName,10);
	  
	  // Verify 1 điều kiện trả về đúng - Boolean
	  Assert.assertTrue(studentName.contains("Lê"));
	  // Verify 1 điều kiện trả về sai - Boolean
	  Assert.assertFalse(studentName.contains("Nguyễn"));
	  
	  //Verify null
	  Assert.assertNotNull(studentPrice);
  }
  
  @Test(groups = {"integration"})
  public void TC_02() {
	  System.out.println("Run TC Integration 02");
  }
}
