package com.solvd.bankingservice.util.patterns;

import com.solvd.bankingservice.repo.IAppointmentDAO;
import com.solvd.bankingservice.repo.IServiceRequestDAO;
import com.solvd.bankingservice.repo.impl.mybatis.impl.AppointmentMyBatisImpl;
import com.solvd.bankingservice.repo.impl.mybatis.impl.ServiceRequestMyBatisImpl;
import com.solvd.bankingservice.repo.impl.mysql.AppointmentMySQLJdbcImpl;
import com.solvd.bankingservice.repo.impl.mysql.ServiceRequestMySQLJdbcImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public class ServiceRequestDAOFactory {
    private static final Logger log = LogManager.getLogger(ServiceRequestDAOFactory.class);

    public static IServiceRequestDAO getDAO(DAOType daoType) {
        if (daoType.equals(DAOType.JDBC)){
            return new ServiceRequestMySQLJdbcImpl();
        }else if (daoType.equals(DAOType.MYBATIS)){
            return  new ServiceRequestMyBatisImpl();
        }else {
            log.warn("This DAO type {} is not supported", daoType);
            log.warn("Returning default DAO type: {}", DAOType.JDBC);
            return new ServiceRequestMySQLJdbcImpl();
        }
    }
}
