package com.solvd.banking_service.daos;

import com.solvd.banking_service.models.Appointment;
import com.solvd.banking_service.models.account.Deposit;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IAppointmentDAO extends IDAO<Appointment,Long> {
    void createWithServiceRequestId(Appointment appointment, Long serviceRequestId);
}
