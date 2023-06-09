package com.example.module5dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery3 {
    private static final String SELECT_MONTHS_STRING =
            "SELECT id, " +
                    "(EXTRACT(YEAR FROM p.finish_date) - EXTRACT(YEAR FROM p.start_date)) * 12 " +
                    "+ (EXTRACT(MONTH FROM p.finish_date) - EXTRACT(MONTH FROM p.start_date)) AS months " +
                    "FROM project p " +
                    "WHERE (EXTRACT(YEAR FROM p.finish_date) - EXTRACT(YEAR FROM p.start_date)) * 12 " +
                    "+ (EXTRACT(MONTH FROM p.finish_date) - EXTRACT(MONTH FROM p.start_date)) IN (" +
                    "SELECT (EXTRACT(YEAR FROM p2.finish_date) - EXTRACT(YEAR FROM p2.start_date)) * 12 " +
                    "+ (EXTRACT(MONTH FROM p2.finish_date) - EXTRACT(MONTH FROM p2.start_date)) AS months " +
                    "FROM project p2 " +
                    "ORDER BY months DESC " +
                    "LIMIT 1)";

    private PreparedStatement selectMonthsStatement;

    public DatabaseQuery3(Connection connection) {
        try {
            this.selectMonthsStatement = connection.prepareStatement(SELECT_MONTHS_STRING);
        } catch (SQLException e) {
            System.out.println("Database Query construction exception. Reason: " + e.getMessage());
        }
    }

    public void queryMonths() {
        try {
            ResultSet resultSet = selectMonthsStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int months = resultSet.getInt("months");
                System.out.println("ID: " + id + ", Months: " + months);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Query months exception. Reason: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection connection = H2Database.getInstance().getH2Connection();
        DatabaseQuery3 databaseQuery3 = new DatabaseQuery3(connection);
        databaseQuery3.queryMonths();
    }
}

