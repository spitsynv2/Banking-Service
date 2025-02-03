package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.employee.Branch;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-03
 */
public interface IBranchDAO extends IDAO<Branch,Long> {
    void createWithEmployeeId(Branch branch, Long employeeId);
}
