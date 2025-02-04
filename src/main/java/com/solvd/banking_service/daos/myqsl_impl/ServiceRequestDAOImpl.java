package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IServiceRequestDAO;
import com.solvd.banking_service.models.ServiceRequest;
import com.solvd.banking_service.models.enums.service_request_enums.ServiceRequestStatus;
import com.solvd.banking_service.models.enums.service_request_enums.ServiceRequestType;
import com.solvd.banking_service.utils.database_connection.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class ServiceRequestDAOImpl extends MYSQLImpl<ServiceRequest,Long> implements IServiceRequestDAO {

    private static final Logger log = LogManager.getLogger(ServiceRequestDAOImpl.class);

    private static final String CREATE_WITH_CUSTOMER_ID = "INSERT INTO " + "service_requests " +
            "(customer_id, employee_id, request_type, status, created_at, resolved_at, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + "service_requests" + " SET request_type = ?, " +
            "status = ?, resolved_at = ?, notes = ? WHERE Id = ?";

    @Override
    public void createWithCustomerId(ServiceRequest serviceRequest, Long customerId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_CUSTOMER_ID)) {
                stmt.setLong(1, customerId);
                stmt.setLong(2,serviceRequest.getEmployeeId());
                stmt.setString(3,serviceRequest.getServiceRequestType().toString().toUpperCase());
                stmt.setString(4,serviceRequest.getServiceRequestStatus().toString().toUpperCase());
                stmt.setTimestamp(5, Timestamp.valueOf(serviceRequest.getCreatedAt()));
                stmt.setTimestamp(6, Timestamp.valueOf(serviceRequest.getResolvedAt()));
                stmt.setString(7,serviceRequest.getNotes());

                stmt.executeUpdate();
                log.info("ServiceRequest was created/inserted createWithCustomerId successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void create(ServiceRequest entity) {
        log.error("Method is not implemented in ServiceRequestDAOImpl, Use ---> createWithCustomerId");
    }

    @Override
    public void update(ServiceRequest serviceRequest) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1,serviceRequest.getServiceRequestType().toString().toUpperCase());
                stmt.setString(2,serviceRequest.getServiceRequestStatus().toString().toUpperCase());
                stmt.setTimestamp(3, Timestamp.valueOf(serviceRequest.getResolvedAt()));
                stmt.setString(4,serviceRequest.getNotes());
                stmt.setLong(5,serviceRequest.getId());

                stmt.executeUpdate();
                log.info("ServiceRequest was updated successfully.");
            }
        }catch (SQLException | InterruptedException e) {
            log.error(e);
        }finally {
            if (connection != null) {
                MySQLConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(ServiceRequest entity) {
        log.error("Method is not implemented in ServiceRequestDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "service_requests";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "customer_id";
    }

    @Override
    protected ServiceRequest mapResultSetToEntity(ResultSet rs) {
        ServiceRequest serviceRequest = new ServiceRequest();
        try {
            serviceRequest.setId(rs.getLong("Id"));
            serviceRequest.setCustomerId(rs.getLong("customer_id"));
            serviceRequest.setEmployeeId(rs.getLong("employee_id"));
            serviceRequest.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            serviceRequest.setResolvedAt(rs.getTimestamp("resolved_at").toLocalDateTime());
            serviceRequest.setNotes(rs.getString("notes"));

            ServiceRequestType serviceRequestType = ServiceRequestType.valueOf(rs.getString("request_type").toUpperCase());
            ServiceRequestStatus serviceRequestStatus = ServiceRequestStatus.valueOf(rs.getString("status").toUpperCase());

            serviceRequest.setServiceRequestType(serviceRequestType);
            serviceRequest.setServiceRequestStatus(serviceRequestStatus);
        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }

        return serviceRequest;
    }
}
