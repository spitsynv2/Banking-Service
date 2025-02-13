package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.customer.Address;

import java.util.List;


/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface IAddressDAO extends IDAO<Address,Long> {
    void createWithCustomerId(Address address, Long customerId);
    List<Address> readAllByForeignKeyId(Long id);
}
