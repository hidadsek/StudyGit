package pageUI;

public class CustomerInfoPageUI {
	public String customerInfoLinkBy = "//ul[@class='list']//a[text()='Customer info']";
	public String maleRadioButtonBy ="//input[@id='gender-male']";
	public String femaleRadioButtonBy = "//input[@id='gender-female']";
	public String firstNameTextboxBy = "//input[@id='FirstName']";
	public String lastNameTextboxBy = "//input[@id='LastName']";
	public String dayDropdownListBy = "//select[@name='DateOfBirthDay']";
	public String monthDropdownListBy = "//select[@name='DateOfBirthMonth']";
	public String yearDropdownListBy = "//select[@name='DateOfBirthYear']";
	public String emailTextboxBy = "//input[@id='Email']";
	public String companyTextboxBy = "//input[@id='Company']";
	public String newsletterCheckboxBy = "//input[@id='Newsletter']";
	public String saveButtonBy = "//button[@id='save-info-button']";
}
