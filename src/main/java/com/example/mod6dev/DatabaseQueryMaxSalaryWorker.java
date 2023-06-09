package com.example.mod6dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseQuery1 {

private static final String SELECT_MAX_SALARY_STRING = "SELECT ID, name, birthday, salary, levels FROM worker WHERE salary = ?";
private PreparedStatement selectMaxSalaryStatement;

public DatabaseQuery1(Connection connection) {
    try {
        this.selectMaxSalaryStatement = connection.prepareStatement(SELECT_MAX_SALARY_STRING);
    } catch (SQLException e) {
        System.out.println("Database Query construction exception. Reason: " + e.getMessage());
    }
}

public String queryMaxSalary() {
    StringBuilder result = new StringBuilder();
    try {
        ResultSet resultSet = this.selectMaxSalaryStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
            int salary = resultSet.getInt("salary");
            String levels = resultSet.getString("levels");
            result.append("ID: ").append(id)
                    .append(", Name: ").append(name)
                    .append(", Birthday: ").append(birthday)
                    .append(", Salary: ").append(salary)
                    .append(", Levels: ").append(levels)
                    .append("\n");
        }
    } catch (SQLException e) {
        System.out.println("Query max salary exception. Reason: " + e.getMessage());
    }
    return result.toString();
}

public static void main(String[] args) {
    Connection connection = H2Database.getInstance().getH2Connection();
    DatabaseQuery1 databaseQuery2 = new DatabaseQuery1(connection);

   //select 1
    try {
        PreparedStatement maxSalaryStatement = connection.prepareStatement("SELECT MAX(salary) FROM worker");
        ResultSet maxSalaryResultSet = maxSalaryStatement.executeQuery();
        if (maxSalaryResultSet.next()) {
            int maxSalary = maxSalaryResultSet.getInt(1);
            databaseQuery2.selectMaxSalaryStatement.setInt(1, maxSalary);
            String result = databaseQuery2.queryMaxSalary();
            System.out.println(result);
        }
        maxSalaryResultSet.close();
        maxSalaryStatement.close();
    } catch (SQLException e) {
        System.out.println("Error executing max salary query. Reason: " + e.getMessage());
    }
}
}