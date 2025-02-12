package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.models.account.Loan;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ILoanDAO extends IDAO<Loan,Long>{
    void createWithAccountId(Loan loan, Long accountId);
    List<Loan> readAllByForeignKeyId(Long id);
}
