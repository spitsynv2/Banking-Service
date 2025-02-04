package com.solvd.bankingservice.daos;

import com.solvd.bankingservice.models.employee.Employee;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IEmployeeDAO extends IDAO<Employee,Long> {
    boolean checkPhoneNumberExists(String phoneNumber);
    boolean checkEmailExists(String email);
}
