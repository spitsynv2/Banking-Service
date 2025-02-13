package com.solvd.bankingservice.service.mysql;

import com.solvd.bankingservice.repo.IAppointmentDAO;
import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.repo.IServiceRequestDAO;
import com.solvd.bankingservice.repo.impl.mysql.AppointmentMySQLJdbcImpl;
import com.solvd.bankingservice.repo.impl.mysql.ServiceRequestMySQLJdbcImpl;
import com.solvd.bankingservice.model.ServiceRequest;
import com.solvd.bankingservice.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class ServiceRequestService extends AbstractService<ServiceRequest,Long> {

    private static final Logger log = LogManager.getLogger(ServiceRequestService.class);

    private final IServiceRequestDAO serviceRequestDAO = new ServiceRequestMySQLJdbcImpl();
    private final IAppointmentDAO appointmentDAO = new AppointmentMySQLJdbcImpl();


    @Override
    public ServiceRequest readById(Long id) {
        ServiceRequest serviceRequest = serviceRequestDAO.readById(id);

        if (serviceRequest == null) {
            log.error("Cannot find serviceRequest with ID {}", id);
            return null;
        }

        serviceRequest.setAppointments(appointmentDAO.readAllByForeignKeyId(id));

        return serviceRequest;
    }

    @Override
    protected IDAO<ServiceRequest, Long> getDao() {
        return serviceRequestDAO;
    }
}
