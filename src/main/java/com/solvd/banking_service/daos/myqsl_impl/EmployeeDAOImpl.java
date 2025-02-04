package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IEmployeeDAO;
import com.solvd.banking_service.models.employee.Employee;
import com.solvd.banking_service.utils.database_connection.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class EmployeeDAOImpl extends MYSQLImpl<Employee,Long> implements IEmployeeDAO {

    private static final Logger log = LogManager.getLogger(EmployeeDAOImpl.class);

    private static final String CHECK_PHONE_NUMBER_EXISTS = "SELECT COUNT(*) FROM " + "employees" + " WHERE phone_number = ?";
    private static final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM " + "employees" + " WHERE email = ?";
    private static final String CREATE =
            "INSERT INTO employees (first_name, last_name, email, phone_number, job_title, hire_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, job_title = ?, " +
                    "hire_date = ? WHERE Id = ?";

    @Override
    public void create(Employee employee) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE)) {
                stmt.setString(1,employee.getFirstName());
                stmt.setString(2, employee.getLastName());
                stmt.setString(3,employee.getEmail());
                stmt.setString(4,employee.getPhoneNumber());
                stmt.setString(5, employee.getJobTitle());
                stmt.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));

                stmt.executeUpdate();
                log.info("Employee was created/inserted successfully.");
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error in inserting Employee {}", employee, e);
        } finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Employee employee) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1,employee.getFirstName());
                stmt.setString(2, employee.getLastName());
                stmt.setString(3,employee.getEmail());
                stmt.setString(4,employee.getPhoneNumber());
                stmt.setString(5, employee.getJobTitle());
                stmt.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
                stmt.setLong(7,employee.getId());

                stmt.executeUpdate();
                log.info("Employee {} was updated successfully.", employee);
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error in updating Employee {}", employee, e);
        } finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Employee entity) {
        log.error("Method is not implemented in EmployeeDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    public boolean checkPhoneNumberExists(String phoneNumber) {
        Connection connection = null;

        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(CHECK_PHONE_NUMBER_EXISTS)) {
                statement.setString(1, phoneNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
            return false;
        } finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }

        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        Connection connection = null;

        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL_EXISTS)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
            return false;
        } finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }

        return false;
    }

    @Override
    protected String getTableName() {
        return "employees";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        log.error("Method getForeignKeyColumnLabel() is not supported in EmployeeDAOImpl");
        return null;
    }

    @Override
    public List<Employee> readAllByForeignKeyId(Long foreignKeyId) {
        log.error("Method readAllByForeignKeyId is not supported in EmployeeDAOImpl");
        return null;
    }

    @Override
    protected Employee mapResultSetToEntity(ResultSet rs) {
        Employee employee = new Employee();
        try {
            employee.setId(rs.getLong("Id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setEmail(rs.getString("email"));
            employee.setPhoneNumber(rs.getString("phone_number"));
            employee.setJobTitle(rs.getString("job_title"));
            employee.setHireDate(rs.getDate("hire_date"));
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
        return employee;
    }
}
