package org.gameshop.database;

import lombok.RequiredArgsConstructor;

import java.sql.*;
@RequiredArgsConstructor
public class DatabaseConnector {
    private Connection connection;

    public Connection getConnection() throws SQLException {
        String url = DatabaseConfig.getDbUrl();
        String user = DatabaseConfig.getDbUser();
        String password = DatabaseConfig.getDbPassword();
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
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
