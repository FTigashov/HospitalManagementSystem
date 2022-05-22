package com.example.qualifiedwork;
import com.example.qualifiedwork.authenticaton.AdminLoginController;

import java.sql.*;

public class DBHandler {
    private Statement statement;
    private ResultSet resultSet;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/JavaProjects/QualifiedWork/src/main/resources/mainDB");
        return connection;
    }

}
