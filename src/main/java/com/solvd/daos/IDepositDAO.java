package com.solvd.daos;

import com.solvd.models.account.Deposit;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IDepositDAO extends IDAO<Deposit,Long> {
    void createWithAccountId(Deposit deposit, Long accountId);
}
