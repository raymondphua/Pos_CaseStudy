package Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Product {

    @Getter
    private int digitCode;
    private double price;
    @Getter
    private int amount;
    @Getter
    private ProductSpec spec;
    @Getter
    private Discount discount;
    @Getter
    private boolean advance;

    public Product() {
        this.discount = new Discount(0.1);
    }
    //constructor
    public Product(int digitCode, double price, int amount, ProductSpec spec, boolean advance) {
        this.digitCode = digitCode;
        this.price = price;
        this.amount = amount;
        this.spec = spec;
        this.advance = advance;

        //for now, default discount value of 10% (0.1)
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

    public Discount getDiscount() {
        return this.discount;
    }

    public void setProductSpec(ProductSpec spec) {
        this.spec = spec;
    }

    //setters
    public void setPrice(double price) {
        this.price = price;
    }
}
