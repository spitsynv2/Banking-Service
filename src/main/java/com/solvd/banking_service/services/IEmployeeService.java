package com.solvd.banking_service.services;

import com.solvd.banking_service.models.employee.Employee;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeService extends IService<Employee,Long> {
    Employee getAllEmployeeDataById(Long employeeId);
}
