package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.ServiceRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IServiceRequestDAO extends IDAO<ServiceRequest,Long>, IDAOCommonUtility<ServiceRequest,Long> {
    void createWithCustomerId(@Param("serviceRequest") ServiceRequest serviceRequest,
                              @Param("customerId") Long customerId);
}
