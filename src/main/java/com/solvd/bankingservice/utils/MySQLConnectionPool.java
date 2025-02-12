package com.solvd.bankingservice.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-02
 */
public class MySQLConnectionPool {
    private static final Logger logger = LogManager.getLogger(MySQLConnectionPool.class);
    private static final String URL = ConfigLoader.getProperty("DB_URL");
    private static final String USER = ConfigLoader.getProperty("DB_USER");
    private static final String PASSWORD = ConfigLoader.getProperty("DB_PASSWORD");
    private static final Integer SIZE = Integer.parseInt(ConfigLoader.getProperty("DB_POOL_SIZE"));
    private static final ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<>(SIZE);
    private static final MySQLConnectionPool instance = new MySQLConnectionPool();

    private MySQLConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < SIZE; i++) {
                connections.offer(DriverManager.getConnection(URL, USER, PASSWORD));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error in initialization of MySQLConnectionPool", e);
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public static MySQLConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.offer(connection);
        }
    }

    public void closeAllConnections() {

        while (connections.size() < SIZE) {
            try {
                logger.info("Waiting for all connections to be returned to the pool...");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.warn("Thread was interrupted during wait. Skipping closeAllConnections.");
                return;
            }
        }

        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection", e);
            }
        }

        logger.info("Closed all connections");
    }
}
