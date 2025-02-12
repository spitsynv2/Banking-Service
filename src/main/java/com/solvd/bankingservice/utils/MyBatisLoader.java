package com.solvd.bankingservice.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-12
 */
public class MyBatisLoader {

    private static final Logger log = LogManager.getLogger(MyBatisLoader.class);
    private static final SqlSessionFactory sqlSessionFactory;

    private MyBatisLoader() {}

    static {
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            log.error("Error initializing MyBatis", e);
            throw new RuntimeException("MyBatis initialization failed", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}