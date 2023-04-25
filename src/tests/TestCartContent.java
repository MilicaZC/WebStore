package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import webPageLogin.PageLogin;
import webPagesWebShop.PageCart;
import webPagesWebShop.PageItemsList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestCartContent {

	private static FirefoxDriver driver;
	private static PageItemsList pageItemsList;
	private static PageLogin pageLogin;
	private static PageCart pageCart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.geckodriver", "/C:/Users/pc/Desktop/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		pageLogin = new PageLogin(driver);
		pageItemsList = new PageItemsList(driver);
		pageCart = new PageCart(driver);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		pageLogin.inputStringInUsername("standard_user");
		pageLogin.inputStringInPassword("secret_sauce");
		pageLogin.clickOnLoginButton();
		
		pageItemsList.addItemToCart("Sauce Labs Backpack");
		pageItemsList.enterItemAndAddToCart("Sauce Labs Bolt T-Shirt");

		pageCart.openCart();
	}

	@After
	public void tearDown() throws Exception {
		
		driver.close();
	}

	
	// verifying that the corrected items are displayed, remove the first item and proceed to Checkout
	
	@Test
	
	public void test01VerifyingCartContent() {
		
		pageCart.verifyCartContent();
		pageCart.removeItemFromCart();
		pageCart.verifyCartContent();
		
		pageCart.clickOnButtonCheckout();
	}
	
}
