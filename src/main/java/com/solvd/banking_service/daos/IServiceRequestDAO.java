package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.ServiceRequest;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IServiceRequestDAO extends IDAO<ServiceRequest,Long> {
    void createWithCustomerId(ServiceRequest serviceRequest, Long customerId);
}
