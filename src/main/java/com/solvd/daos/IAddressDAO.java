package com.solvd.daos;

import com.solvd.models.customer.Address;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface IAddressDAO extends IDAO<Address,Long> {
    List<Address> readAllByCustomerId(Long customerId);
    void createWithCustomerId(Address address, Long customerId);
}
