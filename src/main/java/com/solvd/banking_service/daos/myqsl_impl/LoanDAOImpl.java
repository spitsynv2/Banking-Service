package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.ILoanDAO;
import com.solvd.banking_service.models.account.Loan;
import com.solvd.banking_service.models.account.enums.loan_enums.LoanStatus;
import com.solvd.banking_service.models.account.enums.loan_enums.LoanType;
import com.solvd.banking_service.daos.myqsl_impl.database_connection.MyConnectionPool;
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
public class LoanDAOImpl extends MYSQLImpl<Loan,Long> implements ILoanDAO {

    private static final Logger log = LogManager.getLogger(LoanDAOImpl.class);
    private static final String CREATE_WITH_ACCOUNT_ID =
            "INSERT INTO " + "loans" + " (account_id, loan_type, amount, interest_rate, " +
                    "term_months, start_date, payment_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE " + "loans" + " SET loan_type = ?, amount = ?, interest_rate = ?, " +
                    "term_months = ?, start_date = ?, payment_date = ?, status = ? WHERE Id = ?";

    @Override
    public void createWithAccountId(Loan loan, Long accountId) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_ACCOUNT_ID)) {
                stmt.setLong(1, accountId);
                stmt.setString(2,loan.getLoanType().toString().toUpperCase());
                stmt.setDouble(3,loan.getAmount());
                stmt.setDouble(4, loan.getInterestRate());
                stmt.setInt(5, loan.getTermMonths());
                stmt.setDate(6, new java.sql.Date(loan.getStartDate().getTime()));
                stmt.setDate(7, new java.sql.Date(loan.getPaymentDate().getTime()));
                stmt.setString(8, loan.getLoanStatus().toString().toUpperCase());
                stmt.executeUpdate();
                log.info("Loan was created/inserted successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(Loan entity) {
        log.error("Method is not implemented in LoanDAOImpl, Use ---> createWithAccountId");
    }

    @Override
    public void update(Loan loan) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1,loan.getLoanType().toString().toUpperCase());
                stmt.setDouble(2,loan.getAmount());
                stmt.setDouble(3, loan.getInterestRate());
                stmt.setInt(4, loan.getTermMonths());
                stmt.setDate(5, new java.sql.Date(loan.getStartDate().getTime()));
                stmt.setDate(6, new java.sql.Date(loan.getPaymentDate().getTime()));
                stmt.setString(7, loan.getLoanStatus().toString().toUpperCase());
                stmt.setLong(8, loan.getId());
                stmt.executeUpdate();
                log.info("Loan was updated successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Loan entity) {
        log.error("Method is not implemented in LoanDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "loans";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "account_id";
    }

    @Override
    protected Loan mapResultSetToEntity(ResultSet rs) {
        Loan loan = new Loan();
        try {
            loan.setId(rs.getLong("Id"));
            loan.setAmount(rs.getDouble("amount"));
            loan.setInterestRate(rs.getDouble("interest_rate"));
            loan.setTermMonths(rs.getInt("term_months"));
            loan.setStartDate(rs.getDate("start_date"));
            loan.setPaymentDate(rs.getDate("payment_date"));

            LoanType loanType = LoanType.valueOf(rs.getString("loan_type").toUpperCase());
            LoanStatus loanStatus = LoanStatus.valueOf(rs.getString("status").toUpperCase());
            loan.setLoanType(loanType);
            loan.setLoanStatus(loanStatus);
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }
        return loan;
    }
}
