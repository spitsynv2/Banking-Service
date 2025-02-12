package com.solvd.bankingservice.repo.impl.mysql;

import com.solvd.bankingservice.repo.IBranchDAO;
import com.solvd.bankingservice.models.employee.Branch;
import com.solvd.bankingservice.utils.MySQLConnectionPool;
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

    private static final String INSERT =
            "INSERT INTO branches (branch_name, location, phone_number, open_date) VALUES (?, ?, ?, ?)";
    private static final String CREATE_WITH_EMPLOYEE_ID =
            "INSERT INTO branches (employee_id, branch_name, location, phone_number, open_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE branches SET branch_name = ?, location = ?, phone_number = ?, open_date = ? WHERE Id = ?";
    private static final String UPDATE_WITH_EMPLOYEE_ID =
            "UPDATE branches SET employee_id = ?, branch_name = ?, location = ?, phone_number = ?, open_date = ? WHERE Id = ?";

    @Override
    public void createWithEmployeeId(Branch branch, Long employeeId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_EMPLOYEE_ID)) {
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
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void updateWithEmployeeId(Branch branch, Long employeeId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE_WITH_EMPLOYEE_ID)) {
                stmt.setLong(1,employeeId);
                stmt.setString(2, branch.getBranchName());
                stmt.setString(3, branch.getLocation());
                stmt.setString(4, branch.getPhoneNumber());
                stmt.setDate(5, new java.sql.Date(branch.getOpenDate().getTime()));
                stmt.setLong(6, branch.getId());

                stmt.executeUpdate();
                log.info("Branch {} was updated successfully WithEmployeeId.", branch);
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error updating branch WithEmployeeId {}", branch, e);
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(Branch branch) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(INSERT)) {
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
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Branch branch) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
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
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
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
    protected Branch mapResultSetToEntity(ResultSet rs){
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
