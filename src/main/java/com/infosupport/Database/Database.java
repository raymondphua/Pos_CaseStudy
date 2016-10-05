package com.infosupport.Database;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

/**
 * Created by Raymond Phua on 3-10-2016.
 */
public abstract class Database {

    protected static Connection conn;
    //protected static ProductRepository productRepository = new ProductRepository();

//    public Database() {
////        for (Product product : getAllProducts()) {
////            System.out.println(product.getDigitCode());
////        }
//    }

    protected static void getConnection() {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:phua/admin@localhost:1521/XE");
            conn = ods.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    public static ResultSet getAllBooks() throws SQLException {
//        getConnection();
//
//        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
//        String query = "SELECT * FROM BOOKS ORDER BY book_id";
//
//        System.out.println("\nExecuting query: " + query);
//        ResultSet rset = stmt.executeQuery(query);
//
//        stmt.close();
//        return rset;
//    }

//    public static ProductRepository getProductRepository() {
//        return productRepository;
//    }
}
