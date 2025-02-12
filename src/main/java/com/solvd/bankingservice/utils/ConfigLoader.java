package com.solvd.bankingservice.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-12
 */
public class ConfigLoader {

    private static final Logger log = LogManager.getLogger(ConfigLoader.class);
    private static final Properties properties = new Properties();

    private ConfigLoader() {}

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties.example")) {
            if (input == null) {
                log.error("config.properties file not found.");
                throw new RuntimeException("config.properties file not found.");
            }
            properties.load(input);
        } catch (IOException e) {
            log.error("Error loading config.properties", e);
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
