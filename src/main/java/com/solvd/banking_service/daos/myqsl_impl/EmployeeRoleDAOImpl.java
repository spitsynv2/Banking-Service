package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IEmployeeRoleDAO;
import com.solvd.banking_service.models.employee.EmployeeRole;
import com.solvd.banking_service.daos.myqsl_impl.database_connection.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class EmployeeRoleDAOImpl extends MYSQLImpl<EmployeeRole,Long> implements IEmployeeRoleDAO {

    private static final Logger log = LogManager.getLogger(EmployeeRoleDAOImpl.class);

    private static final String CREATE_WITH_EMPLOYEE_ID =
            "INSERT INTO employee_roles (employee_id, role_name, responsibilities) VALUES (?, ?, ?)";
    private static final String CREATE=
            "INSERT INTO employee_roles (role_name, responsibilities) VALUES (?, ?)";
    private static final String UPDATE =
            "UPDATE employee_roles SET role_name = ?, responsibilities = ? WHERE Id = ?";
    private static final String UPDATE_WITH_EMPLOYEE_ID =
            "UPDATE employee_roles SET employee_id = ?, role_name = ?, responsibilities = ? WHERE Id = ?";

    @Override
    public void createWithEmployeeId(EmployeeRole employeeRole, Long employeeId) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_EMPLOYEE_ID)) {
                stmt.setLong(1, employeeId);
                stmt.setString(2,employeeRole.getRoleName());
                stmt.setString(3,employeeRole.getResponsibilities());

                stmt.executeUpdate();
                log.info("Role was created/inserted with employee_id successfully.");
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error inserting employeeRole {}, with employee_id {}", employeeRole, employeeId, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void updateWithEmployeeId(EmployeeRole employeeRole, Long employeeId) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE_WITH_EMPLOYEE_ID)) {
                stmt.setLong(1,employeeId);
                stmt.setString(2,employeeRole.getRoleName());
                stmt.setString(3,employeeRole.getResponsibilities());
                stmt.setLong(4,employeeRole.getId());

                stmt.executeUpdate();
                log.info("EmployeeRole {} was updated successfully WithEmployeeId.", employeeRole);
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error updating EmployeeRole WithEmployeeId {}", employeeRole, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(EmployeeRole employeeRole) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE)) {
                stmt.setString(1,employeeRole.getRoleName());
                stmt.setString(2,employeeRole.getResponsibilities());

                stmt.executeUpdate();
                log.info("Role was created/inserted successfully.");
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error in inserting employeeRole {}", employeeRole, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(EmployeeRole employeeRole) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1,employeeRole.getRoleName());
                stmt.setString(2,employeeRole.getResponsibilities());
                stmt.setLong(3,employeeRole.getId());
                stmt.executeUpdate();
                log.info("EmployeeRole {} was updated successfully.", employeeRole);
            }
        } catch (SQLException | InterruptedException e) {
            log.error("Error in updating EmployeeRole {}", employeeRole, e);
        } finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(EmployeeRole entity) {
        log.error("Method is not implemented in EmployeeRoleDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "employee_roles";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "employee_id";
    }

    @Override
    protected EmployeeRole mapResultSetToEntity(ResultSet rs){
        EmployeeRole employeeRole = new EmployeeRole();
        try {
            employeeRole.setId(rs.getLong("Id"));
            employeeRole.setRoleName(rs.getString("role_name"));
            employeeRole.setResponsibilities(rs.getString("responsibilities"));
        } catch (SQLException e) {
            log.error(e);
            return null;
        }

        return employeeRole;
    }
}
