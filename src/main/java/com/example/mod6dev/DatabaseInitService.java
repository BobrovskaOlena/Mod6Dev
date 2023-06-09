package com.example.mod6dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;


public class DatabaseInitService{
        public static void main (String[] args) {
            Connection postgresConnection = PostgresDatabase.getInstance().getPostgresConnection();
            DatabasePopulateService databasePopulateService = new DatabasePopulateService(postgresConnection);
        }}
