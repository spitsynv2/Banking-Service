package com.solvd.bankingservice.repo.impl.mysql;

import com.solvd.bankingservice.repo.IDepositDAO;
import com.solvd.bankingservice.model.account.Deposit;
import com.solvd.bankingservice.model.account.enums.deposit_enums.DepositStatus;
import com.solvd.bankingservice.model.account.enums.deposit_enums.DepositType;
import com.solvd.bankingservice.util.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public class DepositMySQLJdbcImpl extends AbstractMySQLJdbcImpl<Deposit,Long> implements IDepositDAO {

    private static final Logger log = LogManager.getLogger(DepositMySQLJdbcImpl.class);

    private static final String CREATE_WITH_ACCOUNT_ID ="INSERT INTO " + "deposits" + " (account_id, deposit_type, " +
            "amount, interest_rate, term_months, start_date, maturity_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + "deposits" + " SET deposit_type = ?, amount = ?, interest_rate = ?, " +
            "term_months = ?, start_date = ?, maturity_date = ?, status = ? WHERE Id = ?";

    @Override
    public void createWithAccountId(Deposit deposit, Long accountId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_ACCOUNT_ID)) {
                stmt.setLong(1, accountId);
                stmt.setString(2,deposit.getDepositType().toString().toUpperCase());
                stmt.setDouble(3,deposit.getAmount());
                stmt.setDouble(4, deposit.getInterestRate());
                stmt.setInt(5, deposit.getTermMonths());
                stmt.setDate(6, java.sql.Date.valueOf(deposit.getStartDate()));
                stmt.setDate(7, java.sql.Date.valueOf(deposit.getMaturityDate()));
                stmt.setString(8, deposit.getDepositStatus().toString().toUpperCase());
                stmt.executeUpdate();
                log.info("Deposit was created/inserted successfully.");
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
    public void create(Deposit entity) {
        log.error("Method is not implemented in DepositDAO, Use ---> createWithAccountId");
    }

    @Override
    public void update(Deposit deposit) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1,deposit.getDepositType().toString().toUpperCase());
                stmt.setDouble(2,deposit.getAmount());
                stmt.setDouble(3, deposit.getInterestRate());
                stmt.setInt(4, deposit.getTermMonths());
                stmt.setDate(5, java.sql.Date.valueOf(deposit.getStartDate()));
                stmt.setDate(6, java.sql.Date.valueOf(deposit.getMaturityDate()));
                stmt.setString(7, deposit.getDepositStatus().toString().toUpperCase());
                stmt.setLong(8, deposit.getId());
                stmt.executeUpdate();
                log.info("Deposit was updated successfully.");
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
        return "deposits";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "account_id";
    }

    @Override
    protected Deposit mapResultSetToEntity(ResultSet rs) {
        Deposit deposit = new Deposit();
        try {
            deposit.setId(rs.getLong("Id"));
            deposit.setAmount(rs.getDouble("amount"));
            deposit.setInterestRate(rs.getDouble("interest_rate"));
            deposit.setTermMonths(rs.getInt("term_months"));
            deposit.setStartDate(rs.getDate("start_date").toLocalDate());
            deposit.setMaturityDate(rs.getDate("maturity_date").toLocalDate());

            DepositType depositType = DepositType.valueOf(rs.getString("deposit_type").toUpperCase());
            DepositStatus depositStatus = DepositStatus.valueOf(rs.getString("status").toUpperCase());
            deposit.setDepositType(depositType);
            deposit.setDepositStatus(depositStatus);
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }
        return deposit;
    }
}
