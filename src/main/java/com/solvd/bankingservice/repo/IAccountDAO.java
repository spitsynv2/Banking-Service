package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.models.account.Account;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IAccountDAO extends IDAO<Account,Long> {
    void createWithCustomerId(Account account, Long customerId);
    List<Account> readAllByForeignKeyId(Long id);
}
