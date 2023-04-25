package webPagesWebShop;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageItemsList {

	private WebDriver driver;
	private static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement weCartBadge;

	@FindBy(xpath = "//div[@class='inventory_item']")
	private static List<WebElement> weWebShopItems;

	public PageItemsList(WebDriver driver) {
		this.driver = driver;
		driver.get(PAGE_URL);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	public void openPageItemsList() {
		driver.get(PAGE_URL);

	}

	/* Methods for adding items to cart. 
	 I made list of WebElements "weWebShopItems" (as a global variable), than have chosen with key words "title" which item to add to cart, and clicked button "Add to cart".
	 After that, I checked whether CartBedge Icon has been updated appropriately. */

	public void addItemToCart(String title) {

		WebElement weButtonAddToCart = driver.findElement(By.xpath("//div[text()='" + title+ "']//ancestor::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']//button"));

		/* Going trough the "for loop" and choose/click on item. Than, checking whether
		   the CartBadge is Updated: */

		for (int i = 0; i < weWebShopItems.size(); i++) {

			String itemName = weWebShopItems.get(i).getText();

			if (itemName.contains(title)) {

				weButtonAddToCart.click();

				WebElement weCartBadgeUpdated = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
				assertEquals(weCartBadgeUpdated.getText(), "1"); // ovo moze bolje

				break;

			}
		}

	}
	// Method for choosing item, click on it to see more information and click to add to the cart:

	public void enterItemAndAddToCart(String title) throws InterruptedException {

		WebElement weItemToShop = driver.findElement(By.xpath("//div[text()='" + title + "']"));

		for (int i = 0; i < weWebShopItems.size(); i++) {

			String itemName = weWebShopItems.get(i).getText();

			if (itemName.contains(title)) {

				weItemToShop.click();

				WebElement weButtonAddToCart = driver.findElement(By.xpath("//button[text() = 'Add to cart']"));

				weButtonAddToCart.click();

				WebElement weBackToProducts = driver.findElement(By.xpath("//button[@id='back-to-products']"));

				weBackToProducts.click();
				break;
			}

		}

	}

}
