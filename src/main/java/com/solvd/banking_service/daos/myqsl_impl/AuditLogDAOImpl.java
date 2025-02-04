package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IAuditLogDAO;
import com.solvd.banking_service.models.AuditLog;
import com.solvd.banking_service.models.enums.LogActionType;
import com.solvd.banking_service.daos.myqsl_impl.database_connection.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class AuditLogDAOImpl extends MYSQLImpl<AuditLog,Long> implements IAuditLogDAO {

    private static final Logger log = LogManager.getLogger(AuditLogDAOImpl.class);
    private static final String CREATE_WITH_CUSTOMER_ID =
            "INSERT INTO " + "audit_logs" + " (customer_id, employee_id, action_type, action_description, date) VALUES (?, ?, ?, ?, ?)";

    @Override
    public AuditLog readById(Long id) {
        Connection connection = null;
        AuditLog entity = null;
        String READ_BY_ID = "SELECT * FROM " + getTableName() + " WHERE log_id = ?";

        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(READ_BY_ID)) {
                stmt.setObject(1, id);
                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        entity = mapResultSetToEntity(rs);
                    }
                }
            }
        }catch (SQLException | InterruptedException e) {
            log.error("Error in reading by id: {}, from table: {}", id, getTableName(), e);
            return null;
        }finally {
            if (connection != null) {
                MyConnectionPool.releaseConnection(connection);
            }
        }

        log.info("AuditLog: {} was successfully read from database", entity);
        return entity;
    }

    @Override
    public void createWithCustomerId(AuditLog auditLog, Long customerId) {
        Connection connection = null;
        try {
            connection = MyConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_CUSTOMER_ID)) {
                stmt.setLong(1,customerId);
                stmt.setLong(2,auditLog.getAuditorId());
                stmt.setString(3,auditLog.getLogActionType().toString().toUpperCase());
                stmt.setString(4,auditLog.getActionDescription());
                stmt.setTimestamp(5,Timestamp.valueOf(auditLog.getDate()));

                stmt.executeUpdate();
                log.info("AuditLog was created/inserted successfully.");
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
    public void create(AuditLog entity) {
        log.error("Method is not implemented in AuditLogDAOImpl, Use ---> createWithAccountId");
    }

    @Override
    public void update(AuditLog entity) {
        log.error("Method is not supported in AuditLogDAOImpl, cannot modify any AuditLog");
    }

    @Override
    public void delete(AuditLog entity) {
        log.error("Method is not supported in AuditLogDAOImpl, cannot delete any AuditLog data");
    }

    @Override
    public void deleteById(Long aLong) {
        log.error("Method is not supported in AuditLogDAOImpl, cannot delete any AuditLog data by id");
    }

    @Override
    protected String getTableName() {
        return "audit_logs";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "customer_id";
    }

    @Override
    protected AuditLog mapResultSetToEntity(ResultSet rs) {
        AuditLog auditLog = new AuditLog();
        try {
            auditLog.setLogId(rs.getLong("log_id"));
            auditLog.setCustomerId(rs.getLong("customer_id"));
            auditLog.setAuditorId(rs.getLong("employee_id"));

            LogActionType logActionType = LogActionType.valueOf(rs.getString("action_type").toUpperCase());
            auditLog.setLogActionType(logActionType);
            auditLog.setActionDescription(rs.getString("action_description"));
            auditLog.setDate(rs.getTimestamp("date").toLocalDateTime());
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }

        return auditLog;
    }
}
