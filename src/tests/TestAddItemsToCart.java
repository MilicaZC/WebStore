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
import webPagesWebShop.PageItemsList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestAddItemsToCart {

	private static FirefoxDriver driver;
	private static PageItemsList pageItemsList;
	private static PageLogin pageLogin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.setProperty("webdriver.geckodriver", "/C:/Users/pc/Desktop/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		pageLogin = new PageLogin(driver);
		pageItemsList = new PageItemsList(driver);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		driver.close();
	}

	@Before
	public void setUp() throws Exception {

		pageLogin.inputStringInUsername("standard_user");
		pageLogin.inputStringInPassword("secret_sauce");
		pageLogin.clickOnLoginButton();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test01AddItemsToCart() throws InterruptedException {

		pageItemsList.addItemToCart("Sauce Labs Backpack");
		pageItemsList.enterItemAndAddToCart("Sauce Labs Bolt T-Shirt");

	}

}
