package Domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Warehouse {

    private static Warehouse instance = null;
    private static List<Product> warehouse;

    private Warehouse(){ this.warehouse = new LinkedList<>(); };

    public static Warehouse getInstance() {
        if(instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void addProduct(int digitCode, double price, int amount, ProductSpec spec) {
        Product newProduct = new Product(digitCode, price, amount, spec);
        warehouse.add(newProduct);
    }

    public Product getProduct(int code) {
        for(Product p : warehouse) {
            if (p.matchesCode(code)) {
                return p;
            }
        }

        return null;
    }
}
