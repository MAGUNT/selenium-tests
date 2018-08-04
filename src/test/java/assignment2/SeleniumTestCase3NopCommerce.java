package assignment2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

class SeleniumTestCase3NopCommerce extends SeleniumTestCase2NopCommerce {
    private static final Logger LOG = Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());
    private static final String COUNTRY_SELECT_ID           = "CountryId";
    private static final String ZIP_INPUT_ID                = "ZipPostalCode";
    private static final String COUNTRY_NAME                = "Costa Rica";
    private static final String ZIP_CODE                    = "1000-1";
    private static final String TERMS_SERVICE_CHECK_ID      = "termsofservice";
    private static final String ESTIMATE_SHIPPING_BUTTON_ID = "estimate-shipping-button";
    private static final String CHECK_OUT_BUTTON_ID         = "checkout";
    private static final String CHECK_OUT_AS_GUEST_URL      =  "/login/checkoutasguest";
    private static final String QUANTITY                    = "0";

    /**
     * Vaya a la opción Shopping Cart
     *
     * En el campo Country (debajo de Estimate shipping) seleccione Costa Rica
     * En el campo Zip / postal code ingrese el valor 1000-1
     * De click en el botón Estimate shipping
     * Marque la opción “I agree with the terms of service...”
     * De click en la opción Checkout
     * Verifique que no es permitido hacer Checkout sin haber hecho antes Sign in
     * Vaya nuevamente a la opción Shopping Cart
     * En el campo Qty digite 0 (cero)
     * De click en la opción Update Shopping Cart
     * Verifique que el Shopping Cart está vacío
     * Cierre el browser
     * Despliegue un mensaje indicando si el caso se ejecutó correctamente, indicando el
     * número de caso.
     */
    @Test
    @Override
    void testCase() {
        super.testCase();
        goToShoppingCart();
        selectCountry();
        enterZipCode();

        checkout();
        assertContainsURL(CHECK_OUT_AS_GUEST_URL);
        goToShoppingCart();
        updateQuantity(QUANTITY);
        updateCart();
        checkShoppingCartIsEmpty();
        LOG.info("Test case 3 completed successfully");
    }

    private void enterZipCode() {
        WebElement zipInput = waitToClickElement(By.id(ZIP_INPUT_ID));
        zipInput.clear();
        zipInput.sendKeys(ZIP_CODE);
    }

    private void checkout() {
        waitToClickElement(By.id(ESTIMATE_SHIPPING_BUTTON_ID)).click();
        waitToClickElement(By.id(TERMS_SERVICE_CHECK_ID)).click();
        waitToClickElement(By.id(CHECK_OUT_BUTTON_ID)).click();
    }
    private void selectCountry() {
        Select select = new Select(waitToClickElement(By.id(COUNTRY_SELECT_ID)));
        select.selectByVisibleText(SeleniumTestCase3NopCommerce.COUNTRY_NAME);
    }
}
