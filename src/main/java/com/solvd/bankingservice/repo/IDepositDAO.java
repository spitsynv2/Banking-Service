package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.account.Deposit;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IDepositDAO extends IDAO<Deposit,Long> {
    void createWithAccountId(Deposit deposit, Long accountId);
    List<Deposit> readAllByForeignKeyId(Long id);
}
