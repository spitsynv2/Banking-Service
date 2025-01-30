package com.solvd.daos;

import com.solvd.models.account.Loan;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ILoanDAO extends IDAO<Loan,Long>{
    void createWithAccountId(Loan loan, Long accountId);
}
