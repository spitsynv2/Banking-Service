package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.account.Deposit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public interface IDepositDAO extends IDAO<Deposit,Long>, IDAOCommonUtility<Deposit,Long> {
    void createWithAccountId(@Param("deposit")Deposit deposit,
                             @Param("accountId")Long accountId);
}
