package com.solvd.bankingservice;

import com.solvd.bankingservice.daos.impl.xml.jaxb.*;
import com.solvd.bankingservice.models.customer.Customer;
import com.solvd.bankingservice.services.ICustomerAccountService;
import com.solvd.bankingservice.services.IEmployeeService;
import com.solvd.bankingservice.services.mysql.AuditLogService;
import com.solvd.bankingservice.services.mysql.CustomerAccountService;
import com.solvd.bankingservice.services.mysql.EmployeeService;
import com.solvd.bankingservice.services.mysql.ServiceRequestService;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.BankingServiceWrapper;
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

    private static final AppointmentXMLImpl appointmentXMLservice = new AppointmentXMLImpl();
    private static final CardXMLImpl cardXMLservice = new CardXMLImpl();
    private static final DepositXMLImpl depositXMLservice = new DepositXMLImpl();
    private static final LoanXMLImpl loanXMLservice = new LoanXMLImpl();
    private static final TransactionXMLImpl transactionXMLservice = new TransactionXMLImpl();

    private static final BankingXMLImpl BANKING_XML_IMPL = new BankingXMLImpl();


    public static void main(String[] args)
    {
        Customer customer = customerAccountService.getAllCustomerDataById(2L);

        //Employee employee = employeeService.getAllEmployeeDataById(1L);

        //AuditLog auditLog = auditLogService.readById(3L);

        //ServiceRequest serviceRequest = serviceRequestService.readById(2L);

        MySQLConnectionPool.closeAllConnections();

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

        BankingServiceWrapper bankingServiceWrapper = BANKING_XML_IMPL.unmarshalAll();
        log.info(bankingServiceWrapper);
        BANKING_XML_IMPL.marshalAll(bankingServiceWrapper,"output.xml");

        //TODO create some services for xml, not call directly
        //TODO DOM parser
        //TODO json
    }
}
