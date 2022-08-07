package controller;

import java.sql.*;

public class DatabaseConnection {
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";

    public static Connection getConnection(String database) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL + database, USERNAME, PASSWORD);
            System.out.println("Successful connection to " + database + " database");
            return connection;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}