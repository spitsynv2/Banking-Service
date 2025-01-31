package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.customer.Address;


/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface IAddressDAO extends IDAO<Address,Long> {
    void createWithCustomerId(Address address, Long customerId);
}
