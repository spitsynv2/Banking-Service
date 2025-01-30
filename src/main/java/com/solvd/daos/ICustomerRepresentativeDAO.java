package com.solvd.daos;

import com.solvd.models.customer.CustomerRepresentative;


/**
 * @author Vadym Spitsyn
 * @created 2025-01-29
 */
public interface ICustomerRepresentativeDAO extends IDAO<CustomerRepresentative, Long> {
    void createWithCompanyId(CustomerRepresentative representative,Long companyId);
    boolean checkEmailExists(String email);
}
