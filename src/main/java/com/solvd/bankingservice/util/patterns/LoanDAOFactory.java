package com.solvd.bankingservice.util.patterns;

import com.solvd.bankingservice.repo.ILoanDAO;
import com.solvd.bankingservice.repo.impl.mybatis.impl.LoanMyBatisImpl;
import com.solvd.bankingservice.repo.impl.mysql.LoanMySQLJdbcImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-14
 */
public class LoanDAOFactory {
    private static final Logger log = LogManager.getLogger(LoanDAOFactory.class);

    public static ILoanDAO getDAO(DAOType daoType) {
        if (daoType.equals(DAOType.JDBC)){
            return new LoanMySQLJdbcImpl();
        }else if (daoType.equals(DAOType.MYBATIS)){
            return new LoanMyBatisImpl();
        }else {
            log.warn("This DAO type {} is not supported", daoType);
            log.warn("Returning default DAO type: {}", DAOType.JDBC);
            return new LoanMySQLJdbcImpl();
        }
    }
}
