package Domain;

import lombok.Getter;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Discount {

    @Getter
    private int id;

    private double discountValue;

    public Discount(double discountValue) {
        this.discountValue = discountValue;
    }

    public double calcDiscount(double price) {
        return price*(1-discountValue);
    }

    public double getDiscountValue() {
        return this.discountValue;
    }
}
