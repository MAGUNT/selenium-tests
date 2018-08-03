package assignment2;

import base.SeleniumTestCaseBase;
import dal.Repositories;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test_data_models.Product;
import java.util.List;


class SeleniumTestCase5NopCommerce extends SeleniumTestCaseBase {
    private static final String UPDATE_WISH_LIST_CLASS         = "update-wishlist-button";
    private static final String WISH_LIST_CLASS                = "ico-wishlist";
    private static final String WISH_LIST_EMPTY_MSG            = "The wishlist is empty!";
    private static final String NO_DATA_SELECTOR               = ".no-data";
    private static final String SEARCH_INPUT_ID                = "small-searchterms";
    private static final String WISH_LIST                      = "wishlist";
    private static final String WISH_LIST_FIRST_ROW            = ".cart tbody tr:first-of-type";
    private static final String WISH_LIST_PRODUCT_NAME_A       = WISH_LIST_FIRST_ROW + " .product-name";
    private static final String WISH_LIST_PRODUCT_QTY_INPUT    = WISH_LIST_FIRST_ROW + " .qty-input";
    private static final String WISH_LIST_PRODUCT_REMOVE_INPUT = WISH_LIST_FIRST_ROW + " .remove-from-cart input";
    private static final String WISH_LIST_PRODUCT_PRICE        = WISH_LIST_FIRST_ROW + " .product-subtotal";
    private static final String WISH_LIST_BUTTON_XPATH =
            "//*[contains(@class, \"item-box\")]" +
            "/*[descendant::a[contains(text(), \"%s\")]]" +
            "/descendant::input[contains(@class, \"add-to-wishlist-button\")]";

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void testCase(final Product product) {

        connectToSite();
        checkWishListIsEmpty();
        searchProduct(product);
        addToWishList(product);

        waitToClickElement(By.linkText(WISH_LIST)).click();

        checkProduct(product);
        updateQuantity(product);
        checkTotal(product);
        removeProduct();
    }

    private void removeProduct() {
        waitToClickElement(By.cssSelector(WISH_LIST_PRODUCT_REMOVE_INPUT)).click();
        waitToClickElement(By.className(UPDATE_WISH_LIST_CLASS)).click();
    }

    private void checkTotal(final Product product) {
        assertHasText(By.cssSelector(WISH_LIST_PRODUCT_PRICE), product.getTotal());
    }

    private void checkProduct(final Product product) {
        assertHasText(By.cssSelector(WISH_LIST_PRODUCT_NAME_A), product.getName());
    }

    private void updateQuantity(final Product product) {
        WebElement qtyInput = waitToClickElement(By.cssSelector(WISH_LIST_PRODUCT_QTY_INPUT));
        qtyInput.clear();
        qtyInput.sendKeys(product.getQuantity());
        updateWishList();
    }

    private void updateWishList() {
        waitToClickElement(By.className(UPDATE_WISH_LIST_CLASS)).click();
    }

    private void checkWishListIsEmpty() {
        waitToClickElement(By.className(WISH_LIST_CLASS)).click();
        assertHasText(By.cssSelector(NO_DATA_SELECTOR), WISH_LIST_EMPTY_MSG);
    }

    private void searchProduct(final Product product) {
        WebElement searchInput = waitToClickElement(By.id(SEARCH_INPUT_ID));
        searchInput.sendKeys(product.getName());
        searchInput.submit();
    }

    private void addToWishList(final Product product) {
        final String xpath = String.format(WISH_LIST_BUTTON_XPATH, scapeString(product.getName()));
        waitToClickElement(By.xpath(xpath)).click();
    }

    private static List<Product> testCaseProvider() {
        return Repositories.getProductRepo().getAll();
    }

    private String scapeString(final String string) {
        return string.replace("\"", "\\\"");
    }
}
