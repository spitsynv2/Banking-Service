package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.models.employee.EmployeeRole;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeRoleDAO extends IDAO<EmployeeRole,Long> {
    void createWithEmployeeId(EmployeeRole employeeRole, Long employeeId);
    void updateWithEmployeeId(EmployeeRole employeeRole, Long employeeId);
    List<EmployeeRole> readAllByForeignKeyId(Long id);
}
