package com.solvd.bankingservice.service;

import com.solvd.bankingservice.model.ServiceRequest;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IServiceRequestService extends IService<ServiceRequest,Long> {
    void createWithCustomerId(ServiceRequest serviceRequest,Long customerId);
    List<ServiceRequest> readAllByForeignKeyId(Long customerId);
}
