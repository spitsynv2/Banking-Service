package com.solvd.banking_service.services.mysql_dao_communications_services;

import com.solvd.banking_service.daos.IAuditLogDAO;
import com.solvd.banking_service.daos.IDAO;
import com.solvd.banking_service.daos.myqsl_impl.AuditLogDAOImpl;
import com.solvd.banking_service.models.AuditLog;
import com.solvd.banking_service.services.AbstractService;
import com.solvd.banking_service.services.IAuditLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class AuditLogService extends AbstractService<AuditLog,Long> implements IAuditLogService {

    private static final Logger log = LogManager.getLogger(AuditLogService.class);
    private final IAuditLogDAO auditLogDAO = new AuditLogDAOImpl();

    @Override
    public List<AuditLog> getAllAuditLogDataByCustomerId(Long customerId) {
        List<AuditLog> auditLogs = auditLogDAO.readAllByForeignKeyId(customerId);

        if (auditLogs.isEmpty()) {
            log.error("Cannot find AuditLogs for customer with Id: {}", customerId);
            return null;
        }
        return auditLogs;
    }

    @Override
    protected IDAO<AuditLog, Long> getDao() {
        return auditLogDAO;
    }
}
