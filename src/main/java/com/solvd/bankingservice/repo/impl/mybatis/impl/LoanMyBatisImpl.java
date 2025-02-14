package com.solvd.bankingservice.repo.impl.mybatis.impl;

import com.solvd.bankingservice.model.account.Loan;
import com.solvd.bankingservice.repo.ILoanDAO;
import com.solvd.bankingservice.util.MyBatisLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-14
 */
public class LoanMyBatisImpl extends AbstractMyBatisImpl<Loan,Long> implements ILoanDAO {
    private static final Logger log = LogManager.getLogger(LoanMyBatisImpl.class);

    @Override
    public void createWithAccountId(Loan loan, Long accountId) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            ILoanDAO mapper = session.getMapper(ILoanDAO.class);

            mapper.createWithAccountId(loan, accountId);
            session.commit();
            log.info("Successfully created loan with accountId: {} and loanId: {}", accountId, loan.getId());
        } catch (Exception e) {
            log.error("Error creating loan with accountId: {}", accountId, e);
        }
    }

    @Override
    protected Class<?> getMapperClass() {
        return ILoanDAO.class;
    }

    @Override
    protected String getTableName() {
        return "loans";
    }
}
