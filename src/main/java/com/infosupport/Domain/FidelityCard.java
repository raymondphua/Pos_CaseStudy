package com.infosupport.Domain;

import lombok.Data;

/**
 * Created by Raymond Phua on 22-9-2016.
 */
@Data
public class FidelityCard {
    private int id;
    private Customer customer;

    public FidelityCard(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }
}
