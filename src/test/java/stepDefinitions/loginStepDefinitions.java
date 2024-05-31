package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.addCustomerPage;
import pageObjects.loginPage;
import pageObjects.searchCustomerPage;

public class loginStepDefinitions extends baseClass {
	
	@Before
    public void setup() throws IOException
    {
        //Loading Config.properties file steps
        configProp=new Properties();
        FileInputStream configPropfile = new FileInputStream(".//src//test//resources//config.properties");
        configProp.load(configPropfile);
        //Loading Config.properties file is done


        String br=configProp.getProperty("browser");

        if (br.equals("firefox")) {
        }
        else if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }
        else if (br.equals("ie")) {
        }

    }

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		baseClass.getLogger().info("*********Launching browser***************");
		lp = new loginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		baseClass.getLogger().info("*********Opening URL***************");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		baseClass.getLogger().info("*********Providing User Info***************");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		lp.clickLogin();
		Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
		baseClass.getLogger().info("*********Login Validation Start***************");
		if (driver.getPageSource().contains("Login was unsuccessful")) {
			baseClass.getLogger().info("*********Login Failed***************");
			driver.close();
			Assert.assertTrue(false);
		} else {
			baseClass.getLogger().info("*********Login Successful***************");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);
	}

	@When("User click on Log out button")
	public void user_click_on_log_out_button() throws InterruptedException {
		baseClass.getLogger().info("*********Logout from Application***************");
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		baseClass.getLogger().info("*********Close Application***************");
		driver.quit();
	}

	// Adding new customer steps definitions
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		baseClass.getLogger().info("*********Adding new Customer***************");
		addCust = new addCustomerPage(driver);
		baseClass.getLogger().info("*********Dashboard display Validation***************");
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickOnAddnew();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		baseClass.getLogger().info("*********Providing Customer details***************");
		String email = randomestring() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		// addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Pratik");
		addCust.setLastName("Jaiswal");
		addCust.setDob("1/04/1999"); // Format: D/MM/YYY
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing");
	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		baseClass.getLogger().info("*********Add Customer Validation***************");
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}

	// Searching customer by email ID
	@When("Enter customer EMail")
	public void enter_customer_EMail() {
		baseClass.getLogger().info("*********Search Customer by Email***************");
		searchCust = new searchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		baseClass.getLogger().info("*********Search Customer by Email Validation***************");
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

	// steps for searching a customer by Name................
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		baseClass.getLogger().info("*********Search Customer by Name***************");
		searchCust = new searchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		baseClass.getLogger().info("*********Search Customer by Name Validation***************");
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

}
