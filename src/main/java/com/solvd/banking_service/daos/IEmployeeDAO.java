package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.employee.Employee;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeDAO extends IDAO<Employee,Long> {
    boolean checkPhoneNumberExists(String phoneNumber);
    boolean checkEmailExists(String email);
}
