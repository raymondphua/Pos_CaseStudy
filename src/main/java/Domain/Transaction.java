package Domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
@Getter
public abstract class Transaction {

    private int id;
    private double total;
    private LocalDateTime date;
    private List<Product> products;

    public Transaction() {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.total = 0;
        this.date = LocalDateTime.now();
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.total += product.getPrice();
        products.add(product);
    };

    public void removeProduct(Product product) {
        for(Product p : products) {
            if (p.matchesCode(product.getDigitCode())) {
                this.total -= p.getPrice();
                products.remove(p);
            }
        }
    }

    public void endTransaction() {
        System.out.println("*************************");
        System.out.println("Ending transaction...");
        System.out.println();
        for(Product p : products) {
            System.out.println(p.getSpec().getProperty("Name") + " : " + p.getPrice());
        }
        System.out.println();
        System.out.println("Total price: " + this.total);
        System.out.println("*************************");
    }
}
