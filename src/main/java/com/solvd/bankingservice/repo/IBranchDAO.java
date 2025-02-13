package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.employee.Branch;

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
