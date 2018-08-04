package assignment1;
import base.SeleniumTestCaseBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Arrays;
import java.util.logging.Logger;


public class SeleniumTestCase2NopCommerce extends SeleniumTestCaseBase {

    private static final Logger LOG = Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());

    private final static int CATEGORY_NAV_WAIT_TIME    = 3000;
    private final static String TOGGLE_BUTTON_CLASS    = "menu-toggle";
    private final static String LOGO_SELECTOR          = ".header-logo a";
    private final static String[] CATEGORIES_LINK_TEXT = {
            "Computers",
            "Electronics",
            "Apparel",
            "Digital downloads",
            "Books",
            "Jewelry",
            "Gift Cards"
    };

    /**
     * Abra el navegador (Chrome)
     *
     * Conéctese al sitio web http://demo.nopcommerce.com
     * Ingrese a las categorías Computers, Electronics, Apparel, Digital downloads, books,
     * Jewelry and Gift Cards.
     * En cada caso, espere 3 segundos antes de ir a la siguiente categoría.
     * Vuelva a la página principal haciendo click en la imagen de la esquina superior izquierda
     * (nopCommerce).
     * Despliegue un mensaje indicando si el caso se ejecutó correctamente.
     */
    @Test
    public void TestCase() {
        Arrays.stream(CATEGORIES_LINK_TEXT)
                .forEach(this::navegateToCategory);

        waitExact(CATEGORY_NAV_WAIT_TIME);
        WebElement logo = getDriver()
                .findElement(By.cssSelector(LOGO_SELECTOR));
        logo.click();

        LOG.info("Test case 2 completed successfully");
    }

    private void navegateToCategory(final String category) {

        waitExact(CATEGORY_NAV_WAIT_TIME);
        WebElement toggle = getDriver()
                .findElement(By.className(TOGGLE_BUTTON_CLASS));

        boolean isMovile = toggle.isDisplayed();
        if(isMovile) {
            toggle.click();
        }
        WebElement link = wait(isMovile ? 1 : 0)
                .until(ExpectedConditions
                        .elementToBeClickable(By.linkText(category)));
        link.click();
    }
}