package assignment1;

import base.SeleniumTestCaseBase;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

public class SeleniumTestCase1NopCommerce extends SeleniumTestCaseBase {

    private static final Logger LOG = Logger.getLogger(SeleniumTestCase1NopCommerce.class.getName());

    private final static int LOAD_PAGE_WAIT_TIME	= 3000;
    private final static int CLOSE_PAGE_WAIT_TIME	= 5000;
    private final static String PAGE_TITLE 		 	= "nopCommerce demo store";


    /**
     * Abra el navegador (Chrome o Firefox)
     *
     * Conactese al sitio web http://demo.nopcommerce.com
     * Verifique que el title sea NopCommerce demo store
     * Despliegue un mensaje indicando si el title es correcto.
     * Espere 5 segundos
     * Cierre el browser
     */
    @Test
    public void TestCase() {
        waitExact(LOAD_PAGE_WAIT_TIME);
        checkTitle();
        waitExact(CLOSE_PAGE_WAIT_TIME);
        
        LOG.info("Test case 1 completed successfully");
    }


    
    private void checkTitle() {    	
    	if (getDriver().getTitle().equals(PAGE_TITLE))
    		LOG.info("El titulo si es '" + PAGE_TITLE + "'");
    	else
    		LOG.warning("El titulo no es '" + PAGE_TITLE + "'");
    }
}