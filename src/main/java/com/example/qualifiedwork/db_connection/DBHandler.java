package com.example.qualifiedwork.db_connection;

import java.sql.*;

public class DBHandler {
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/med_system", "root", "");
        return connection;
    }
}
