package com.solvd.bankingservice.repo;

import com.solvd.bankingservice.model.Appointment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public interface IAppointmentDAO extends IDAO<Appointment,Long>, IDAOCommonUtility<Appointment, Long> {
    void createWithServiceRequestId(@Param("appointment") Appointment appointment,
                                    @Param("serviceRequestId") Long serviceRequestId);
}
