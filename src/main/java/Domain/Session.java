package Domain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class Session {

    private List<Transaction> transactions;
    private Employee employee;
    private int kassa;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Session(Employee employee, int kassaId) {
        this.employee = employee;
        this.kassa = kassaId;
        this.transactions = new LinkedList<>();
        this.startTime = LocalDateTime.now();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    //TODO: start + end time calculation + show
    public void showDetails() {
        System.out.println("Details for: ");
        System.out.println("Kassa: " + kassa);
        System.out.println("Domain.Employee: " + employee.getUsername());
        System.out.println("Transactions: " + transactions.size());
        System.out.println();
        System.out.println("Total revenue: " + calculateTotalRevenue());
    }

    private double calculateTotalRevenue() {
        double total = 0;
        for(Transaction t : transactions) {
            total += t.getTotal();
        }

        return total;
    }
}
