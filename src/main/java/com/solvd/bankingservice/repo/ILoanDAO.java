package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.account.Loan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface ILoanDAO extends IDAO<Loan,Long>, IDAOCommonUtility<Loan,Long>{
    void createWithAccountId(@Param("loan")Loan loan,
                             @Param("accountId")Long accountId);
}
