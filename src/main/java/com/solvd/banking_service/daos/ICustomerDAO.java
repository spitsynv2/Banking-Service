package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.customer.Customer;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
public interface ICustomerDAO extends IDAO<Customer, Long> {
    Customer readByEmail(String email);
    boolean checkEmailExists(String email);
    boolean checkTaxIdExists(String taxId);
}
