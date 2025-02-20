package com.solvd.bankingservice.repo.impl.mybatis;

import com.solvd.bankingservice.model.account.Transaction;
import com.solvd.bankingservice.repo.ITransactionDAO;
import com.solvd.bankingservice.util.MyBatisLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-14
 */
public class TransactionMyBatisImpl extends AbstractMyBatisImpl<Transaction,Long> implements ITransactionDAO {
    private static final Logger log = LogManager.getLogger(TransactionMyBatisImpl.class);

    @Override
    public void createWithAccountId(Transaction transaction, Long accountId) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            ITransactionDAO mapper = session.getMapper(ITransactionDAO.class);

            mapper.createWithAccountId(transaction, accountId);
            session.commit();
            log.info("Successfully created transaction with accountId: {} and transactionId: {}", accountId, transaction.getId());
        } catch (Exception e) {
            log.error("Error creating transaction with accountId: {}", accountId, e);
        }
    }

    @Override
    protected Class<?> getMapperClass() {
        return ITransactionDAO.class;
    }

    @Override
    protected String getTableName() {
        return "transactions";
    }
}
