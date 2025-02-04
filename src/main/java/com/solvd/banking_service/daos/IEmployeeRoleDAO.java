package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.employee.EmployeeRole;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeRoleDAO extends IDAO<EmployeeRole,Long> {
    void createWithEmployeeId(EmployeeRole employeeRole, Long employeeId);
    void updateWithEmployeeId(EmployeeRole employeeRole, Long employeeId);
}
