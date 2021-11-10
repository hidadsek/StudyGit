package pageUI;

public class AddressPageUI {
	public String addressLinkBy = "//ul[@class='list']//a[text()='Addresses']";
	public String addButtonBy = "//div[@class='add-button']/button";
	public String firstNameTextboxBy = "//input[@id='Address_FirstName']";
	public String lastNameTextboxBy = "//input[@id='Address_LastName']";
	public String emailTextboxBy = "//input[@id='Address_Email']";
	public String companyTextboxBy = "//input[@id='Address_Company']";
	
	public String countryDropdownListBy = "//select[@id='Address_CountryId']";
	public String stateDropdownListBy = "//select[@id='Address_StateProvinceId']";
	
	public String cityTextboxBy = "//input[@id='Address_City']";
	public String address1TextboxBy = "//input[@id='Address_Address1']";
	public String address2TextboxBy = "//input[@id='Address_Address2']";
	public String zipTextboxBy = "//input[@id='Address_ZipPostalCode']";
	public String phoneTextboxBy = "//input[@id='Address_PhoneNumber']";
	public String faxTextboxBy = "//input[@id='Address_FaxNumber']";
	public String saveButtonBy = "//button[contains(@class,'save-address-button')]";
}
