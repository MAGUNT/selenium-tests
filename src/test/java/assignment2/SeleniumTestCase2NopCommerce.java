package assignment2;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import base.SeleniumTestCaseBase;

class SeleniumTestCase2NopCommerce extends SeleniumTestCaseBase  {
	private static final Logger LOG 				     = Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());
    private final static String ITEM_TO_WISHLIST	     = "Fahrenheit 451";
    private final static String CONTINUE_SHOPPING_BUTTON = "continue-shopping-button";
    /**
     * Vaya la página Wishlist
     *
     * Verifique que se despliega el mensaje “The wishlist is empty!”
     * Vaya al campo de búsqueda de artículos utilizando el id= small-searchterms.
     * Busque el libro Fahrenheit 451.
     *
     * Agregue el libro al Wishlist.
     * Vaya nuevamente al Wishlist y verifique que el libro ha sido incluido
     * Vaya a la opción Shopping cart
     * Verifique que se despliegue el mensaje “Your Shopping Cart is empty!”
     * Vaya a la opción Wishlist
     * Marque el libro Fahrenheit 451 en la opción Add to cart y haga click en el botón Add to
     * cart
     * Valide que el libro fue agregado al Shopping Cart
     * Haga click en la opción Continue shopping
     * Despliegue un mensaje indicando si el caso se ejecutó correctamente, indicando el
     * número de caso.
     */
    @Test
    void testCase() {
		checkWishListIsEmpty();

		searchProduct(ITEM_TO_WISHLIST);
		addToWishList(ITEM_TO_WISHLIST);
		matchesProduct(ITEM_TO_WISHLIST);

		checkShoppingCartIsEmpty();
    	goToWishList();
		addItemToCart();

		matchesProduct(ITEM_TO_WISHLIST);
    	waitToClickElement(By.className(CONTINUE_SHOPPING_BUTTON)).click();
    	LOG.info("Test case 2 completed successfully");
    }
}
