package com.example.mod6dev;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDatabase {
    private static final PostgresDatabase INSTANCE = new PostgresDatabase();

    private  Connection PostgresConnection;
    private PostgresDatabase () {

        try {
            String postgresConnectionUrl = Property.getConnectionUrlForPostgres();
            String username = Property.getUserForPostgres();
            String password = Property.getPasswordForPostgres();
            if (postgresConnectionUrl != null) {
                this.PostgresConnection = DriverManager.getConnection(postgresConnectionUrl, username, password);
            }
            flywayMigration(postgresConnectionUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Create connection exception");
        }
    }

    public static PostgresDatabase getInstance() {
        return INSTANCE;
    }

    public Connection getPostgresConnection() {
        return PostgresConnection;
    }

    public int executeUpdate(String query) {
        try(Statement statement = INSTANCE.getPostgresConnection().createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            PostgresConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void flywayMigration(String connectionUrl, String username, String password) {
        Flyway flyway = Flyway.configure().dataSource(connectionUrl, username, password).load();
        flyway.migrate();
    }
}

