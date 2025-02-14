package com.solvd.bankingservice.repo.impl.mybatis.impl;

import com.solvd.bankingservice.model.account.Deposit;
import com.solvd.bankingservice.repo.IDepositDAO;
import com.solvd.bankingservice.util.MyBatisLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-14
 */
public class DepositMyBatisImpl extends AbstractMyBatisImpl<Deposit,Long> implements IDepositDAO {
    private static final Logger log = LogManager.getLogger(DepositMyBatisImpl.class);

    @Override
    public void createWithAccountId(Deposit deposit, Long accountId) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            IDepositDAO mapper = session.getMapper(IDepositDAO.class);

            mapper.createWithAccountId(deposit, accountId);
            session.commit();
            log.info("Successfully created deposit with accountId: {} and depositId: {}", accountId, deposit.getId());
        } catch (Exception e) {
            log.error("Error creating deposit with accountId: {}", accountId, e);
        }
    }

    @Override
    protected Class<?> getMapperClass() {
        return IDepositDAO.class;
    }

    @Override
    protected String getTableName() {
        return "deposits";
    }
}
