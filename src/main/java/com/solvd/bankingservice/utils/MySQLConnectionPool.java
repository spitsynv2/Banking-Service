package com.solvd.bankingservice.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-02
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;


public class MySQLConnectionPool {
    private static final Logger logger = LogManager.getLogger(MySQLConnectionPool.class);
    private static final String URL = ConfigLoader.getProperty("DB_URL");
    private static final String USER = ConfigLoader.getProperty("DB_USER");
    private static final String PASSWORD = ConfigLoader.getProperty("DB_PASSWORD");
    private static final int SIZE = 1;
    private static final MySQLConnectionPool instance = new MySQLConnectionPool();
    private static ArrayBlockingQueue<Connection> connections ;

    private MySQLConnectionPool(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connections = new ArrayBlockingQueue<>(SIZE);

            for (int i = 0; i < SIZE; i++) {
                connections.offer(DriverManager.getConnection(URL, USER, PASSWORD));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public static MySQLConnectionPool getInstance() {
        return instance;
    }

    public static Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.offer(connection);
        }
    }

    public static void closeAllConnections() {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        logger.info("Closing all connections");
    }
}
