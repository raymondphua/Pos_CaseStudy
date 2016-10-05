package Domain;

import lombok.Data;

/**
 * Created by Raymond Phua on 22-9-2016.
 */
@Data
public class Customer {

    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
