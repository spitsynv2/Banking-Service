package com.solvd.bankingservice.daos;

import com.solvd.bankingservice.models.account.Deposit;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IDepositDAO extends IDAO<Deposit,Long> {
    void createWithAccountId(Deposit deposit, Long accountId);
    List<Deposit> readAllByForeignKeyId(Long id);
}
