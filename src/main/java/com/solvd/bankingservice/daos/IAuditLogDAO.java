package com.solvd.bankingservice.daos;

import com.solvd.bankingservice.models.AuditLog;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IAuditLogDAO extends IDAO<AuditLog,Long> {
    void createWithCustomerId(AuditLog auditLog, Long customerId);
    List<AuditLog> readAllByForeignKeyId(Long id);
}
