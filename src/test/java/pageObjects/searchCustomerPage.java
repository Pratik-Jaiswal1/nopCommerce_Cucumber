package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class searchCustomerPage {

	public WebDriver driver;

	public searchCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(id = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;

	@FindBy(id = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(id = "search-customers")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(xpath = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(xpath = "//table[@id='customers-grid']")
	WebElement table;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	
	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setFirstName(String fname) {

		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {

		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}

	public void clickSearch() {
		btnSearch.click();

	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
					.getText();
			System.out.println(emailid);
			if (emailid.equals(email)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String Name) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]"))
					.getText();

			if (Name.equals(name)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
