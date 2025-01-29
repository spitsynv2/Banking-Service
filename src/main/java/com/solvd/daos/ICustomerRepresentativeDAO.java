package com.solvd.daos;

import com.solvd.models.customer.CustomerRepresentative;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface ICustomerRepresentativeDAO extends IDAO<CustomerRepresentative, Long> {
    void createWithCompanyId(CustomerRepresentative representative,Long companyId);
    boolean checkEmailExists(String email);
}
