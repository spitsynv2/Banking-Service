package com.solvd.bankingservice.service.mysql;

import com.solvd.bankingservice.repo.IAuditLogDAO;
import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.repo.impl.mysql.AuditLogMySQLJdbcImpl;
import com.solvd.bankingservice.model.AuditLog;
import com.solvd.bankingservice.service.AbstractService;
import com.solvd.bankingservice.service.IAuditLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class AuditLogService extends AbstractService<AuditLog,Long> implements IAuditLogService {

    private static final Logger log = LogManager.getLogger(AuditLogService.class);
    private final IAuditLogDAO auditLogDAO = new AuditLogMySQLJdbcImpl();

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
