package com.infosupport.Domain;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Sale extends Transaction {

    public Sale() {
        super();
    }

    @Override
    public void endTransaction() {
        System.out.println("*************************");
        System.out.println("Ending transaction...");
        System.out.println();
        System.out.println("Following products sold: ");
        for(Product p : products) {
            System.out.println(p.getSpec().getProperty("Name") + " : " + p.getPrice());
        }
        System.out.println();
        System.out.println("Total price: " + this.total);
        System.out.println("*************************");
    }
}
