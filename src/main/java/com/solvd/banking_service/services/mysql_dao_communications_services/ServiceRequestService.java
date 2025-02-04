package com.solvd.banking_service.services.mysql_dao_communications_services;

import com.solvd.banking_service.daos.IAppointmentDAO;
import com.solvd.banking_service.daos.IDAO;
import com.solvd.banking_service.daos.IServiceRequestDAO;
import com.solvd.banking_service.daos.myqsl_impl.AppointmentDAOImpl;
import com.solvd.banking_service.daos.myqsl_impl.ServiceRequestDAOImpl;
import com.solvd.banking_service.models.ServiceRequest;
import com.solvd.banking_service.services.AbstractService;
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
