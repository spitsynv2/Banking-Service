package com.solvd.bankingservice.repo.impl.mybatis.impl;

import com.solvd.bankingservice.model.Appointment;
import com.solvd.bankingservice.repo.IAppointmentDAO;
import com.solvd.bankingservice.util.MyBatisLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public class AppointmentMyBatisImpl extends AbstractMyBatisImpl<Appointment,Long> implements IAppointmentDAO {
    private static final Logger log = LogManager.getLogger(AppointmentMyBatisImpl.class);

    @Override
    public void create(Appointment appointment) {
        log.error("Method is not implemented in AppointmentMyBatisImpl, Use ---> createWithServiceRequestId");
    }

    @Override
    public void createWithServiceRequestId(Appointment appointment, Long serviceRequestId) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            IAppointmentDAO mapper = session.getMapper(IAppointmentDAO.class);

            mapper.createWithServiceRequestId(appointment, serviceRequestId);
            session.commit();
            log.info("Successfully created appointment with serviceRequestId: {} and appointmentId: {}", serviceRequestId, appointment.getId());
        } catch (Exception e) {
            log.error("Error creating appointment with serviceRequestId: {}", serviceRequestId, e);
        }
    }

    @Override
    protected Class<?> getMapperClass() {
        return IAppointmentDAO.class;
    }

    @Override
    protected String getTableName() {
        return "appointments";
    }
}
