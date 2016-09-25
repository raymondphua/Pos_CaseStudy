package Domain;

import lombok.NoArgsConstructor;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
@NoArgsConstructor
public class Product {

    private int digitCode;
    private double price;
    private int amount;
    private ProductSpec spec;
    private Discount discount;

    //constructor
    public Product(int digitCode, double price, int amount, ProductSpec spec) {
        this.digitCode = digitCode;
        this.price = price;
        this.amount = amount;
        this.spec = spec;

        //for now, default discount value of 10% (0.1)
        this.discount = new Discount(0.1);
    }

    public Product(int digitCode, double price, int amount) {
        this.digitCode = digitCode;
        this.price = price;
        this.amount = amount;
        this.discount = new Discount(0.1);
    }

    public boolean matchesCode(int digitCode) {
        return this.digitCode == digitCode;
    }

    //getters
    public int getDigitCode() {
        return this.digitCode;
    }

    public double getPrice() {

        if (this.discount.getDiscountValue() != 0.0) {
            return this.discount.calcDiscount(this.price);
        }
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }

    public ProductSpec getSpec() {
        return this.spec;
    }

    public void setProductSpec(ProductSpec spec) {
        this.spec = spec;
    }

    //setters
    public void setPrice(double price) {
        this.price = price;
    }
}
