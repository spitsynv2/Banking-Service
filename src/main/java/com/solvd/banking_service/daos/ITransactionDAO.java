package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.account.Transaction;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ITransactionDAO extends IDAO<Transaction, Long> {
    void createWithAccountId(Transaction transaction, Long accountId);
}
