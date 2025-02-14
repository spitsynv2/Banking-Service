package com.solvd.bankingservice.util.patterns;

import com.solvd.bankingservice.repo.IDepositDAO;
import com.solvd.bankingservice.repo.impl.mybatis.impl.DepositMyBatisImpl;
import com.solvd.bankingservice.repo.impl.mysql.DepositMySQLJdbcImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-14
 */
public class DepositDAOFactory {
    private static final Logger log = LogManager.getLogger(DepositDAOFactory.class);

    public static IDepositDAO getDAO(DAOType daoType) {
        if (daoType.equals(DAOType.JDBC)){
            return new DepositMySQLJdbcImpl();
        }else if (daoType.equals(DAOType.MYBATIS)){
            return new DepositMyBatisImpl();
        }else {
            log.warn("This DAO type {} is not supported", daoType);
            log.warn("Returning default DAO type: {}", DAOType.JDBC);
            return new DepositMySQLJdbcImpl();
        }
    }
}
