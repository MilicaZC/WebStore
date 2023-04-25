package webPageLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin {

	private WebDriver driver;
	private static final String PAGE_URL = "https://www.saucedemo.com/";

	@FindBy(id = "user-name")
	private WebElement weUsername;

	@FindBy(id = "password")
	private WebElement wePassword;

	@FindBy(id = "login-button")
	private WebElement weLoginButton;

	@FindBy(xpath = "//h3[.='Epic sadface: Username is required']")
	private WebElement weUsernameErrorMessage;

	@FindBy(xpath = "//h3[.='Epic sadface: Password is required']")
	private WebElement wePasswordErrorMessage;

	@FindBy(xpath = "//h3[.='Epic sadface: Sorry, this user has been locked out.']")
	private WebElement weLockedOutUserErrorMessage;

	@FindBy(xpath = "//h3[.='Epic sadface: Username and password do not match any user in this service']")
	private WebElement weInvalidUsernameErrorMessage;

	public PageLogin(WebDriver driver) {
		this.driver = driver;
		driver.get(PAGE_URL);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	//Method for opening Page for Login
	public void openPageSwagLabs() {
		driver.get(PAGE_URL);
	}

	//Method for input username in form
	public void inputStringInUsername(String username) {
		weUsername.clear();
		weUsername.sendKeys(username);
	}

	//Method for input password in form
	public void inputStringInPassword(String password) {
		wePassword.clear();
		wePassword.sendKeys(password);
	}

	//Submiting Login
	public void clickOnLoginButton() {
		weLoginButton.click();
	}

	// Username message error
	public String getUsernameErrorMessage() {
		return weUsernameErrorMessage.getText();
	}

	//Password message error
	public String getPasswordErrorMessage() {
		return wePasswordErrorMessage.getText();
	}

	// Locked out username message error 
	public String getLockedOutUserErrorMessage() {
		return weLockedOutUserErrorMessage.getText();

	}

	// Invalid Username messsage error
	public String getInvalidUsernameErrorMessage() {
		return weInvalidUsernameErrorMessage.getText();

	}

}
