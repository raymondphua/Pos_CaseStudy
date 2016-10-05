import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Domain.*;
import Properties.*;
/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class WarehouseTester {

    static boolean stopTransaction = false;
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Employee employee = new Employee(1, "admin", "admin");
        Transaction transaction = null;


        //login user
        System.out.println("Welcome, please sign in with your username and password...");

        while (true) {
            System.out.println("Username:");
            String username = reader.nextLine();

            System.out.println("Password:");
            String password = reader.nextLine();

            if (!employee.matches(username, password)) {
                System.out.println("Username and/or password does not exist!");
            } else {
                break;
            }
        }

        //start session after login
        //TODO HARDCODED KASSA ID YES/NO?
        Session session = new Session(employee, 1);

        System.out.println("******* Welcome, " + employee.getUsername() + " *******");
        System.out.println();

        initializeWarehouse(Warehouse.getInstance());

        while (!stopTransaction) {
            transaction = chooseTransaction(reader);

            if (transaction instanceof Sale) {
                Sale salesTransaction = (Sale) transaction;

                Product product = Warehouse.getInstance().getProduct(1111);
                System.out.println("Product id: " + product.getDigitCode());
                System.out.println("Product price: " + product.getPrice());
                System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
                System.out.println();
                salesTransaction.addProduct(product);

                product = Warehouse.getInstance().getProduct(2222);
                System.out.println("Product id: " + product.getDigitCode());
                System.out.println("Product price: " + product.getPrice());
                System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
                System.out.println();

                salesTransaction.addProduct(product);

                product = Warehouse.getInstance().getProduct(3333);
                System.out.println("Product id: " + product.getDigitCode());
                System.out.println("Product price: " + product.getPrice());
                System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
                System.out.println();

                salesTransaction.addProduct(product);

                salesTransaction.endTransaction();
                session.addTransaction(salesTransaction);
            } else if (transaction instanceof Reservation) {
                Reservation reservationTransaction = (Reservation) transaction;
                scanFidelityCard(reservationTransaction);
                Product reservationProduct = Warehouse.getInstance().getProduct(4444);

                reservationTransaction.reserveProduct(reservationProduct);

                reservationTransaction.endTransaction();
                session.addTransaction(reservationTransaction);
            } else if (transaction instanceof Refund) {
                Refund refundTransaction = (Refund) transaction;
                scanFidelityCard(refundTransaction);
                Product refundProduct = Warehouse.getInstance().getProduct(4444);

                refundTransaction.removeProduct(refundProduct);

                refundTransaction.endTransaction();
                session.addTransaction(refundTransaction);
            }
        }

        session.showDetails();
    }

    private static void scanFidelityCard(Transaction transaction) {

        while (true) {
            System.out.println("Please input the customer fidelity card id: ");
            int fidelityCardId = reader.nextInt();
            boolean fidelityCardExists = transaction.scanFidelityCard(fidelityCardId);

            if (fidelityCardExists) {
                transaction.printFidelityCard();
                break;
            } else {
                System.out.println("Fidelity card id incorrect!");
            }
        }
    }

    private static Transaction chooseTransaction(Scanner reader) {
        TransactionFactory transactionFactory = new TransactionFactory();
        //choose transaction sale, refund, reservation
        Transaction transaction = null;
        boolean invalidChoice = true;
        while(invalidChoice) {
            System.out.println("Choose transaction: ");
            System.out.println("Sales (1) | Reservation (2) | Refund (3) | Quit (4)");
            int transactionChoice = reader.nextInt();
            TransactionType transactionType = null;

            switch(transactionChoice) {
                case 1: transactionType = TransactionType.SALE;
                        break;
                case 2: transactionType = TransactionType.RESERVATION;
                        break;
                case 3: transactionType = TransactionType.REFUND;
                        break;
                case 4: System.out.println("Quitting....");
                        invalidChoice = false;
                        stopTransaction = true;
                        break;
                default:System.out.println("Invalid input! Try again...");
            }

            if (transactionType != null) {
                transaction = transactionFactory.createTransaction(transactionType);
                invalidChoice = false;
            }
        }

        return transaction;
    }

    private static void initializeWarehouse(Warehouse warehouse) {
        Map properties = new HashMap();
        properties.put("Name", Name.COLA);
        warehouse.addProduct(1111, 1.5, 4, new ProductSpec(properties), false);

        properties.clear();
        properties.put("Name", Name.ICETEA);
        warehouse.addProduct(2222, 2, 4, new ProductSpec(properties), false);

        properties.clear();
        properties.put("Name", Name.MILK);
        warehouse.addProduct(3333, 1.25, 4, new ProductSpec(properties), false);

        properties.clear();
        properties.put("Name", Name.LAPTOP);
        warehouse.addProduct(4444, 300, 3, new ProductSpec(properties), true);
    }
}
