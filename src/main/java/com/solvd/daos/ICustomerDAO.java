package com.solvd.daos;

import com.solvd.models.customer.Customer;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
public interface ICustomerDAO extends IDAO<Customer, Long> {
    Customer readByEmail(String email);
    boolean checkEmailExists(String email);
    boolean checkTaxIdExists(String taxId);
}
