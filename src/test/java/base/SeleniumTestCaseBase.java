package base;

import dal.Repositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_data_models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class SeleniumTestCaseBase {
    private static final String ADD_TOCART_BUTTON_CLASS        = ".wishlist-add-to-cart-button";
    private static final String WISH_LIST                      = "wishlist";
    private static final String NO_DATA_SELECTOR               = ".no-data";
    private static final String WISH_LIST_CLASS                = "ico-wishlist";
    private static final String SHOPPING_CART_CLASS            = "ico-cart";
    private static final String WISH_LIST_EMPTY_MSG            = "The wishlist is empty!";
    private static final String SHOPPING_CART_EMPTY_MSG        = "Your Shopping Cart is empty!";
    private static final String SEARCH_INPUT_ID                = "small-searchterms";
    private static final String UPDATE_WISH_LIST_CLASS         = "update-wishlist-button";
    private static final String UPDATE_CART_CLASS              = ".update-cart-button";
    private static final String WISH_LIST_FIRST_ROW            = ".cart tbody tr:first-of-type";
    private static final String WISH_LIST_PRODUCT_NAME_A       = WISH_LIST_FIRST_ROW + " .product-name";
    private static final String WISH_LIST_PRODUCT_QTY_INPUT    = WISH_LIST_FIRST_ROW + " .qty-input";
    private static final String WISH_LIST_PRODUCT_REMOVE_INPUT = WISH_LIST_FIRST_ROW + " .remove-from-cart input";
    private static final String WISH_LIST_PRODUCT_PRICE        = WISH_LIST_FIRST_ROW + " .product-subtotal";
    private static final String WISH_LIST_ADD_CART_INPUT       = WISH_LIST_FIRST_ROW + " .add-to-cart input";
    private static final String WISH_LIST_BUTTON_XPATH  =
            "//*[contains(@class, \"item-box\")]" +
                    "/*[descendant::a[contains(text(), %s)]]" +
                    "/descendant::input[contains(@class, \"add-to-wishlist-button\")]";



    private final static String NAME_SYSTEM_PROPERTY	= "webdriver.chrome.driver";
    private final static String VALUE_SYSTEM_PROPERTY	= "./src/test/java/assets/chromedriver";
    private final static int MAX_WAITING_TIME           =  5;
    protected final static String NOP_COMMERCE_URL      = "http://demo.nopcommerce.com/";

    protected WebDriver driver;

    /**
     * Este método se ejecuta antes de cada test
     */
    @BeforeEach
    public void startBrowser() {
        System.setProperty(NAME_SYSTEM_PROPERTY, VALUE_SYSTEM_PROPERTY);
        this.driver = new ChromeDriver();
        connectToSite();
    }

    /**
     * Este método se ejecuta despues de cada test
     */
    @AfterEach
    public void shutDownDriver() {
        this.getDriver().quit();
    }


    /**
     *
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Retorna un objeto WebDriverWait utilizando el método getDriver().
     * @param seconds cantidad de segundos máxima a esperar.
     * @return WebDriverWait
     */
    public WebDriverWait  wait(final int seconds) {
        return new WebDriverWait(driver, seconds);
    }


    /**
     * Espera a que el elemento sea encontrado.
     * @param byStrategy estrategia de busqueda.
     * @return WebElement
     */
    public WebElement waitToFoundElement(final By byStrategy) {
        return wait(MAX_WAITING_TIME).until(
                ExpectedConditions.presenceOfElementLocated(byStrategy));
    }
    /**
     * Espera a que el elemento sea seleccionable.
     * @param byStrategy estrategia de busqueda.
     * @return WebElement
     */
    public WebElement waitToClickElement(final By byStrategy) {
        return wait(MAX_WAITING_TIME).until(
                ExpectedConditions.elementToBeClickable(byStrategy));

    }

    /**
     * Presiona el elemento si es visible, de lo contrario no hace nada.
     * @param byStrategy
     */
    public void clickIfVisible(By byStrategy) {
        WebElement item = waitToFoundElement(byStrategy);
        if(item.isDisplayed()) {
            item.click();
        }
    }

    /**
     * Espera duerme el hilo una cantidad determinada de milisegundos.
     * @param milliseconds milisegundos
     */
    public void waitExact(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Método que espera a que un elemento contenga cierto texto.
     * @param byStrategy estrategia de busqueda
     * @param string texto
     * @return si contiene el texto.
     */
    public boolean hasText(By byStrategy, String string) {
        return wait(MAX_WAITING_TIME).until(ExpectedConditions.textToBe(byStrategy, string));
    }

    /**
     * Método que espera a que un elemento contenga cierto texto y ejecuta un assert true.
     * @param byStrategy estrategia de busqueda
     * @param string texto
     * @return si contiene el texto.
     */
    public void assertHasText(By byStrategy, String string) {
        Assertions.assertTrue(hasText(byStrategy, string));
    }


    /**
     *
     * Método que espera a que un elemento contenga un patron y ejecuta un assert true
     * @param byStrategy estrategia de busqueda
     * @param string texto
     * @return si contiene el texto.
     */
    public void assertMatchesText(By byStrategy, String string) {
        Assertions.assertTrue(matchesText(byStrategy, string));
    }


    /**
     *
     * Método que espera a que un elemento contenga un patron.
     * @param byStrategy estrategia de busqueda
     * @param string texto
     * @return si contiene el texto.
     */
    public boolean matchesText(By byStrategy, String string) {
        Pattern pattern = Pattern.compile(string);
        return wait(MAX_WAITING_TIME)
                .until(ExpectedConditions.textMatches(byStrategy, pattern));
    }

    /**
     * Ingresa a la pagina web.
     */
    public void connectToSite() {
        getDriver().get(NOP_COMMERCE_URL);
    }

    /**
     * Verifica que el wishlist este vacío.
     */
    public void checkWishListIsEmpty() {
        goToWishList();
        assertHasText(By.cssSelector(NO_DATA_SELECTOR), WISH_LIST_EMPTY_MSG);
    }

    /**
     * Ingresa al wishlist
     */
    public void goToWishList() {
        waitToClickElement(By.className(WISH_LIST_CLASS)).click();
    }

    /**
     * Ingresa al shopping cart
     */
    public void goToShoppingCart() {
        waitToClickElement(By.className(SHOPPING_CART_CLASS)).click();
    }

    /**
     * Verifica que el shopping cart este vacío.
     */
    public void checkShoppingCartIsEmpty() {
        goToShoppingCart();
        assertHasText(By.cssSelector(NO_DATA_SELECTOR), SHOPPING_CART_EMPTY_MSG);
    }

    /**
     * Agrega un elemento al shopping cart.
     */
    public void addItemToCart() {
        waitToClickElement(By.cssSelector(WISH_LIST_ADD_CART_INPUT)).click();
        waitToClickElement(By.cssSelector(ADD_TOCART_BUTTON_CLASS)).click();

    }

    /**
     * Busca producto
     * @param name nombre del producto
     */
    public void searchProduct(final String name) {
        WebElement searchInput = waitToClickElement(By.id(SEARCH_INPUT_ID));
        searchInput.sendKeys(name);
        searchInput.submit();
    }

    /**
     * Assert para verificar si el url contiene un texto determinado
     * @param url url
     */
    public void assertContainsURL(String url) {
        boolean contains = wait(MAX_WAITING_TIME).until(
                ExpectedConditions.urlContains(url));

        Assertions.assertTrue(contains);
    }

    /**
     * Agrega un elemento al WishList.
     * @param name nombre del producto
     */
    public void addToWishList(final String name) {
        final String xpath = String.format(WISH_LIST_BUTTON_XPATH, scapeString(name));
        waitToClickElement(By.xpath(xpath)).click();
        waitToClickElement(By.linkText(WISH_LIST)).click();
    }


    /**
     * Escapa " y  de un string
     * @param string texto
     * @return texto escapado
     */
    private String scapeString(final String string) {
        if(!string.contains("\"") && !string.contains("'")) {
            return "\"" + string + "\"";
        }
        Matcher matcher = Pattern.compile("[^'\"]+|['\"]").matcher(string);
        List<String> matches = new ArrayList<>();
        while ( matcher.find() ) {
            matches.add(matcher.group(0));
        }

        return "concat(" + matches.stream().map(this::mapMatch)
                .collect(Collectors.joining(",")) + ")";
    }

    private String mapMatch(String string) {
        if (string.equals("'"))  {
            return "\"\'\""; // output "'"
        }

        if (string.equals("\"")) {
            return "'\"'"; // output '"'
        }
        return "'" + string + "'";
    }
    /**
     * Verifica que un producto se encuentre en la primera fila del wishlist o shopping cart
     * @param name nombre del producto
     */
    public void checkProduct(final String name) {
        assertHasText(By.cssSelector(WISH_LIST_PRODUCT_NAME_A), name);
    }

    /**
     * Verifica que un producto se encuentre en la primera fila del wishlist o shopping cart.
     * Verifica que el texto brindado este contenido dentro del nombre del producto.
     * @param name nombre del producto
     */
    public void matchesProduct(final String name) {
        assertMatchesText(By.cssSelector(WISH_LIST_PRODUCT_NAME_A), name);
    }

    /**
     * Elimina un producto del wishlist
     */
    public void removeProduct() {
        waitToClickElement(By.cssSelector(WISH_LIST_PRODUCT_REMOVE_INPUT)).click();
        waitToClickElement(By.className(UPDATE_WISH_LIST_CLASS)).click();
    }

    /**
     * Verifica que el total del primer producto del wishlist o cart contenga el texto verificado.
     * @param total total de dinero
     */
    public void checkTotal(final String total) {
        assertHasText(By.cssSelector(WISH_LIST_PRODUCT_PRICE), total);
    }


    /**
     * Cambia la cantidad de productos del primer elemento que se encuentre en el wishlist o cart.
     * @param qty cantidad de productos
     */
    public void updateQuantity(final String qty) {
        WebElement qtyInput = waitToClickElement(By.cssSelector(WISH_LIST_PRODUCT_QTY_INPUT));
        qtyInput.clear();
        qtyInput.sendKeys(qty);
    }

    /**
     * Presiona el boton para modificar el wishlist
     */
    public void updateWishList() {
        waitToClickElement(By.className(UPDATE_WISH_LIST_CLASS)).click();
    }

    /**
     * Presiona el boton para modificar el cart
     */
    public void updateCart() {
        waitToClickElement(By.cssSelector(UPDATE_CART_CLASS)).click();
    }
}
