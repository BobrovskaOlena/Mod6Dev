package com.example.mod6dev;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Property {
      public static String getConnectionUrlForPostgres() {
        try (InputStream input = Property.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return "jdbc:postgresql://" +
                    prop.getProperty("postgres.db.host") +
                    ":" +
                    prop.getProperty("postgres.db.port") +
                    "/" +
                    prop.getProperty("postgres.db.database");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getUserForPostgres() {
        try (InputStream input = Property.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("postgres.db.username");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getPasswordForPostgres() {
        try (InputStream input = Property.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("postgres.db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
