package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.employee.Employee;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeDAO extends IDAO<Employee,Long> {
    boolean checkPhoneNumberExists(String phoneNumber);
    boolean checkEmailExists(String email);
}
