package com.solvd.banking_service;

import com.solvd.banking_service.models.AuditLog;
import com.solvd.banking_service.models.ServiceRequest;
import com.solvd.banking_service.models.account.Card;
import com.solvd.banking_service.models.account.enums.CardType;
import com.solvd.banking_service.models.customer.Customer;
import com.solvd.banking_service.models.employee.Employee;
import com.solvd.banking_service.services.ICustomerAccountService;
import com.solvd.banking_service.services.IEmployeeService;
import com.solvd.banking_service.services.mysql_dao_communications_services.AuditLogService;
import com.solvd.banking_service.services.mysql_dao_communications_services.CustomerAccountService;
import com.solvd.banking_service.services.mysql_dao_communications_services.EmployeeService;
import com.solvd.banking_service.services.mysql_dao_communications_services.ServiceRequestService;
import com.solvd.banking_service.services.xml_communications_service.*;
import com.solvd.banking_service.services.xml_communications_service.wrappers.BankingServiceWrapper;
import com.solvd.banking_service.utils.database_connection.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    private static final ICustomerAccountService customerAccountService = new CustomerAccountService();
    private static final IEmployeeService employeeService = new EmployeeService();
    private static final ServiceRequestService serviceRequestService = new ServiceRequestService();
    private static final AuditLogService auditLogService = new AuditLogService();

    private static final AppointmentXMLService appointmentXMLservice = new AppointmentXMLService();
    private static final CardXMLService cardXMLservice = new CardXMLService();
    private static final DepositXMLService depositXMLservice = new DepositXMLService();
    private static final LoanXMLService loanXMLservice = new LoanXMLService();
    private static final TransactionXMLService transactionXMLservice = new TransactionXMLService();

    private static final BankingXMLService bankingXMLService = new BankingXMLService();


    public static void main(String[] args)
    {
        //Customer customer = customerAccountService.getAllCustomerDataById(2L);

        //Employee employee = employeeService.getAllEmployeeDataById(1L);

        //AuditLog auditLog = auditLogService.readById(3L);

        //ServiceRequest serviceRequest = serviceRequestService.readById(2L);

        //MySQLConnectionPool.closeAllConnections();

        log.info(appointmentXMLservice.getAllEntitys());
        log.info(cardXMLservice.getAllEntitys());
        log.info(depositXMLservice.getAllEntitys());
        log.info(loanXMLservice.getAllEntitys());
        log.info(transactionXMLservice.getAllEntitys());

        /*
        cardXMLservice.writeEntitiesToXML(
                List.of(new Card(2L, "555666612234", CardType.CREDIT_MASTERCARD,
                        LocalDate.parse("2027-12-31"), "124", true)),
                "cards.xml");
         */

        BankingServiceWrapper bankingServiceWrapper = bankingXMLService.unmarshalAll();
        log.info(bankingServiceWrapper);
        bankingXMLService.marshalAll(bankingServiceWrapper,"output.xml");
    }
}
