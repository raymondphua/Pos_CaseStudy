package com.infosupport.Domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Reservation extends Transaction {

    @Getter
    private Map<FidelityCard, ArrayList<Product>> reservedProducts;

    public Reservation() {
        super();
        this.reservedProducts = new HashMap<>();
    }

    @Override
    public void endTransaction() {
        System.out.println("*************************");
        System.out.println("Ending transaction...");
        System.out.println();
        System.out.println("Following products reserved: ");
        for(Product p : products) {
            System.out.println(p.getSpec().getProperty("Name") + " - advance: " + p.isAdvance());
        }
        System.out.println();
        System.out.println("Total price: " + this.total);
        System.out.println("*************************");
    }

    public void reserveProduct(Product product) {
        System.out.println("Reserved product: ");
        System.out.println("id: " + product.getDigitCode());
        System.out.println("price: " + product.getPrice());
        System.out.println("spec: " + product.getSpec().getProperty("Name"));
        System.out.println("advance: " + product.isAdvance());

        this.total += (product.getPrice()/4); // todo: advance is now 1/4 of the price by default

        if (!reservedProducts.containsKey(currentFidelity)) {
            reservedProducts.put(currentFidelity, new ArrayList<>());
        }

        reservedProducts.get(currentFidelity).add(product);
    }
}
