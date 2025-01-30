package com.solvd.daos;

import com.solvd.models.account.Account;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IAccountDAO extends IDAO<Account,Long> {
    void createWithCustomerId(Account account, Long customerId);
}
