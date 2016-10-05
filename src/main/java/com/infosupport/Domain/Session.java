package com.infosupport.Domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        int salesAmount = 0;
        int reservationAmount = 0;
        int refundAmount = 0;

        double salesTotal = 0;
        double reservationTotal = 0;
        double refundTotal = 0;

        endTime = LocalDateTime.now();

        for (Transaction t : transactions) {
            if (t instanceof Sale) {
                salesAmount++;
                salesTotal += t.getTotal();
            } else if (t instanceof Reservation) {
                reservationAmount++;
                reservationTotal += t.getTotal();
            } else {
                refundAmount++;
                refundTotal += t.getTotal();
            }
        }

        System.out.println("Details for: ");
        System.out.println("Kassa: " + kassa);
        System.out.println("Employee: " + employee.getUsername());
        System.out.println("Time: " + hoursRun()) ;
        System.out.println("Total transactions: " + transactions.size());
        System.out.println();
        System.out.println("Sales transactions: " + salesAmount);
        System.out.println("Sales total: " + salesTotal);
        System.out.println();
        System.out.println("Reservation transactions: " + reservationAmount);
        showReservations();
        System.out.println("Reservation total: " + reservationTotal);
        System.out.println();
        System.out.println("Refund transactions: " + refundAmount);
        System.out.println("Refund total: " + refundTotal);
        System.out.println();
        System.out.println("Total revenue: " + calculateTotalRevenue());
    }

    private int hoursRun() {
        return (int)ChronoUnit.HOURS.between(startTime, endTime);
    }

    private void showReservations() {
        FidelityCard key = null;
        System.out.println("Reservations for: ");
        for (Transaction t : transactions) {
            if (t instanceof Reservation) {
                Reservation reservation = (Reservation) t;
                for (FidelityCard fidelityCard : reservation.getReservedProducts().keySet()) {
                    key = fidelityCard;
                    System.out.println("customer: " + fidelityCard.getCustomer().getName());
                    System.out.println("Card id: " + fidelityCard.getId());
                }
                System.out.println("Reserved products: ");
                for (Product p : reservation.getReservedProducts().get(key)) {
                    System.out.println(p.getSpec().getProperty("Name"));
                }
            }
        }
    }

    private double calculateTotalRevenue() {
        double total = 0;
        for(Transaction t : transactions) {
            total += t.getTotal();
        }

        return total;
    }
}
