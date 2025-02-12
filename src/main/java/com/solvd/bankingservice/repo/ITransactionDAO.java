package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.models.account.Transaction;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ITransactionDAO extends IDAO<Transaction, Long> {
    void createWithAccountId(Transaction transaction, Long accountId);
    List<Transaction> readAllByForeignKeyId(Long id);
}
