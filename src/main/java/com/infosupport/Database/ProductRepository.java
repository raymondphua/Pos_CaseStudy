package com.infosupport.Database;

import com.infosupport.Domain.Product;
import com.infosupport.Domain.ProductSpec;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Raymond Phua on 5-10-2016.
 */
public class ProductRepository {

    private static Connection conn;

    public static List<Product> getAllProducts() {
        getConnection();

        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String query = "SELECT * FROM PRODUCTS p JOIN PRODUCT_SPECS ps ON p.DIGITCODE = ps.PRODUCT_ID_FK " +
                    "JOIN DISCOUNTS d ON p.FOR_KEY_DISCOUNT = d.ID ORDER BY p.DIGITCODE";

            System.out.println("\n Executing query: " + query);
            ResultSet rset = stmt.executeQuery(query);

            List<Product> products = convertResultSetToList(rset);

            stmt.close();
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Product getOneProduct(int digitcode) {
        getConnection();

        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String query = "SELECT * FROM PRODUCTS p JOIN PRODUCT_SPECS ps ON p.digitcode = ps.product_id_fk WHERE DIGITCODE = " + digitcode;

            System.out.println("\n Executing query: " + query);
            ResultSet rset = stmt.executeQuery(query);

            List<Product> products = convertResultSetToList(rset);

            //first index
            Product product = products.get(0);

            stmt.close();
            return product;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static Product mapToProduct(ResultSet rset, Product product) throws SQLException {

        if (product != null) {
            ProductSpec specs = product.getSpec();
            specs.getProperties().put(rset.getString("property"), rset.getString("value"));
        } else {
            Map properties = new HashMap();
            //Name name = getNameFromDb(rset.getString("value"));
            properties.put(rset.getString("property"), rset.getString("value"));

            int code = rset.getInt("digitcode");
            double price = rset.getDouble("price");
            int amount = rset.getInt("amount");
            boolean advance = rset.getBoolean("advance");

            product = new Product(code, price, amount, new ProductSpec(properties), advance);
        }

        return product;
    }

    private static List<Product> convertResultSetToList(ResultSet rset) throws SQLException{
        Map<Integer, Product> foundProducts = new HashMap<>();

        while (rset.next()) {
            Product existingProduct = foundProducts.get(rset.getInt("digitcode"));

            Product product = mapToProduct(rset, existingProduct);

            foundProducts.put(rset.getInt("digitcode"), product);
        }

        return foundProducts.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
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
}
