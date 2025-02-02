package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.ITransactionDAO;
import com.solvd.banking_service.models.account.Transaction;
import com.solvd.banking_service.models.account.enums.transaction_enums.TransactionStatus;
import com.solvd.banking_service.models.account.enums.transaction_enums.TransactionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */

public class TransactionDAOImpl extends MYSQLImpl<Transaction,Long> implements ITransactionDAO {

    private static final Logger log = LogManager.getLogger(TransactionDAOImpl.class);

    public TransactionDAOImpl(Connection connection) {
        super(connection);
    }

    public List<Transaction> readAllByForeignKeyId(Long foreignKeyId) {
        List<Transaction> transactionList = new ArrayList<>();

        String sql = "SELECT * FROM " + getTableName() + " WHERE from_account_id = ? OR to_account_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, foreignKeyId);
            stmt.setLong(2, foreignKeyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactionList.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            log.error(e);
            return null;
        }

        log.info("Items: {} were successfully readAllByIdentifier from database table {}", transactionList, getTableName());
        return transactionList;
    }

    @Override
    public void createWithAccountId(Transaction transaction, Long accountId) {
        String sql = "INSERT INTO " + getTableName() + " (from_account_id, to_account_id, transaction_type, amount, transaction_date, description, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void create(Transaction entity) {
        log.error("Method not implemented in TransactionDAOImpl, Use --- createWithAccountId");
    }

    @Override
    public void update(Transaction transaction) {
        String sql = "UPDATE " + getTableName() + " SET from_account_id = ?, to_account_id = ?, transaction_type = ?, amount = ?, transaction_date = ?, description = ?, status = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Transaction entity) {
        log.error("Method not implemented in TransactionDAOImpl, Use --- deleteById(ID id)");
        
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
