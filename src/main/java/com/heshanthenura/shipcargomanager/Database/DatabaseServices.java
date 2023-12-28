package com.heshanthenura.shipcargomanager.Database;

import com.heshanthenura.shipcargomanager.Components.Container;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseServices {

    static Logger logger = Logger.getLogger("logger");


    public static List<Container> retrieveContainersBySlot(int slotNumber) {
        List<Container> containers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM container_table WHERE slot_number = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, slotNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        int id = resultSet.getInt("id");
                        int slot = resultSet.getInt("slot_number");
                        Date arrivedDate = resultSet.getDate("arrived_date");
                        Date releaseDueDate = resultSet.getDate("release_due_date");
                        Date releasedDate = resultSet.getDate("released_date");
                        Color color = Color.valueOf(resultSet.getString("color"));
                        LocalDate arrivedLocalDate = (arrivedDate != null) ? arrivedDate.toLocalDate() : null;
                        LocalDate releaseDueLocalDate = (releaseDueDate != null) ? releaseDueDate.toLocalDate() : null;
                        LocalDate releasedLocalDate = (releasedDate != null) ? releasedDate.toLocalDate() : null;
                        Container container = new Container(id, slot, arrivedLocalDate, releaseDueLocalDate, releasedLocalDate,color);
                        containers.add(container);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return containers;
    }

    public void addContainer(int slotNumber, LocalDate arrivedDate, LocalDate releaseDueDate, LocalDate releasedDate, String color) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String insertQuery = "INSERT INTO container_table (slot_number, arrived_date, release_due_date, released_date, color) VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, slotNumber);
                    preparedStatement.setDate(2, arrivedDate != null ? Date.valueOf(arrivedDate) : null);
                    preparedStatement.setDate(3, releaseDueDate != null ? Date.valueOf(releaseDueDate) : null);
                    preparedStatement.setDate(4, releasedDate != null ? Date.valueOf(releasedDate) : null);
                    preparedStatement.setString(5, color); // Set the color

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        logger.info("Data inserted successfully into container_table!");
                    } else {
                        logger.warning("Failed to insert data into container_table!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContainerById(int containerId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String deleteQuery = "DELETE FROM container_table WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, containerId);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        logger.info("Container with ID " + containerId + " deleted successfully from container_table!");
                    } else {
                        logger.warning("No container found with ID " + containerId + " in container_table!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (Container c : retrieveContainersBySlot(1)){
            logger.info(c.toString());
        }
    }

    public void initTables() {
        createContainerTable();
        createSlotTable();
    }

    private void createContainerTable() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            logger.info(String.valueOf(connection));
            if (connection != null) {
                Statement statement = connection.createStatement();

                String createContainerTableQuery = "CREATE TABLE IF NOT EXISTS container_table ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "slot_number INT,"
                        + "arrived_date DATE,"
                        + "release_due_date DATE,"
                        + "released_date DATE,"
                        + "color VARCHAR(50)" // New column
                        + ")";

                statement.executeUpdate(createContainerTableQuery);
                logger.info("Container table created (if it didn't exist) successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createSlotTable() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            logger.info(String.valueOf(connection));
            if (connection != null) {
                Statement statement = connection.createStatement();

                String createSlotTableQuery = "CREATE TABLE IF NOT EXISTS slot_table ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "container_id INT,"
                        + "slot_number INT,"
                        + "FOREIGN KEY (container_id) REFERENCES container_table(id)"
                        + ")";

                statement.executeUpdate(createSlotTableQuery);
                logger.info("Slot table created (if it didn't exist) successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
