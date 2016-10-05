package Domain;

import lombok.Getter;

import javax.print.attribute.standard.Fidelity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
@Getter
public abstract class Transaction {

    protected int id;
    protected double total;
    protected LocalDateTime date;
    protected List<Product> products;
    protected List<FidelityCard> fidelityCards;
    protected FidelityCard currentFidelity;


    public Transaction() {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.total = 0;
        this.date = LocalDateTime.now();
        this.products = new ArrayList<>();
        this.fidelityCards = new ArrayList<>();

        addFidelityCards();
    }

    public void addProduct(Product product){
        this.total += product.getPrice();
        products.add(product);
    };

    public void removeProduct(Product product) {
        this.total -= product.getPrice();
        products.remove(product);
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

    public boolean scanFidelityCard(int id) {
        FidelityCard fidelityCard = fidelityCards.stream().filter(f -> f.getId() == id).findFirst().orElse(null);//.orElseThrow(() -> new RuntimeException());
        this.currentFidelity = fidelityCard;

        if (currentFidelity != null) {
            return true;
        } else {
            return false;
        }
    }

    public void printFidelityCard() {
        System.out.println("Current fidelity card: ");
        System.out.println("id: " + currentFidelity.getId());
        System.out.println("customer: " + currentFidelity.getCustomer().getName());
    }

    private void addFidelityCards() {
        Customer customer = new Customer(1234, "Test naam");
        Customer customer2 = new Customer(2222, "Customer 2");
        this.fidelityCards.add(new FidelityCard(1234, customer));
        this.fidelityCards.add(new FidelityCard(2222, customer2));
    }
}
