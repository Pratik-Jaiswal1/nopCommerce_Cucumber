package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	public WebDriver driver;

	public loginPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Email")
	@CacheLookup
	WebElement emailField;

	@FindBy(id = "Password")
	@CacheLookup
	WebElement passwordField;

	@FindBy(xpath = "/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	@CacheLookup
	WebElement loginButton;

	@FindBy(linkText = "Logout")
	@CacheLookup
	WebElement logoutButton;

	public void setUserName(String uname) {
		emailField.clear();
		emailField.sendKeys(uname);

	}

	public void setPassword(String pwd) {
		passwordField.clear();
		passwordField.sendKeys(pwd);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void clickLogout() {
		logoutButton.click();
	}

}
