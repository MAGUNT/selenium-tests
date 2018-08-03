package base;

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
import java.util.regex.Pattern;

public abstract class SeleniumTestCaseBase {
    private final static String NAME_SYSTEM_PROPERTY	= "webdriver.chrome.driver";
    private final static String VALUE_SYSTEM_PROPERTY	= "./src/test/java/assets/chromedriver.exe";
    private final static int MAX_WAITING_TIME           =  5;
    protected final static String NOP_COMMERCE_URL      = "http://demo.nopcommerce.com/";
    protected WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        System.setProperty(NAME_SYSTEM_PROPERTY, VALUE_SYSTEM_PROPERTY);
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void shutDownDriver() {
        this.getDriver().quit();
    }


    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait  wait(final int seconds) {
        return new WebDriverWait(driver, seconds);
    }

    public WebElement waitToFoundElement(final By byStrategy) {
        return wait(MAX_WAITING_TIME).until(
                ExpectedConditions.presenceOfElementLocated(byStrategy));
    }

    public WebElement waitToClickElement(final By byStrategy) {
        return wait(MAX_WAITING_TIME).until(
                ExpectedConditions.elementToBeClickable(byStrategy));

    }

    public void clickIfVisible(By byStrategy) {
        WebElement item = waitToFoundElement(byStrategy);
        if(item.isDisplayed()) {
            item.click();
        }
    }

    public void waitExact(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean hasText(By byStrategy, String string) {
        return wait(MAX_WAITING_TIME).until(ExpectedConditions.textToBe(byStrategy, string));
    }

    public void assertHasText(By byStrategy, String string) {
        Assertions.assertTrue(hasText(byStrategy, string));
    }

    public boolean matchesText(By byStrategy, String string) {
        return wait(MAX_WAITING_TIME).until(ExpectedConditions.textMatches(byStrategy, Pattern.compile(string)));
    }

    public void connectToSite() {
        getDriver().get(NOP_COMMERCE_URL);
    }
}
