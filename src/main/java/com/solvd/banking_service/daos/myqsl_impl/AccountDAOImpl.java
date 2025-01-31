package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IAccountDAO;
import com.solvd.banking_service.models.account.Account;
import com.solvd.banking_service.models.account.enums.account_enums.AccountStatus;
import com.solvd.banking_service.models.account.enums.account_enums.AccountType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public class AccountDAOImpl extends MYSQLImpl<Account,Long> implements IAccountDAO {

    private static final Logger log = LogManager.getLogger(AccountDAOImpl.class);

    public AccountDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void createWithCustomerId(Account account, Long customerId) {
        String sql = "INSERT INTO " + getTableName() + " (customer_id, account_type, balance, currency, opened_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, customerId);
            stmt.setString(2, account.getAccountType().toString().toUpperCase());
            stmt.setDouble(3, account.getBalance());
            stmt.setString(4, account.getCurrency());
            stmt.setTimestamp(5, Timestamp.valueOf(account.getOpenedDate()));
            stmt.setString(6, account.getAccountStatus().toString().toUpperCase());
            stmt.executeUpdate();
            log.info("Account was created/inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Account entity) {
        throw new UnsupportedOperationException("Method not implemented in AddressDAOImpl, Use --- createWithCustomerId");
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE " + getTableName() + " SET account_type = ?, balance = ?, currency = ?, opened_date = ?, status = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountType().toString().toUpperCase());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getCurrency());
            stmt.setTimestamp(4, Timestamp.valueOf(account.getOpenedDate()));
            stmt.setString(5, account.getAccountStatus().toString().toUpperCase());
            stmt.setLong(6, account.getId());
            stmt.executeUpdate();
            log.info("Account was updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Method not implemented in AddressDAOImpl, Use --- deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "accounts";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "customer_id";
    }

    @Override
    protected Account mapResultSetToEntity(ResultSet rs) {
        Account account = new Account();
        try {
            account.setId(rs.getLong("Id"));
            account.setBalance(rs.getDouble("balance"));
            account.setCurrency(rs.getString("currency"));
            account.setOpenedDate(rs.getTimestamp("opened_date").toLocalDateTime());

            AccountType accountType = AccountType.valueOf(rs.getString("account_type").toUpperCase());
            AccountStatus accountStatus = AccountStatus.valueOf(rs.getString("status").toUpperCase());
            account.setAccountType(accountType);
            account.setAccountStatus(accountStatus);
        } catch (IllegalArgumentException | SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
