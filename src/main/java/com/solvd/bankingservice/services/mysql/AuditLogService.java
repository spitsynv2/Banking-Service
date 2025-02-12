package com.solvd.bankingservice.services.mysql;

import com.solvd.bankingservice.repo.IAuditLogDAO;
import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.repo.impl.mysql.AuditLogDAOImpl;
import com.solvd.bankingservice.models.AuditLog;
import com.solvd.bankingservice.services.AbstractService;
import com.solvd.bankingservice.services.IAuditLogService;
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
