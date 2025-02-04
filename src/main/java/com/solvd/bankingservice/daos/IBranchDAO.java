package com.solvd.bankingservice.daos;

import com.solvd.bankingservice.models.employee.Branch;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-03
 */
public interface IBranchDAO extends IDAO<Branch,Long> {
    void createWithEmployeeId(Branch branch, Long employeeId);
    void updateWithEmployeeId(Branch branch, Long employeeId);
    List<Branch> readAllByForeignKeyId(Long id);
}
