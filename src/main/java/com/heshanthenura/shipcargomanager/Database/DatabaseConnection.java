package com.heshanthenura.shipcargomanager.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:h2:./src/main/resources/data.db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    static Logger logger = Logger.getLogger("logger");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
