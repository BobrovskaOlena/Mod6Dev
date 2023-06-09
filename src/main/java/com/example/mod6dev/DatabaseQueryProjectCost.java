package com.example.mod6dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueryProjectCost {
        public String SELECT_PROJECT_COST_STRING = "SELECT project.id, SUM(worker.salary * (EXTRACT(YEAR FROM project.finish_date) - EXTRACT(YEAR FROM project.start_date)) * 12 + " +
                "(EXTRACT(MONTH FROM project.finish_date) - EXTRACT(MONTH FROM project.start_date))) AS project_cost" +
                " FROM project" +
                " JOIN project_worker ON project.id = project_worker.project_id" +
                " JOIN worker ON project_worker.worker_id = worker.id" +
                " GROUP BY project.id" +
                " ORDER BY project_cost DESC;";

        public PreparedStatement selectProjectCostStatement;

        public DatabaseQueryProjectCost(Connection connection) {
            try {
                this.selectProjectCostStatement = connection.prepareStatement(SELECT_PROJECT_COST_STRING);
            } catch (SQLException e) {
                System.out.println("Database Query construction exception. Reason: " + e.getMessage());
            }
        }

        public void queryProjectCost() {
            try {
                ResultSet resultSet = selectProjectCostStatement.executeQuery();
                while (resultSet.next()) {
                    int projectId = resultSet.getInt("id");
                    double projectCost = resultSet.getDouble("project_cost");
                    System.out.println("Project ID: " + projectId + ", Project Cost: " + projectCost);
                }
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Query project cost exception. Reason: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            Connection connection = PostgresDatabase.getInstance().getPostgresConnection();
            DatabaseQueryProjectCost databaseQuery5 = new DatabaseQueryProjectCost(connection);

            databaseQuery5.queryProjectCost();
        }
    }

