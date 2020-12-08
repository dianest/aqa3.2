package ru.netology.db;

import java.sql.*;

public final class DbUtils {
    private DbUtils() {

    }

    public static void clearTables() {
        try {
            final String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            final String url = "jdbc:mysql://localhost:3306/app?autoReconnect=true&useSSL=false";
            final String username = "app";
            String password = "pass";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DELETE FROM cards");
                    statement.executeUpdate("DELETE FROM auth_codes");
                    statement.executeUpdate("DELETE FROM card_transactions");
                    statement.executeUpdate("DELETE FROM users");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
    }

    public static String getAuthCode(String login) {
        try {
            final String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            final String url = "jdbc:mysql://localhost:3306/app?autoReconnect=true&useSSL=false";
            final String username = "app";
            String password = "pass";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                try (Statement statement = connection.createStatement()) {
                    final ResultSet userIdResult = statement.executeQuery(
                            "SELECT * FROM users WHERE login='" + login + "'");
                    if (userIdResult.first()) {
                        final String userId = userIdResult.getString("id");
                        final ResultSet authCodeResult = statement.executeQuery(
                                "SELECT * FROM auth_codes WHERE user_id='" + userId + "'");
                        if (authCodeResult.last()) {
                            return authCodeResult.getString("code");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }

        return "";
    }
}
