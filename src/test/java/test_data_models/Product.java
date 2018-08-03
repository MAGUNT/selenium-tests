package test_data_models;

import com.poiji.annotation.ExcelCellName;

public class Product {

    @ExcelCellName("Product")
    private String name;

    @ExcelCellName("Qty")
    private String quantity;

    @ExcelCellName("Total")
    private String total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)",
                getName(), getQuantity(), getTotal());
    }
}
