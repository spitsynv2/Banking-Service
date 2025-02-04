package com.solvd.banking_service.daos.myqsl_impl;

import com.solvd.banking_service.daos.IAppointmentDAO;
import com.solvd.banking_service.models.Appointment;
import com.solvd.banking_service.models.enums.AppointmentStatus;
import com.solvd.banking_service.utils.database_connection.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class AppointmentDAOImpl extends MYSQLImpl<Appointment,Long> implements IAppointmentDAO {

    private static final Logger log = LogManager.getLogger(AppointmentDAOImpl.class);

    private static final String CREATE_WITH_SERVICE_REQUEST_ID = "INSERT INTO " + "appointments " +
            "(service_request_id, appointment_date, status, notes) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + "appointments" + " SET appointment_date = ?, " +
            "status = ?, notes = ? WHERE Id = ?";

    @Override
    public void createWithServiceRequestId(Appointment appointment, Long serviceRequestId) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(CREATE_WITH_SERVICE_REQUEST_ID)) {
                stmt.setLong(1, serviceRequestId);
                stmt.setTimestamp(2, Timestamp.valueOf(appointment.getAppointmentDate()));
                stmt.setString(3,appointment.getAppointmentStatus().toString().toUpperCase());
                stmt.setString(4,appointment.getNotes());

                stmt.executeUpdate();
                log.info("Appointment was created/inserted WithServiceRequestId successfully.");
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
    public void create(Appointment entity) {
        log.error("Method is not implemented in AppointmentDAOImpl, Use ---> createWithServiceRequestId");
    }

    @Override
    public void update(Appointment appointment) {
        Connection connection = null;
        try {
            connection = MySQLConnectionPool.getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setTimestamp(1, Timestamp.valueOf(appointment.getAppointmentDate()));
                stmt.setString(2,appointment.getAppointmentStatus().toString().toUpperCase());
                stmt.setString(3,appointment.getNotes());
                stmt.setLong(4,appointment.getId());

                stmt.executeUpdate();
                log.info("Appointment was updated WithServiceRequestId successfully.");
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
    public void delete(Appointment entity) {
        log.error("Method is not implemented in AppointmentDAOImpl, Use ---> deleteById(ID id)");
    }

    @Override
    protected String getTableName() {
        return "appointments";
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        return "service_request_id";
    }

    @Override
    protected Appointment mapResultSetToEntity(ResultSet rs) {
        Appointment appointment = new Appointment();

        try {
            appointment.setId(rs.getLong("Id"));
            appointment.setAppointmentDate(rs.getTimestamp("appointment_date").toLocalDateTime());
            AppointmentStatus appointmentStatus = AppointmentStatus.valueOf(rs.getString("status").toUpperCase());
            appointment.setAppointmentStatus(appointmentStatus);
            appointment.setNotes(rs.getString("notes"));

        } catch (IllegalArgumentException | SQLException e) {
            log.error(e);
            return null;
        }

        return appointment;
    }
}
