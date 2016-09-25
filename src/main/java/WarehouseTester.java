import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Domain.*;
import Properties.*;
/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class WarehouseTester {
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

        transaction = chooseTransaction(reader);

        initializeWarehouse(Warehouse.getInstance());

        Product product = Warehouse.getInstance().getProduct(1111);
        System.out.println("Product id: " + product.getDigitCode());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
        System.out.println();
        transaction.addProduct(product);

        product = Warehouse.getInstance().getProduct(2222);
        System.out.println("Product id: " + product.getDigitCode());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
        System.out.println();

        transaction.addProduct(product);

        product = Warehouse.getInstance().getProduct(3333);
        System.out.println("Product id: " + product.getDigitCode());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
        System.out.println();

        transaction.addProduct(product);

        transaction.endTransaction();
        session.addTransaction(transaction);

        transaction = chooseTransaction(reader);

        product = Warehouse.getInstance().getProduct(1111);
        System.out.println("Product id: " + product.getDigitCode());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
        System.out.println();

        transaction.addProduct(product);

        product = Warehouse.getInstance().getProduct(3333);
        System.out.println("Product id: " + product.getDigitCode());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
        System.out.println();

        transaction.addProduct(product);

        transaction.endTransaction();
        session.addTransaction(transaction);

        transaction = chooseTransaction(reader);

        product = Warehouse.getInstance().getProduct(2222);
        System.out.println("Product id: " + product.getDigitCode());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Product spec: " + product.getSpec().getProperty("Name"));
        System.out.println();

        transaction.addProduct(product);

        transaction.endTransaction();
        session.addTransaction(transaction);

        transaction = chooseTransaction(reader);
        session.showDetails();
    }

    private static Transaction chooseTransaction(Scanner reader) {
        TransactionFactory transactionFactory = new TransactionFactory();
        //choose transaction sale, refund, reservation
        Transaction transaction = null;
        boolean invalidChoice = true;
        while(invalidChoice) {
            System.out.println("Choose transaction: ");
            System.out.println("Sales (1) | Refund (2) | Reservation (3) | Quit (4)");
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
        warehouse.addProduct(1111, 1.5, 4, new ProductSpec(properties));

        properties.clear();
        properties.put("Name", Name.ICETEA);
        warehouse.addProduct(2222, 2, 4, new ProductSpec(properties));

        properties.clear();
        properties.put("Name", Name.MILK);
        warehouse.addProduct(3333, 1.25, 4, new ProductSpec(properties));
    }
}
