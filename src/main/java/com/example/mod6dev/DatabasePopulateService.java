package com.example.module5dev;
import java.sql.*;
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
        } catch(SQLException e) {
            System.out.println("Database Populate construction exception. Reason: " + e.getMessage());
        }
    }
    public void saveWorker(Long id, String name, LocalDate birthday, int salary, String levels) {
        try {
            this.insertStatement1.setLong(1, id);
            this.insertStatement1.setString(2, name);
            this.insertStatement1.setDate(3,java.sql.Date.valueOf(birthday.toString()));
            this.insertStatement1.setInt(4, salary);
            this.insertStatement1.setString(5, levels);
            this.insertStatement1.addBatch();
            this.insertStatement1.executeBatch();
        } catch(SQLException e) {
            System.out.println("Insert worker exception. Reason: " + e.getMessage());
        }
    }
    public void saveClient(Long id, String name) {
        try {
            this.insertStatement2.setLong(1, id);
            this.insertStatement2.setString(2, name);
            this.insertStatement2.addBatch();
            this.insertStatement2.executeBatch();
        } catch(SQLException e) {
            System.out.println("Insert client exception. Reason: " + e.getMessage());
        }
    }

    public void saveProject(Long id, Long client_id, LocalDate start_date, LocalDate finish_date) {
        try {
            this.insertStatement3.setLong(1, id);
            this.insertStatement3.setLong(2, client_id);
            this.insertStatement3.setDate(3, java.sql.Date.valueOf(start_date.toString()));
            this.insertStatement3.setDate(4, java.sql.Date.valueOf(finish_date.toString()));
            this.insertStatement3.addBatch();
            this.insertStatement3.executeBatch();
        } catch(SQLException e) {
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

    public static void main(String[] args) {

        Connection connection = H2Database.getInstance().getH2Connection();

        DatabasePopulateService databasePopulate2 = new DatabasePopulateService(connection);

        // INSERT INTO worker
        databasePopulate2.saveWorker(1L, "Anton Skliar", LocalDate.of(1992, 2, 5), 900, "Trainee");
        databasePopulate2.saveWorker(2L, "Mary Jonson", LocalDate.of(1996, 7, 5), 1200, "Junior");
        databasePopulate2.saveWorker(3L, "Daniil Bee", LocalDate.of(1998, 2, 21), 1500, "Middle");
        databasePopulate2.saveWorker(4L, "Olena Bobrovska", LocalDate.of(1998, 7, 28), 2500, "Middle");
        databasePopulate2.saveWorker(5L, "Daniel Ram", LocalDate.of(1999, 6, 5), 3600, "Senior");
        databasePopulate2.saveWorker(6L, "Karina Kostenko", LocalDate.of(1998, 2, 22), 3000, "Middle");
        databasePopulate2.saveWorker(7L, "Oleg Logenko", LocalDate.of(1996, 2, 5), 3100, "Middle");
        databasePopulate2.saveWorker(8L, "Alisa Borysenko", LocalDate.of(1994, 2, 10), 4000, "Senior");
        databasePopulate2.saveWorker(9L, "Tom Kristen", LocalDate.of(1988, 4, 23), 4500, "Senior");
        databasePopulate2.saveWorker(10L, "Olia Kristenko", LocalDate.of(1983, 9, 1), 5500, "Senior");

        // INSERT INTO client
        databasePopulate2.saveClient(1L, "Anna Smith");
        databasePopulate2.saveClient(2L, "Maria Jekson");
        databasePopulate2.saveClient(3L, "Anton Skliar");
        databasePopulate2.saveClient(4L, "Oleg Lagosh");
        databasePopulate2.saveClient(5L, "Viktoria Danysiuk");
        databasePopulate2.saveClient(6L, "Valeriya Rem");

        // INSERT INTO project
        databasePopulate2.saveProject(1L, 1L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 28));
        databasePopulate2.saveProject(2L, 2L, LocalDate.of(2021, 12, 1), LocalDate.of(2022, 3, 31));
        databasePopulate2.saveProject(3L, 3L, LocalDate.of(2022, 2, 15), LocalDate.of(2022, 6, 30));
        databasePopulate2.saveProject(4L, 4L, LocalDate.of(2021, 11, 15), LocalDate.of(2022, 2, 28));
        databasePopulate2.saveProject(5L, 5L, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 12, 31));
        databasePopulate2.saveProject(6L, 6L, LocalDate.of(2022, 3, 1), LocalDate.of(2022, 5, 31));
        databasePopulate2.saveProject(7L, 1L, LocalDate.of(2022, 7, 1), LocalDate.of(2023, 1, 31));
        databasePopulate2.saveProject(8L, 3L, LocalDate.of(2022, 5, 15), LocalDate.of(2022, 12, 31));
        databasePopulate2.saveProject(9L, 2L, LocalDate.of(2022, 9, 1), LocalDate.of(2023, 5, 31));
        databasePopulate2.saveProject(10L, 5L, LocalDate.of(2022, 8, 1), LocalDate.of(2022, 11, 30));

        // INSERT INTO project_worker
        databasePopulate2.saveProject_worker(1L, 1L);
        databasePopulate2.saveProject_worker(1L, 5L);
        databasePopulate2.saveProject_worker(2L, 2L);
        databasePopulate2.saveProject_worker(2L, 5L);
        databasePopulate2.saveProject_worker(2L, 7L);
        databasePopulate2.saveProject_worker(3L, 1L);
        databasePopulate2.saveProject_worker(3L, 4L);
        databasePopulate2.saveProject_worker(3L, 6L);
        databasePopulate2.saveProject_worker(3L, 7L);
        databasePopulate2.saveProject_worker(4L, 4L);
        databasePopulate2.saveProject_worker(4L, 2L);
        databasePopulate2.saveProject_worker(4L, 7L);
        databasePopulate2.saveProject_worker(5L, 1L);
        databasePopulate2.saveProject_worker(5L, 2L);
        databasePopulate2.saveProject_worker(5L, 8L);
        databasePopulate2.saveProject_worker(5L, 9L);
        databasePopulate2.saveProject_worker(5L, 7L);
        databasePopulate2.saveProject_worker(6L, 8L);
        databasePopulate2.saveProject_worker(6L, 9L);
        databasePopulate2.saveProject_worker(7L, 10L);
        databasePopulate2.saveProject_worker(7L, 1L);
        databasePopulate2.saveProject_worker(7L, 4L);
        databasePopulate2.saveProject_worker(7L, 8L);
        databasePopulate2.saveProject_worker(8L, 8L);
        databasePopulate2.saveProject_worker(8L, 3L);
        databasePopulate2.saveProject_worker(8L, 7L);
        databasePopulate2.saveProject_worker(9L, 2L);
        databasePopulate2.saveProject_worker(9L, 4L);
        databasePopulate2.saveProject_worker(9L, 5L);
        databasePopulate2.saveProject_worker(9L, 8L);
        databasePopulate2.saveProject_worker(10L, 1L);
        databasePopulate2.saveProject_worker(10L, 4L);
        databasePopulate2.saveProject_worker(10L, 5L);
        databasePopulate2.saveProject_worker(10L, 7L);
        databasePopulate2.saveProject_worker(10L, 9L);

    }
}