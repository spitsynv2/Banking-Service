package com.solvd.bankingservice.service;

import com.solvd.bankingservice.model.Appointment;
import com.solvd.bankingservice.repo.IAppointmentDAO;
import com.solvd.bankingservice.repo.IDAO;
import com.solvd.bankingservice.util.patterns.DAOFactory;
import com.solvd.bankingservice.util.patterns.DAOType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public class AppointmentService extends AbstractService<Appointment,Long> implements IAppointmentService {
    private static final Logger log = LogManager.getLogger(AppointmentService.class);
    private final IAppointmentDAO appointmentDAO;

    public AppointmentService(DAOType daoType){
        appointmentDAO = DAOFactory.createDao(IAppointmentDAO.class,daoType);
    }

    @Override
    public void create(Appointment entity) {
        log.error("Unsupported operation");
    }

    @Override
    protected IDAO<Appointment, Long> getDao() {
        return appointmentDAO;
    }

    @Override
    public void createWithServiceRequestId(Appointment appointment, Long serviceRequestId) {
        appointmentDAO.createWithServiceRequestId(appointment, serviceRequestId);
    }
}
