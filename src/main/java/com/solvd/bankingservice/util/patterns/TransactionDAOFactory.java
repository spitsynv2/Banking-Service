package com.solvd.bankingservice.util.patterns;

import com.solvd.bankingservice.repo.ITransactionDAO;
import com.solvd.bankingservice.repo.impl.mybatis.impl.TransactionMyBatisImpl;
import com.solvd.bankingservice.repo.impl.mysql.TransactionMySQLJdbcImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-14
 */
public class TransactionDAOFactory {
    private static final Logger log = LogManager.getLogger(TransactionDAOFactory.class);

    public static ITransactionDAO getDAO(DAOType daoType) {
        if (daoType.equals(DAOType.JDBC)){
            return new TransactionMySQLJdbcImpl();
        }else if (daoType.equals(DAOType.MYBATIS)){
            return new TransactionMyBatisImpl();
        }else {
            log.warn("This DAO type {} is not supported", daoType);
            log.warn("Returning default DAO type: {}", DAOType.JDBC);
            return new TransactionMySQLJdbcImpl();
        }
    }
}
