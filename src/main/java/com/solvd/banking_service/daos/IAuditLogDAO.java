package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.AuditLog;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IAuditLogDAO extends IDAO<AuditLog,Long> {
    void createWithCustomerId(AuditLog auditLog, Long customerId);
}
