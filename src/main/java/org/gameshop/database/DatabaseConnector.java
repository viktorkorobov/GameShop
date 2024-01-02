package org.gameshop.database;

import lombok.RequiredArgsConstructor;

import java.sql.*;
@RequiredArgsConstructor
public class DatabaseConnector {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

}
