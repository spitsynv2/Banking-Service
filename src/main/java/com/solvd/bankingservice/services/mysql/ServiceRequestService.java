package com.solvd.bankingservice.services.mysql;

import com.solvd.bankingservice.repo.IAppointmentDAO;
import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.repo.IServiceRequestDAO;
import com.solvd.bankingservice.repo.impl.mysql.AppointmentDAOImpl;
import com.solvd.bankingservice.repo.impl.mysql.ServiceRequestDAOImpl;
import com.solvd.bankingservice.models.ServiceRequest;
import com.solvd.bankingservice.services.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class ServiceRequestService extends AbstractService<ServiceRequest,Long> {

    private static final Logger log = LogManager.getLogger(ServiceRequestService.class);

    private final IServiceRequestDAO serviceRequestDAO = new ServiceRequestDAOImpl();
    private final IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();


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
