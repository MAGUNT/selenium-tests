package assignment2;

import base.SeleniumTestCaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

class SeleniumTestCase4NopCommerce extends SeleniumTestCaseBase {

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


    @Test
    void testCase() {
        connectToSite();

        clickIfVisible(By.className(TOGGLE_BUTTON_CLASS));
        waitToClickElement(By.linkText(COMPUTER_SECCION_TEXT)).click();
        waitToClickElement(By.linkText(SOFTWARE_SECCION_TEXT)).click();
        clickIfVisible(By.linkText(LIST_ICON_TEXT));

        sortOrder(SORT_ORDER_TEXT_1, FIRST_ELEMENT_EXPECTED_1);
        sortOrder(SORT_ORDER_TEXT_2, FIRST_ELEMENT_EXPECTED_2);
    }


    private void sortOrder(String order, String expectedResult) {
        Select select =  new Select(waitToClickElement(By.id(SORT_SELECT_ID)));
        select.selectByVisibleText(order);
        assertHasText(By.cssSelector(FIRST_ITEM_TITLE),  expectedResult);
    }
}
