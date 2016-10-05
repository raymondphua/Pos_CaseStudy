package Database;

import Domain.Product;
import Domain.ProductSpec;
import Properties.Name;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 3-10-2016.
 */
public class Database {

    private static Connection conn;

    public Database() {
        for (Product product : getAllProducts()) {
            System.out.println(product.getDigitCode());
        }
    }

    private static void getConnection() {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:phua/admin@localhost:1521/XE");
            conn = ods.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ResultSet getAllBooks() throws SQLException {
        getConnection();

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String query = "SELECT * FROM BOOKS ORDER BY book_id";

        System.out.println("\nExecuting query: " + query);
        ResultSet rset = stmt.executeQuery(query);

        return rset;
    }

    public static List<Product> getAllProducts() {
        getConnection();

        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String query = "SELECT * FROM PRODUCTS p JOIN PRODUCT_SPECS ps ON p.DIGITCODE = ps.PRODUCT_ID_FK " +
                    "JOIN DISCOUNTS d ON p.FOR_KEY_DISCOUNT = d.ID ORDER BY p.DIGITCODE";

            System.out.println("\n Executing query: " + query);
            ResultSet rset = stmt.executeQuery(query);

            List<Product> products = new ArrayList<>();
            while (rset.next()) {
                Map properties = new HashMap();
                Name name = getNameFromDb(rset.getString("value"));
                properties.put("Name", name);

                int digitcode = rset.getInt("digitcode");
                double price = rset.getDouble("price");
                int amount = rset.getInt("amount");
                boolean advance = rset.getBoolean("advance");

                products.add(new Product(digitcode, price, amount, new ProductSpec(properties), advance));
            }

            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static Name getNameFromDb(String name) {
        switch (name) {
            case "COLA":
                return Name.COLA;
            case "ICETEA":
                return Name.ICETEA;
            case "MILK":
                return Name.MILK;
            case "LAPTOP":
                return Name.LAPTOP;
            default:
                return Name.COLA;
        }
    }
}
