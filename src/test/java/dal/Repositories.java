package dal;

import test_data_models.Product;

public class Repositories {

    private static final ExcelRepository<Product> productRepo =
            new ExcelRepository<>("./src/test/java/assets/Parametros.xlsx", Product.class);

    private Repositories() { throw new AssertionError(); }


    public static ExcelRepository<Product> getProductRepo() {
        return productRepo;
    }
}
