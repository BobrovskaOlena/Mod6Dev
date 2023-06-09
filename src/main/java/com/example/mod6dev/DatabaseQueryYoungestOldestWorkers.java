package com.example.mod6dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseQuery4 {
    private static final String SELECT_YOUNGEST_ELDEST_STRING =
            "SELECT 'OLDEST' AS TYPE, name, birthday FROM worker WHERE birthday = (SELECT MIN(birthday) FROM worker)" +
                    "UNION " +
                    "SELECT 'YOUNGEST' AS TYPE, name, birthday FROM worker WHERE birthday = (SELECT MAX(birthday) FROM worker)";

    private PreparedStatement selectYoungestOldestStatement;

    public DatabaseQuery4(Connection connection) {
        try {
            this.selectYoungestOldestStatement = connection.prepareStatement(SELECT_YOUNGEST_ELDEST_STRING);
        } catch (SQLException e) {
            System.out.println("Database Query construction exception. Reason: " + e.getMessage());
        }
    }

    public void queryYoungestOldest() {
        try {
            ResultSet resultSet = selectYoungestOldestStatement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString("TYPE");
                String name = resultSet.getString("name");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                System.out.println("Type: " + type + ", Name: " + name + ", Birthday: " + birthday);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Query youngest/oldest exception. Reason: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection connection = H2Database.getInstance().getH2Connection();
        DatabaseQuery4 databaseQuery4 = new DatabaseQuery4(connection);

        databaseQuery4.queryYoungestOldest();
    }
}
