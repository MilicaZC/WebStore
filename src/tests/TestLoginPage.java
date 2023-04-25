package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import webPageLogin.PageLogin;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestLoginPage {

	private static FirefoxDriver driver;
	private static PageLogin pageLogin;

	@BeforeClass

	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.geckodriver", "/C:/Users/pc/Desktop/geckodriver.exe");

		// 1A. If you want to use Chrome browser and Windows 10 for test run,
		// just use the following line of code instead of above:
		// System.setProperty("webdriver.chrome.driver", "/C:/Users/pc/Desktop/chromedriver.exe");
		// 1B. If you want to use Chrome browser and Linux for test run, just
		// use the following line of code instead of above:
		// System.setProperty("webdriver.chrome.driver","/C:/Users/pc/Desktop/chromedriver");

		driver = new FirefoxDriver();
		// if you want to use Chrome browser for test run, use the following line of code instead of above:
		// driver = new ChromeDriver();
		driver.manage().window().maximize();
		pageLogin = new PageLogin(driver);

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

	// testing Web Shop/LOGIN PAGE with empty Username and empty Password

	@Test
	public void test01LoginWithEmptyFields() {

		pageLogin.inputStringInUsername("");
		pageLogin.inputStringInPassword("");
		pageLogin.clickOnLoginButton();

		assertEquals("Epic sadface: Username and password are required", pageLogin.getUsernameErrorMessage());

	}

	// testing Web Shop/LOGIN PAGE with valid Username: "standard_user" and empty Password

	@Test
	public void test02LoginWithValidUsernameEmptyPassword() {

		pageLogin.inputStringInUsername("standard_user");
		pageLogin.inputStringInPassword("");
		pageLogin.clickOnLoginButton();

		assertEquals("Epic sadface: Password is required", pageLogin.getPasswordErrorMessage());

	}

	// testing Web Shop/LOGIN PAGE with valid Username: "locked_out_user" and valid Password:"secret_sauce"

	@Test
	public void test03LoginWithLockedOutUsernameValidPassword() {

		pageLogin.inputStringInUsername("locked_out_user");
		pageLogin.inputStringInPassword("secret_sauce");
		pageLogin.clickOnLoginButton();

		assertEquals("Epic sadface: Sorry, this user has been locked out.", pageLogin.getLockedOutUserErrorMessage());

	}

	// testing Web Shop/LOGIN PAGE with invalid Username: "milica_cirkovic" and valid Password:"secret_sauce"

	@Test
	public void test04LoginWithInvalidUsernameValidPassword() {

		pageLogin.inputStringInUsername("milica_cirkovic");
		pageLogin.inputStringInPassword("secret_sauce");
		pageLogin.clickOnLoginButton();

		assertEquals("Epic sadface: Username and password do not match any user in this service",
				pageLogin.getInvalidUsernameErrorMessage());

	}

	// testing Web Shop/LOGIN PAGE with valid Username: "standard_user" and valid Password:"secret_sauce"
	
	@Test
	public void test05LoginWithValidUsernameValidPassword() {

		pageLogin.inputStringInUsername("standard_user");
		pageLogin.inputStringInPassword("secret_sauce");
		pageLogin.clickOnLoginButton();

		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

	}

}
