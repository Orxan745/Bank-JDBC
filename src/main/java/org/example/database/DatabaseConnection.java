package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/bank_customer";
    private static final String username = "postgres";
    private static final String password = "3459095";

    public static Connection getDatabaseConnection(){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e){
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }
}