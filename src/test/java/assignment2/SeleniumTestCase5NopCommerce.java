package assignment2;

import base.SeleniumTestCaseBase;
import dal.Repositories;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import test_data_models.Product;

import java.util.List;
import java.util.logging.Logger;


class SeleniumTestCase5NopCommerce extends SeleniumTestCaseBase {
    private static final Logger LOG = Logger.getLogger(SeleniumTestCase2NopCommerce.class.getName());


    /**
     * Vaya la página Wishlist
     *
     * Verifique que se despliega el mensaje “The wishlist is empty!”
     * Vaya al campo de búsqueda de artículos utilizando el id= small-searchterms.
     * Busque el Producto indicado en la primera columna del Excel llamado Parametros.xls.
     * Agregue el Producto al Wishlist.
     * Vaya nuevamente al Wishlist y modifique el campo Qty para que incluya la cantidad que
     * se indica en la segunda columna del Excel Parametros.xls.
     * Verifique que el total es el indicado en la columna Total del Excel.
     * Remueva el Producto del Wishlist y actualice la lista (Update Wishlist).
     * Despliegue un mensaje indicando que el registro se procesó correctamente.
     * El procedimiento anterior debe ejecutarse tantas veces como filas haya en el Excel
     * suministrado.
     * @param product producto
     */
    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void testCase(final Product product) {
        checkWishListIsEmpty();

        searchProduct(product.getName());
        addToWishList(product.getName());
        checkProduct(product.getName());

        updateQuantity(product.getQuantity());
        updateWishList();
        checkTotal(product.getTotal());
        removeProduct();

        LOG.info("Test case 5 completed successfully");
    }

    /**
     * Proveedor del data driven test
     * @return productos
     */
    static List<Product> testCaseProvider() {
        return Repositories.getProductRepo().getAll();
    }
}
