package com.example.module5dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class DatabaseInitService {
    public static void main(String[] args) {
        H2Database h2Database = H2Database.getInstance();
        String sqlFilePathInit = "src/main/resources/com/example/module5dev/init_db.sql";

        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePathInit))) {
            StringBuilder queryBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                queryBuilder.append(line);
                if (line.trim().endsWith(";")) {
                    String query = queryBuilder.toString();
                    h2Database.executeUpdate(query);
                    queryBuilder.setLength(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            H2Database.closeConnection();
        }
    }
}
