package com.example.mod6dev;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class DatabasePopulateService {
    private static final String INSERT1_STRING = "INSERT INTO worker(id, name, birthday, salary, levels) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT2_STRING = "INSERT INTO client (id,name) VALUES (?, ?)";
    private static final String INSERT3_STRING = "INSERT INTO project (id, client_id,start_date,finish_date) VALUES (?, ?, ?, ?)";
    private static final String INSERT4_STRING = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)";
    private PreparedStatement insertStatement1;
    private PreparedStatement insertStatement2;
    private PreparedStatement insertStatement3;
    private PreparedStatement insertStatement4;

    public DatabasePopulateService(Connection connection) {
        try {
            this.insertStatement1 = connection.prepareStatement(INSERT1_STRING);
            this.insertStatement2 = connection.prepareStatement(INSERT2_STRING);
            this.insertStatement3 = connection.prepareStatement(INSERT3_STRING);
            this.insertStatement4 = connection.prepareStatement(INSERT4_STRING);
        } catch (SQLException e) {
            System.out.println("Database Populate construction exception. Reason: " + e.getMessage());
        }
    }

    public void saveWorker(Long id, String name, LocalDate birthday, int salary, String levels) {
        try {
            this.insertStatement1.setLong(1, id);
            this.insertStatement1.setString(2, name);
            this.insertStatement1.setDate(3, Date.valueOf(birthday.toString()));
            this.insertStatement1.setInt(4, salary);
            this.insertStatement1.setString(5, levels);
            this.insertStatement1.addBatch();
            this.insertStatement1.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert worker exception. Reason: " + e.getMessage());
        }
    }

    public void saveClient(Long id, String name) {
        try {
            this.insertStatement2.setLong(1, id);
            this.insertStatement2.setString(2, name);
            this.insertStatement2.addBatch();
            this.insertStatement2.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert client exception. Reason: " + e.getMessage());
        }
    }

    public void saveProject(Long id, Long client_id, LocalDate start_date, LocalDate finish_date) {
        try {
            this.insertStatement3.setLong(1, id);
            this.insertStatement3.setLong(2, client_id);
            this.insertStatement3.setDate(3, Date.valueOf(start_date.toString()));
            this.insertStatement3.setDate(4, Date.valueOf(finish_date.toString()));
            this.insertStatement3.addBatch();
            this.insertStatement3.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert project exception. Reason: " + e.getMessage());
        }
    }

    public void saveProject_worker(Long project_id, Long worker_id) {
        try {
            this.insertStatement4.setLong(1, project_id);
            this.insertStatement4.setLong(2, worker_id);
            this.insertStatement4.addBatch();
            this.insertStatement4.executeBatch();
        } catch (SQLException e) {
            System.out.println("Insert project_worker exception. Reason: " + e.getMessage());
        }
    }
}