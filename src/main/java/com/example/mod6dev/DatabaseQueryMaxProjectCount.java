package com.example.mod6dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueryMaxProjectCount {
        private static final String SELECT_MAX_PROJECT_COUNT_STRING =
                "SELECT COUNT(client_id) FROM project GROUP BY client_id ORDER BY COUNT(client_id) DESC LIMIT 1";

        private static final String SELECT_CLIENT_PROJECT_COUNT_STRING =
                "SELECT client.id, client.name, COUNT(project.client_id) AS project_count FROM client " +
                        "LEFT JOIN project ON client.id = project.client_id GROUP BY client.id " +
                        "HAVING COUNT(project.client_id) = ?";

        public PreparedStatement selectMaxProjectCountStatement;
        public PreparedStatement selectClientProjectCountStatement;

        public DatabaseQueryMaxProjectCount(Connection connection) {
            try {
                this.selectMaxProjectCountStatement = connection.prepareStatement(SELECT_MAX_PROJECT_COUNT_STRING);
                this.selectClientProjectCountStatement = connection.prepareStatement(SELECT_CLIENT_PROJECT_COUNT_STRING);
            } catch (SQLException e) {
                System.out.println("Database Query construction exception. Reason: " + e.getMessage());
            }
        }

        public int getMaxProjectCount() {
            int maxProjectCount = 0;
            try {
                ResultSet resultSet = selectMaxProjectCountStatement.executeQuery();
                if (resultSet.next()) {
                    maxProjectCount = resultSet.getInt(1);
                }
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error executing max project count query. Reason: " + e.getMessage());
            }
            return maxProjectCount;
        }

        public void queryClientProjectCount(int maxProjectCount) {
            try {
                selectClientProjectCountStatement.setInt(1, maxProjectCount);
                ResultSet resultSet = selectClientProjectCountStatement.executeQuery();
                while (resultSet.next()) {
                    int clientId = resultSet.getInt("id");
                    String clientName = resultSet.getString("name");
                    int projectCount = resultSet.getInt("project_count");
                    System.out.println("Client ID: " + clientId + ", Name: " + clientName + ", Project Count: " + projectCount);
                }
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Query client project count exception. Reason: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            Connection connection = PostgresDatabase.getInstance().getPostgresConnection();
            DatabaseQueryMaxProjectCount databaseQuery3 = new DatabaseQueryMaxProjectCount(connection);
            int maxProjectCount = databaseQuery3.getMaxProjectCount();
            databaseQuery3.queryClientProjectCount(maxProjectCount);
        }
    }

