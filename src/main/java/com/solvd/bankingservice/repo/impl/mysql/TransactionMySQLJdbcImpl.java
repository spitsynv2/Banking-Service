package com.solvd.bankingservice.repo.impl.mysql;

import com.solvd.bankingservice.repo.ITransactionDAO;
import com.solvd.bankingservice.model.account.Transaction;
import com.solvd.bankingservice.model.account.enums.transaction_enums.TransactionStatus;
import com.solvd.bankingservice.model.account.enums.transaction_enums.TransactionType;
import com.solvd.bankingservice.util.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */

public class TransactionMySQLJdbcImpl extends AbstractMySQLJdbcImpl<Transaction,Long> implements ITransactionDAO {

    private static final Logger log = LogManager.getLogger(TransactionMySQLJdbcImpl.class);

    private static final String READ_ALL_BY_FOREIGN_KEY_ID =
            "SELECT * FROM " + "transactions" + " WHERE from_account_id = ? OR to_account_id = ?";
    private static final String CREATE_WITH_ACCOUNT_ID =
            "INSERT INTO " + "transactions" + " (from_account_id, to_account_id, transaction_type, amount, " +
                    "transaction_date, description, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE " + "transactions" + " SET from_account_id = ?, to_account_id = ?, transaction_type = ?, " +
                    "amount = ?, transaction_date = ?, description = ?, status = ? WHERE Id = ?";


    public List<Transaction> readAllByForeignKeyId(Long foreignKeyId) {
        Connection connection = null;
        List<Transaction> transactionList = new ArrayList<>();

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(READ_ALL_BY_FOREIGN_KEY_ID)) {
                stmt.setLong(1, foreignKeyId);
                stmt.setLong(2, foreignKeyId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        transactionList.add(mapResultSetToEntity(rs));
                    }
                }
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
            return null;
        }finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        if (!transactionList.isEmpty()) {
            log.info("{}List: {} were successfully readAllByIdentifier from database table: {}",
                    transactionList.getFirst().getClass().getSimpleName(), transactionList, getTableName());
        }else {
            log.info("No records found in database table: {}", getTableName());
        }
        return transactionList;
    }

    @Override
    public void createWithAccountId(Transaction transaction, Long accountId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_ACCOUNT_ID)) {
                //stmt.setLong(1, accountId);
                stmt.setLong(1,transaction.getFromAccountId());
                stmt.setLong(2, transaction.getToAccountId());
                stmt.setString(3,transaction.getTransactionType().toString().toUpperCase());
                stmt.setDouble(4,transaction.getAmount());
                stmt.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDate()));
                stmt.setString(6,transaction.getDescription());
                stmt.setString(7,transaction.getTransactionStatus().toString().toUpperCase());
                stmt.executeUpdate();
                log.info("Transaction was created/inserted successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(Transaction entity) {
        log.error("Method is not implemented in TransactionMySQLJdbcImpl, Use ---> createWithAccountId");
    }

    @Override
    public void update(Transaction transaction) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setLong(1, transaction.getFromAccountId());
                stmt.setLong(2, transaction.getToAccountId());
                stmt.setString(3,transaction.getTransactionType().toString().toUpperCase());
                stmt.setDouble(4,transaction.getAmount());
                stmt.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDate()));
                stmt.setString(6,transaction.getDescription());
                stmt.setString(7,transaction.getTransactionStatus().toString().toUpperCase());
                stmt.setLong(8,transaction.getId());
                stmt.executeUpdate();
                log.info("Transaction was Updated successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    protected String getTableName() {
        return "transactions";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "from_account_id";
    }

    @Override
    protected Transaction mapResultSetToEntity(ResultSet rs) {
        Transaction transaction = new Transaction();
        try {
            transaction.setId(rs.getLong("Id"));
            transaction.setFromAccountId(rs.getLong("from_account_id"));
            transaction.setToAccountId(rs.getLong("to_account_id"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());
            transaction.setDescription(rs.getString("description"));

            TransactionType transactionType = TransactionType.valueOf(rs.getString("transaction_type").toUpperCase());
            TransactionStatus transactionStatus = TransactionStatus.valueOf(rs.getString("status").toUpperCase());
            transaction.setTransactionType(transactionType);
            transaction.setTransactionStatus(transactionStatus);
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }

        return transaction;
    }
}
