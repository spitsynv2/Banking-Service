package com.solvd.daos;

import com.solvd.models.account.Transaction;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ITransactionDAO extends IDAO<Transaction, Long> {
    void createWithAccountId(Transaction transaction, Long accountId);
}
