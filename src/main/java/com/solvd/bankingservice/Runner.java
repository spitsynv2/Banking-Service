package com.solvd.bankingservice;

import com.solvd.bankingservice.repo.impl.xml.wrappers.BankingWrapper;
import com.solvd.bankingservice.models.customer.Customer;
import com.solvd.bankingservice.services.ICustomerAccountService;
import com.solvd.bankingservice.services.IEmployeeService;
import com.solvd.bankingservice.services.json.jackson.BankingServiceJACKSON;
import com.solvd.bankingservice.services.mysql.AuditLogService;
import com.solvd.bankingservice.services.mysql.CustomerAccountService;
import com.solvd.bankingservice.services.mysql.EmployeeService;
import com.solvd.bankingservice.services.mysql.ServiceRequestService;
import com.solvd.bankingservice.services.xml.IBankingProcessingService;
import com.solvd.bankingservice.services.xml.dom.BankingServiceDOM;
import com.solvd.bankingservice.services.xml.jaxb.BankingServiceJAXB;
import com.solvd.bankingservice.utils.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    private static final ICustomerAccountService customerAccountService = new CustomerAccountService();
    private static final IEmployeeService employeeService = new EmployeeService();
    private static final ServiceRequestService serviceRequestService = new ServiceRequestService();
    private static final AuditLogService auditLogService = new AuditLogService();

    private static final IBankingProcessingService bankingServiceJAXB = new BankingServiceJAXB();
    private static final IBankingProcessingService bankingServiceDOM = new BankingServiceDOM();
    private static final IBankingProcessingService bankingServiceJACKSON = new BankingServiceJACKSON();

    public static void main(String[] args) {

        //MYSQL
        Customer customer = customerAccountService.getAllCustomerDataById(2L);
        //Employee employee = employeeService.getAllEmployeeDataById(1L);
        //AuditLog auditLog = auditLogService.readById(3L);
        //ServiceRequest serviceRequest = serviceRequestService.readById(2L);
        MySQLConnectionPool.getInstance().closeAllConnections();

        //JAXB
        BankingWrapper bankingWrapper = bankingServiceJAXB.readAllFromFile();
        log.info(bankingWrapper);
        log.info(bankingServiceJAXB.readAllCardsFromFile());
        bankingServiceJAXB.writeAllToFile(bankingWrapper,"output.xml");

        //DOM
        log.info(bankingServiceDOM.readAllFromFile());
        log.info(bankingServiceDOM.readAllCardsFromFile());

        //JSON
        log.info(bankingServiceJACKSON.readAllFromFile());
        log.info(bankingServiceJACKSON.readAllCardsFromFile());
        bankingServiceJACKSON.writeAllToFile(bankingWrapper,"output.json");
    }
}
