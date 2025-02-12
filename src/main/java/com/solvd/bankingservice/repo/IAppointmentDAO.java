package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.models.Appointment;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IAppointmentDAO extends IDAO<Appointment,Long> {
    void createWithServiceRequestId(Appointment appointment, Long serviceRequestId);
    List<Appointment> readAllByForeignKeyId(Long id);
}
