package com.solvd.banking_service;

import com.solvd.banking_service.models.AuditLog;
import com.solvd.banking_service.models.ServiceRequest;
import com.solvd.banking_service.models.customer.Customer;
import com.solvd.banking_service.models.employee.Employee;
import com.solvd.banking_service.services.ICustomerAccountService;
import com.solvd.banking_service.services.IEmployeeService;
import com.solvd.banking_service.services.mysql_dao_communications_services.AuditLogService;
import com.solvd.banking_service.services.mysql_dao_communications_services.CustomerAccountService;
import com.solvd.banking_service.daos.myqsl_impl.database_connection.MyConnectionPool;
import com.solvd.banking_service.services.mysql_dao_communications_services.EmployeeService;
import com.solvd.banking_service.services.mysql_dao_communications_services.ServiceRequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    private static final ICustomerAccountService customerAccountService = new CustomerAccountService();
    private static final IEmployeeService employeeService = new EmployeeService();
    private static final ServiceRequestService serviceRequestService = new ServiceRequestService();
    private static final AuditLogService auditLogService = new AuditLogService();

    public static void main(String[] args)
    {
        //Customer customer = customerAccountService.getAllCustomerDataById(2L);

        //Employee employee = employeeService.getAllEmployeeDataById(1L);

        //AuditLog auditLog = auditLogService.readById(3L);

        ServiceRequest serviceRequest = serviceRequestService.readById(2L);

        MyConnectionPool.closeAllConnections();

        //TODO XML FILE
        //TODO JAXB MARSHAL UNMARSHAL
    }
}
