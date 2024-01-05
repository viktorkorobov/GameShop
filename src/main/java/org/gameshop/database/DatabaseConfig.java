package org.gameshop.database;


import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.*;

public class DatabaseConfig {
    private static final String CONFIG_FILE = "database.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            err.println("An error has occured: " + e.getMessage());
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("DB_URL");
    }

    public static String getDbUser() {
        return properties.getProperty("DB_USER");
    }

    public static String getDbPassword() {
        return properties.getProperty("DB_PASSWORD");
    }
}
