package assignment2;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import base.SeleniumTestCaseBase;

public class SeleniumTestCase2NopCommerce extends SeleniumTestCaseBase  {
	private static final Logger LOG 				= Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());
    private final static String WISHLIST_URL		= "/wishlist";
    private final static String SHOPPING_CART_URL	= "/cart";
    private final static String ITEM_TO_WISHLIST	= "Fahrenheit 451";

    @Test
    public void TestCase() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    	//Check if wish list is empty
        driver.get(NOP_COMMERCE_URL + WISHLIST_URL);       
    	if (driver.findElements(By.className("no-data")).size() != 0)
        	LOG.info("Wish list is empty");
        else {
        	LOG.info("Wish list is not empty");	
        	Assert.assertTrue(false);
        }
    	
        //Add item to wish list
        driver.findElement(By.id("small-searchterms")).sendKeys(ITEM_TO_WISHLIST);       
        driver.findElement(By.className("search-box-button")).click();
        driver.findElement(By.className("add-to-wishlist-button")).click();
             
        //Check if item was added to wish list
        driver.get(NOP_COMMERCE_URL + WISHLIST_URL);  
    	if (driver.findElements(By.xpath(".//a[@href='/fahrenheit-451-by-ray-bradbury']")).size() != 0)
    		LOG.info("Item was added to wish list successful");
        else {
        	LOG.info("Item was not added to wish list");
        	Assert.assertTrue(false);
        }
    	
    	//Check if shopping cart is empty
    	driver.get(NOP_COMMERCE_URL + SHOPPING_CART_URL);
    	if (driver.findElements( By.className("no-data")).size() != 0)
        	LOG.info("Shopping cart is empty");
        else {
        	LOG.info("Shopping cart is not empty");	
        	Assert.assertTrue(false);
        }
    	
    	//Add item to shopping cart
    	driver.get(NOP_COMMERCE_URL + WISHLIST_URL);
    	driver.findElement(By.xpath(".//td[@class='add-to-cart']/input[@type='checkbox']")).click();
    	driver.findElement(By.className("wishlist-add-to-cart-button")).click();
    	
    	//Check if item was added to shopping cart
    	driver.get(NOP_COMMERCE_URL + SHOPPING_CART_URL);
    	if (driver.findElements(By.xpath(".//a[@href='/fahrenheit-451-by-ray-bradbury']")).size() != 0)
        	LOG.info("Item was added to shopping cart successful");
        else {
        	LOG.info("Item was not added to shopping cart");	
        	Assert.assertTrue(false);
        }
    	
    	driver.findElement(By.className("continue-shopping-button")).click();
    	LOG.info("Test case 2 completed successfully");
    }
}
