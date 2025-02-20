package com.solvd.bankingservice.service;

import com.solvd.bankingservice.repo.IAppointmentDAO;
import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.repo.IServiceRequestDAO;
import com.solvd.bankingservice.model.ServiceRequest;
import com.solvd.bankingservice.util.patterns.DAOFactory;
import com.solvd.bankingservice.util.patterns.DAOType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class ServiceRequestService extends AbstractService<ServiceRequest,Long> implements IServiceRequestService {

    private static final Logger log = LogManager.getLogger(ServiceRequestService.class);

    private final IServiceRequestDAO serviceRequestDAO;
    private final IAppointmentDAO appointmentDAO;

    public ServiceRequestService(DAOType daoType){
        serviceRequestDAO = DAOFactory.createDao(IServiceRequestDAO.class,daoType);
        appointmentDAO = DAOFactory.createDao(IAppointmentDAO.class,daoType);
    }

    @Override
    public void create(ServiceRequest entity) {
        log.error("Unsupported operation");
    }

    @Override
    public ServiceRequest readById(Long id) {
        ServiceRequest serviceRequest = serviceRequestDAO.readById(id);

        if (serviceRequest == null) {
            log.error("Cannot find serviceRequest with ID {}", id);
            return null;
        }

        serviceRequest.setAppointments(appointmentDAO.readAllByForeignKeyId(serviceRequest.getId()));

        return serviceRequest;
    }

    @Override
    protected IDAO<ServiceRequest, Long> getDao() {
        return serviceRequestDAO;
    }

    @Override
    public void createWithCustomerId(ServiceRequest serviceRequest, Long customerId) {
        serviceRequestDAO.createWithCustomerId(serviceRequest,customerId);
    }

    @Override
    public List<ServiceRequest> readAllByForeignKeyId(Long customerId) {
        List<ServiceRequest> serviceRequestList = serviceRequestDAO.readAllByForeignKeyId(customerId);

        if (serviceRequestList == null || serviceRequestList.isEmpty()) {
            log.error("Cannot find serviceRequestList with customerId {}", customerId);
            return null;
        }

        for (ServiceRequest serviceRequest : serviceRequestList) {
            serviceRequest.setAppointments(appointmentDAO.readAllByForeignKeyId(serviceRequest.getId()));
        }

        return serviceRequestList;
    }
}
