package com.solvd.banking_service.services;

import com.solvd.banking_service.models.AuditLog;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IAuditLogService extends IService<AuditLog,Long>{

    List<AuditLog> getAllAuditLogDataByCustomerId(Long customerId);
}
