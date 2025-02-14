package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.account.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ITransactionDAO extends IDAO<Transaction, Long>, IDAOCommonUtility<Transaction,Long> {
    void createWithAccountId(@Param("transaction") Transaction transaction,
                             @Param("accountId") Long accountId);
}
