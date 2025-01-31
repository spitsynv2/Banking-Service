package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.account.Account;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IAccountDAO extends IDAO<Account,Long> {
    void createWithCustomerId(Account account, Long customerId);
}
