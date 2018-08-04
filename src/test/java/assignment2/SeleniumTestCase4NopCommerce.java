package assignment2;

import base.SeleniumTestCaseBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

class SeleniumTestCase4NopCommerce extends SeleniumTestCaseBase {
    private static final Logger LOG = Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());
    private final static String COMPUTER_SECCION_TEXT  = "Computers";
    private final static String SOFTWARE_SECCION_TEXT  = "Software";
    private final static String LIST_ICON_TEXT         = "List";
    private final static String TOGGLE_BUTTON_CLASS    = "menu-toggle";
    private final static String FIRST_ITEM_TITLE       = ".item-grid .item-box:first-of-type .product-title a";
    private final static String SORT_SELECT_ID         = "products-orderby";

    private final static String FIRST_ELEMENT_EXPECTED_1 = "Sound Forge Pro 11 (recurring)";
    private final static String SORT_ORDER_TEXT_1        = "Price: Low to High";
    private final static String FIRST_ELEMENT_EXPECTED_2 = "Adobe Photoshop CS4";
    private final static String SORT_ORDER_TEXT_2        = "Price: High to Low";

    /**
     * Abra el navegador (Chrome o Firefox)
     *
     * Conéctese al sitio web http://demo.nopcommerce.com
     * Vaya a la categoría Computers / Software
     * Seleccione el ícono para que se despliegue por Lista (no por Grid)
     * En la opción Sort by seleccione Price: Low to high
     * Verifique que el primer artículo desplegado es SoundForge Pro 11
     * (recurring).
     * Ahora, en la opción Sort by seleccione Price: High to low
     * Verifique que el primer artículo desplegado es Adobe Photoshop CS4
     * Cierre el browser
     * Despliegue un mensaje indicando si el caso se ejecutó correctamente, indicando el
     * número de caso.
     */
    @Test
    void testCase() {
        clickIfVisible(By.className(TOGGLE_BUTTON_CLASS));
        waitToClickElement(By.linkText(COMPUTER_SECCION_TEXT)).click();
        waitToClickElement(By.linkText(SOFTWARE_SECCION_TEXT)).click();
        clickIfVisible(By.linkText(LIST_ICON_TEXT));

        sortOrder(SORT_ORDER_TEXT_1, FIRST_ELEMENT_EXPECTED_1);
        sortOrder(SORT_ORDER_TEXT_2, FIRST_ELEMENT_EXPECTED_2);

        LOG.info("Test case 4 completed successfully");
    }


    private void sortOrder(String order, String expectedResult) {
        Select select =  new Select(waitToClickElement(By.id(SORT_SELECT_ID)));
        select.selectByVisibleText(order);
        assertHasText(By.cssSelector(FIRST_ITEM_TITLE),  expectedResult);
    }
}
