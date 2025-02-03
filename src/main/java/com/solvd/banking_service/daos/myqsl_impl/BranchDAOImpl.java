package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IBranchDAO;
import com.solvd.banking_service.models.employee.Branch;
import com.solvd.banking_service.services.database_connection.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-03
 */
public class BranchDAOImpl extends MYSQLImpl<Branch,Long> implements IBranchDAO {

    private static final Logger log = LogManager.getLogger(BranchDAOImpl.class);

    private static final String INSERT_BRANCH =
            "INSERT INTO branches (branch_name, location, phone_number, open_date) VALUES (?, ?, ?, ?)";
    private static final String INSERT_BRANCH_WITH_EMPLOYEE_ID =
            "INSERT INTO branches (employee_id, branch_name, location, phone_number, open_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_BRANCH =
            "UPDATE branches SET branch_name = ?, location = ?, phone_number = ?, open_date = ? WHERE id = ?";

    public BranchDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void createWithEmployeeId(Branch branch, Long employeeId) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(INSERT_BRANCH_WITH_EMPLOYEE_ID)) {
                stmt.setLong(1, employeeId);
                stmt.setString(2, branch.getBranchName());
                stmt.setString(3, branch.getLocation());
                stmt.setString(4, branch.getPhoneNumber());
                stmt.setDate(5, new java.sql.Date(branch.getOpenDate().getTime()));

                stmt.executeUpdate();
                log.info("Branch was created/inserted with employee_id successfully.");
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error inserting branch {}, with employee_id {}", branch, employeeId, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(Branch branch) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(INSERT_BRANCH)) {
                stmt.setString(1, branch.getBranchName());
                stmt.setString(2, branch.getLocation());
                stmt.setString(3, branch.getPhoneNumber());
                stmt.setDate(4, new java.sql.Date(branch.getOpenDate().getTime()));

                stmt.executeUpdate();
                log.info("Branch was created/inserted successfully.");
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error inserting branch {}", branch, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Branch branch) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE_BRANCH)) {
                stmt.setString(1, branch.getBranchName());
                stmt.setString(2, branch.getLocation());
                stmt.setString(3, branch.getPhoneNumber());
                stmt.setDate(4, new java.sql.Date(branch.getOpenDate().getTime()));
                stmt.setLong(5,branch.getId());

                stmt.executeUpdate();
                log.info("Branch {} was updated successfully.", branch);
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error updating branch {}", branch, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Branch entity) {
        log.error("Method is not implemented in BranchDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "branches";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "employee_id";
    }

    @Override
    protected Branch mapResultSetToEntity(ResultSet rs) throws SQLException {
        Branch branch = new Branch();
        try {
            branch.setId(rs.getLong("Id"));
            branch.setBranchName(rs.getString("branch_name"));
            branch.setLocation(rs.getString("location"));
            branch.setPhoneNumber(rs.getString("phone_number"));
            branch.setOpenDate(rs.getDate("open_date"));
        } catch (SQLException e) {
            log.error("Error in mapping branch ResultSet {}", rs, e);
            return null;
        }
        return branch;
    }
}
