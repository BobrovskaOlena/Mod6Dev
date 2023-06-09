package com.example.module5dev;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Property {
    public static String getConnectionUrlForH2() {
        try (InputStream input = Property.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);
            return "jdbc:" +
                    prop.getProperty("h2.db.host") +
                    ":./" +
                    prop.getProperty("h2.db.database");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
