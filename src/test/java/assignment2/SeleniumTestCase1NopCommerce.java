package assignment2;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.SeleniumTestCaseBase;

import java.util.Arrays;
import java.util.logging.Logger;

public class SeleniumTestCase1NopCommerce extends SeleniumTestCaseBase {

    private static final Logger LOG = Logger.getLogger(SeleniumTestCase1NopCommerce.class.getName());

    private final static int CATEGORY_NAV_WAIT_TIME    = 3;
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
     * Abra el navegador (Chrome o Firefox)
     *
     * Conéctese al sitio web http://demo.nopcommerce.com
     * Ingrese a las categorías Computers, Electronics, Apparel, Digital downloads, Books,
     * Jewelry and Gift Cards.
     * En cada caso, espere a que la página se haya desplegado completamente antes de ir a
     * la siguiente categoría. No es válido esperar cantidades fijas de tiempo (por ejemplo: 3
     * segundos).
     * Vuelva a la página principal haciendo click en la imagen de la esquina superior izquierda
     * (nopCommerce).
     * Despliegue un mensaje indicando si el caso se ejecutó correctamente.
     */
    @Test
    void testCase() {
        Arrays.stream(CATEGORIES_LINK_TEXT)
                .forEach(this::navegateToCategory);

        WebElement logo = wait(CATEGORY_NAV_WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(By.cssSelector(LOGO_SELECTOR)));
        logo.click();

        LOG.info("Test case 1 completed successfully");
    }

    private void navegateToCategory(final String category) {

        WebElement toggle = wait(CATEGORY_NAV_WAIT_TIME)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.className(TOGGLE_BUTTON_CLASS)));
        boolean isMovile = toggle.isDisplayed();
        if(isMovile) {
            toggle.click();
        }
        WebElement link = wait(isMovile ? 1 : 0)
                .until(ExpectedConditions
                        .elementToBeClickable(By.linkText(category)));
        link.click();
    }

    public WebDriverWait wait(final int seconds) {
       return new WebDriverWait(driver, seconds);
    }
}
