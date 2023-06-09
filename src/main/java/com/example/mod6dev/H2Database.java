package com.example.module5dev;

import java.sql.*;
import java.util.Objects;
public class H2Database {
        private static final H2Database INSTANCE = new H2Database();
        private static Connection h2Connection;
        private H2Database(){
            try{
                h2Connection = DriverManager.getConnection(Objects.requireNonNull(Property.getConnectionUrlForH2()));
            }catch (SQLException e){
                throw new RuntimeException("Create connection exception");
            }
        }
        public static H2Database getInstance(){
            return INSTANCE;
        }
        public Connection getH2Connection(){
            return h2Connection;
        }
        public int executeUpdate(String query) {
            int result;
            try (Statement statement = INSTANCE.getH2Connection().createStatement()) {
                result = statement.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return result;
        }
        public ResultSet executeQuery(String query) {
            try (Statement statement = INSTANCE.getH2Connection().createStatement()) {
                return statement.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public static void closeConnection(){
            try {
                h2Connection.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }