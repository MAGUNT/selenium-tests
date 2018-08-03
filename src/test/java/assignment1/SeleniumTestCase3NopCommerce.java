package assignment1;

import base.SeleniumTestCaseBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;

public class SeleniumTestCase3NopCommerce extends SeleniumTestCaseBase {

    //  private static String byId = "\"//div[@data-productid='37']/descendant::input[contains(@title, 'wishlist')]\"";
    private static final Logger LOG = Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());
    private static final String WISH_LIST_CLASS = "ico-wishlist";
    private static final String SEARCH_INPUT_ID = "small-searchterms";
    private static final String BOOK_SEARCH     = "Fahrenheit 451";
    private static final String WISH_LIST       = "wishlist";


    private static final String WISH_LIST_BUTTON = String.format(
                    "//a[contains(text(), '%s')]" +
                    "/parent::h2" +
                    "/parent::div" +
                    "/descendant::input[contains(@title, 'wishlist')]",
            BOOK_SEARCH);



    private static final String WISH_LIST_BOOK_NAME = String.format(
                    "//td" +
                    "/a[contains(text(), '%s')]",
            BOOK_SEARCH);


    /**
     *
     * Vaya la página Wishlist
     *
     * Verifique que se despliega el mensaje “The wishlist is empty!�?
     * Vaya al campo de búsqueda de artículos utilizando el id= small-searchterms.
     * Busque el libro Fahrenheit 451.
     * Agregue el libro al Wishlist.
     * Vaya nuevamente al Wishlist y verifique que el libro ha sido incluido
     * Despliegue un mensaje indicando si el caso se ejecutó correctamente.
     */
    @Test
    public void TestCase() {
        getDriver().get(NOP_COMMERCE_URL);
        waitToClickElement(By.className(WISH_LIST_CLASS)).click();

        WebElement searchInput = waitToFoundElement(By.id(SEARCH_INPUT_ID));
        searchInput.sendKeys(BOOK_SEARCH);
        searchInput.submit();

        waitToClickElement(By.xpath(WISH_LIST_BUTTON)).click();
        waitToClickElement(By.linkText(WISH_LIST)).click();
        waitToFoundElement(By.xpath(WISH_LIST_BOOK_NAME));
        LOG.info("Test case 3 completed successfully");
    }

}
