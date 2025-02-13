package com.solvd.bankingservice.service;

import com.solvd.bankingservice.model.Appointment;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public interface IAppointmentService extends IService<Appointment,Long> {
    void createWithServiceRequestId(Appointment appointment, Long serviceRequestId);
}
