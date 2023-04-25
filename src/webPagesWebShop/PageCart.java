package webPagesWebShop;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCart {

	private WebDriver driver;

	private static final String PAGE_URL = "https://www.saucedemo.com/cart.html";

	public PageCart(WebDriver driver) {
		this.driver = driver;
		driver.get(PAGE_URL);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	public void openCart() {
		driver.get(PAGE_URL);
	}
	// Method for checking number of Items in cart

	public void verifyCartContent() {

		int itemsNumber = driver.findElements(By.className("cart_item")).size();

		System.out.println("The number of items in cart is: " + itemsNumber);

	}
	// Method for removing item from cart

	public void removeItemFromCart() {

		WebElement weButtonRemoveItem = driver.findElement(By.xpath("//div[@class ='cart_item'][1]//button"));
		weButtonRemoveItem.click();

	}

	public void clickOnButtonCheckout() {

		WebElement weCheckout = driver.findElement(By.xpath("//button[@id = 'checkout']"));
		weCheckout.click();

	}

}
