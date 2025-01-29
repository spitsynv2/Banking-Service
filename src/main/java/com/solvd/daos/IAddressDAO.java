package com.solvd.daos;

import com.solvd.models.customer.Address;


/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface IAddressDAO extends IDAO<Address,Long> {
    void createWithCustomerId(Address address, Long customerId);
}
