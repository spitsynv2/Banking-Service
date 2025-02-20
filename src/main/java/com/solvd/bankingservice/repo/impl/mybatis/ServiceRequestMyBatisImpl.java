package com.solvd.bankingservice.repo.impl.mybatis;

import com.solvd.bankingservice.model.ServiceRequest;
import com.solvd.bankingservice.repo.IServiceRequestDAO;
import com.solvd.bankingservice.util.MyBatisLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-13
 */
public class ServiceRequestMyBatisImpl extends AbstractMyBatisImpl<ServiceRequest,Long> implements IServiceRequestDAO {
    private static final Logger log = LogManager.getLogger(ServiceRequestMyBatisImpl.class);

    @Override
    public void create(ServiceRequest serviceRequest) {
        log.error("Method is not implemented in ServiceRequestMyBatisImpl, Use ---> createWithServiceRequestId");
    }

    @Override
    protected Class<?> getMapperClass() {
        return IServiceRequestDAO.class;
    }

    @Override
    protected String getTableName() {
        return "service_requests";
    }

    @Override
    public void createWithCustomerId(ServiceRequest serviceRequest, Long customerId) {
        try (SqlSession session = MyBatisLoader.getSqlSessionFactory().openSession()) {
            IServiceRequestDAO mapper = session.getMapper(IServiceRequestDAO.class);

            mapper.createWithCustomerId(serviceRequest, customerId);
            session.commit();
            log.info("Successfully created serviceRequest with customerId: {} and serviceRequestId: {}", customerId, serviceRequest.getId());
        } catch (Exception e) {
            log.error("Error creating serviceRequest with customerId: {}", customerId, e);
        }
    }
}
