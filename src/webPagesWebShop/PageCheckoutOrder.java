package webPagesWebShop;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCheckoutOrder {

	private WebDriver driver;

	private static final String PAGE_URL_STEP_ONE = "https://www.saucedemo.com/checkout-step-one.html";
	private static final String PAGE_URL_STEP_TWO = "https://www.saucedemo.com/checkout-step-two.html";
	private static final String PAGE_URL_CHECKOUT_COMPLETE = "https://www.saucedemo.com/checkout-complete.html";

	@FindBy(id = "first-name")
	private WebElement weFirstName;

	@FindBy(id = "last-name")
	private WebElement weLastName;

	@FindBy(id = "postal-code")
	private WebElement wePostalCode;

	@FindBy(xpath = "//h3[.='Error: First Name is required']")
	private WebElement weFirstNameErrorMessage;

	@FindBy(xpath = "//h3[.='Error: Last Name is required']")
	private WebElement weLastNameErrorMessage;

	@FindBy(xpath = "//h3[.='Error: Postal Code is required']")
	private WebElement wePostalCodeErrorMessage;

	@FindBy(id = "continue")
	private WebElement weButtonContinue;

	@FindBy(id = "finish")
	private WebElement weButtonFinish;

	@FindBy(id = "back-to-products")
	private WebElement weButtonBackHome;

	public PageCheckoutOrder(WebDriver driver) {
		this.driver = driver;
		driver.get(PAGE_URL_STEP_ONE);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	// Method for open page Checkout/first step

	public void openPageCheckoutOne() {
		driver.get(PAGE_URL_STEP_ONE);
	}
	// Method for open page Checkout/ second step and finishing order on the third page

	public void finishingOrder() {
		driver.get(PAGE_URL_STEP_TWO);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", weButtonFinish);
		weButtonFinish.click();
		driver.get(PAGE_URL_CHECKOUT_COMPLETE);
		weButtonBackHome.click();
	}

	// Method for input First name/ checkout second step
	public void inputStringInFirstname(String firstname) {
		weFirstName.clear();
		weFirstName.sendKeys(firstname);
	}

	// Method for input Last name/ checkout second step
	public void inputStringInLastName(String lastname) {
		weLastName.clear();
		weLastName.sendKeys(lastname);
	}

	// Method for input Postal Code/ checkout second step
	public void inputPostalCode(String postalcode) {
		wePostalCode.clear();
		wePostalCode.sendKeys(postalcode);
	}

	// Method click to proceed to checkout/page two

	public void clickOnButtonCheckoutContinue() {
		weButtonContinue.click();
	}
   
	// Methods for getting Error Messages
	
	public String getFirstNameErrorMessage() {
		return weFirstNameErrorMessage.getText();
	}

	public String getLastNameErrorMessage() {
		return weLastNameErrorMessage.getText();
	}

	public String getPostalCodeErrorMessage() {
		return wePostalCodeErrorMessage.getText();

	}

}
