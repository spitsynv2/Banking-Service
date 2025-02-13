package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.ServiceRequest;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IServiceRequestDAO extends IDAO<ServiceRequest,Long> {
    void createWithCustomerId(ServiceRequest serviceRequest, Long customerId);
    List<ServiceRequest> readAllByForeignKeyId(Long id);
}
