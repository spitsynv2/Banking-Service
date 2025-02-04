package com.solvd.bankingservice.services;

import com.solvd.bankingservice.models.customer.Customer;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface ICustomerAccountService extends IService<Customer,Long> {
    Customer getAllCustomerDataById(Long customerId);
    void updateAll(Customer customer);
    void updateAddress(Customer customer);
}
