package com.solvd.services;

import io.github.cdimascio.dotenv.Dotenv;
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


public class MyConnectionPool {
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources/sql_resources").filename("database_config.env").load();
    private static final Logger logger = LogManager.getLogger(MyConnectionPool.class);
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    private static final int SIZE = 1;
    private static final MyConnectionPool instance = new MyConnectionPool();
    private static ArrayBlockingQueue<Connection> connections ;

    private MyConnectionPool(){
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

    public static MyConnectionPool getInstance() {
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
