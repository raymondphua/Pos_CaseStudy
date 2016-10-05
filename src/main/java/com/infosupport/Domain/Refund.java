package com.infosupport.Domain;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Refund extends Transaction {

    public Refund() {
        super();
    }

    @Override
    public void endTransaction() {
        System.out.println("*************************");
        System.out.println("Ending transaction...");
        System.out.println();
        System.out.println("Following products refunded: ");
        for(Product p : products) {
            System.out.println(p.getSpec().getProperty("Name"));
        }
        System.out.println();
        System.out.println("Total refund: " + this.total);
        System.out.println("*************************");
    }
}
