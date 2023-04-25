package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import webPageLogin.PageLogin;
import webPagesWebShop.PageCart;
import webPagesWebShop.PageCheckoutOrder;
import webPagesWebShop.PageItemsList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestCheckoutOrder {

	private static FirefoxDriver driver;
	private static PageItemsList pageItemsList;
	private static PageLogin pageLogin;
	private static PageCart pageCart;
	private static PageCheckoutOrder pageCheckoutOrder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.setProperty("webdriver.geckodriver", "/C:/Users/pc/Desktop/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		pageLogin = new PageLogin(driver);
		pageItemsList = new PageItemsList(driver);
		pageCart = new PageCart(driver);
		pageCheckoutOrder = new PageCheckoutOrder(driver);

		pageLogin.inputStringInUsername("standard_user");
		pageLogin.inputStringInPassword("secret_sauce");
		pageLogin.clickOnLoginButton();

		pageItemsList.addItemToCart("Sauce Labs Backpack");
		pageItemsList.enterItemAndAddToCart("Sauce Labs Bolt T-Shirt");

		pageCart.openCart();
		pageCart.removeItemFromCart();
		pageCheckoutOrder.openPageCheckoutOne();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		 driver.close();
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test01checkoutWithEmptyFields() {

		pageCheckoutOrder.inputStringInFirstname("");
		pageCheckoutOrder.inputStringInLastName("");
		pageCheckoutOrder.inputPostalCode("");

		pageCheckoutOrder.clickOnButtonCheckoutContinue();

		assertEquals("Error: First Name, Last Name and Postal Code are required", pageCheckoutOrder.getFirstNameErrorMessage());

	}

	@Test
	public void test02checkoutWithValidNameEmptyLastNameEmptyZip() {

		pageCheckoutOrder.inputStringInFirstname("Milica");
		pageCheckoutOrder.inputStringInLastName("");
		pageCheckoutOrder.inputPostalCode("");

		pageCheckoutOrder.clickOnButtonCheckoutContinue();

		assertEquals("Error: Last Name and Postal Code are required", pageCheckoutOrder.getLastNameErrorMessage());
	}

	@Test
	public void test03checkoutWithValidNameValidLastNameEmptyZip() {

		pageCheckoutOrder.inputStringInFirstname("Milica");
		pageCheckoutOrder.inputStringInLastName("Cirkovic");
		pageCheckoutOrder.inputPostalCode("");

		pageCheckoutOrder.clickOnButtonCheckoutContinue();

		assertEquals("Error: Postal Code is required", pageCheckoutOrder.getPostalCodeErrorMessage());
	}

	@Test
	public void test04checkoutWithValidFields() {

		pageCheckoutOrder.inputStringInFirstname("Milica");
		pageCheckoutOrder.inputStringInLastName("Cirkovic");
		pageCheckoutOrder.inputPostalCode("11070");

		pageCheckoutOrder.clickOnButtonCheckoutContinue();

		pageCheckoutOrder.finishingOrder();

	}

}
