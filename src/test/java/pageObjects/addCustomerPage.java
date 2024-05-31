package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class addCustomerPage {

	public WebDriver driver;

	public addCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='#']//p[contains(text(),'Customers')]")
	@CacheLookup
	WebElement customerButton;

	@FindBy(xpath = "//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
	@CacheLookup
	WebElement customerMenu;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	@CacheLookup
	WebElement addNewButton;

	@FindBy(xpath = "//input[@id='Email']")
	@CacheLookup
	WebElement emailField;

	@FindBy(xpath = "//input[@id='Password']")
	@CacheLookup
	WebElement passwordField;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement customerRoles;

	@FindBy(xpath = "//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement listItemAdministrators;

	@FindBy(xpath = "///li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement listItemRegistered;

	@FindBy(xpath = "//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement listItemGuests;

	@FindBy(xpath = "//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement listItemVendors;

	@FindBy(xpath = "//*[@id='VendorId']")
	@CacheLookup
	WebElement drpmgrOfVendor;

	@FindBy(id = "Gender_Male")
	@CacheLookup
	WebElement maleGender;

	@FindBy(id = "Gender_Female")
	@CacheLookup
	WebElement femaleGender;

	@FindBy(xpath = "//input[@id='FirstName']")
	@CacheLookup
	WebElement firstName;

	@FindBy(xpath = "//input[@id='LastName']")
	@CacheLookup
	WebElement lastName;

	@FindBy(xpath = "//input[@id='DateOfBirth']")
	@CacheLookup
	WebElement dateOfBirth;

	@FindBy(xpath = "//input[@id='Company']")
	@CacheLookup
	WebElement companyName;

	@FindBy(xpath = "//textarea[@id='AdminComment']")
	@CacheLookup
	WebElement adminContent;

	@FindBy(xpath = "//button[@name='save']")
	@CacheLookup
	WebElement saveButton;

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void clickOnCustomersMenu() {
		customerButton.click();
	}

	public void clickOnCustomersMenuItem() {
		customerMenu.click();
	}

	public void clickOnAddnew() {
		addNewButton.click();
	}

	public void setEmail(String email) {
		emailField.sendKeys(email);
	}

	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void setCustomerRoles(String role) throws InterruptedException {
		/*if (!role.equals("Vendors")) // If role is vendors should not delete Register as per req.
		{
			driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		}*/

		customerRoles.click();

		Thread.sleep(3000);

		if (role.equals("Administrators")) {
			listItemAdministrators.click();
		} else if (role.equals("Guests")) {
			listItemGuests.click();
		} else if (role.equals("Registered")) {
			listItemRegistered.click();
		} else if (role.equals("Vendors")) {
			listItemVendors.click();
		} else {
			listItemGuests.click();
		}

		// listitem.click();
		 Thread.sleep(3000);

		// JavascriptExecutor js = (JavascriptExecutor)ldriver;
		// js.executeScript("arguments[0].click();", listitem);

	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(drpmgrOfVendor);
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			maleGender.click();
		}
		else if(gender.equals("Female"))
		{
			femaleGender.click();
		}
		else
		{
			maleGender.click();//Default
		}	
	}
	
	public void setFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		lastName.sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		dateOfBirth.sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		companyName.sendKeys(comname);
	}
	
	public void setAdminContent(String content)
	{
		adminContent.sendKeys(content);
	}
	
	public void clickOnSave()
	{
		saveButton.click();
	}
}
