package com.solvd.bankingservice.service;

import com.solvd.bankingservice.model.employee.Employee;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeService extends IService<Employee,Long> {
    Employee getAllEmployeeDataById(Long employeeId);
}
