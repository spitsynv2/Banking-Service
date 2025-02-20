package com.solvd.bankingservice.util.patterns;

import com.solvd.bankingservice.repo.IDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-20
 */
public class DAOFactory {
    private static final Logger log = LogManager.getLogger(DAOFactory.class);
    private static final String BASE_DAO_PACKAGE = "com.solvd.bankingservice.repo.impl";
    private static final String MYBATIS_PACKAGE_SUFFIX = ".mybatis.";
    private static final String MYSQL_PACKAGE_SUFFIX = ".mysql.";
    private static final String MYBATIS_CLASS_SUFFIX = "MyBatisImpl";
    private static final String MYSQL_CLASS_SUFFIX = "MySQLJdbcImpl";

    public static <T extends IDAO<?,?>> T createDao(Class<T> daoInterface, DAOType daoType) {
        String interfaceName = daoInterface.getSimpleName();

        if (interfaceName.startsWith("I")) {
            interfaceName = interfaceName.substring(1).split("DAO")[0];
        }

        String implName = "";
        if (daoType.equals(DAOType.MYBATIS)) {
            implName = BASE_DAO_PACKAGE + MYBATIS_PACKAGE_SUFFIX + interfaceName + MYBATIS_CLASS_SUFFIX;
        } else if (daoType.equals(DAOType.MYSQL)){
            implName = BASE_DAO_PACKAGE + MYSQL_PACKAGE_SUFFIX +  interfaceName + MYSQL_CLASS_SUFFIX;
        } else {
            log.warn("This DAO type {} is not supported", daoType);
            log.warn("Using default DAO type: {}", DAOType.MYSQL);
            implName = BASE_DAO_PACKAGE + MYSQL_PACKAGE_SUFFIX +  interfaceName + MYSQL_CLASS_SUFFIX;
            log.warn("Using default Implementation class: {}", implName);
        }

        try {
            Class<?> implClass = Class.forName(implName);
            return daoInterface.cast(implClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            log.error("Error creating DAO for {}", daoInterface.getName(), e);
            return null;
        }
    }
}
