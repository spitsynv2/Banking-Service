package com.solvd.bankingservice;

import com.solvd.bankingservice.service.*;
import com.solvd.bankingservice.service.json.jackson.BankingServiceJACKSON;
import com.solvd.bankingservice.service.mysql.CustomerAccountService;
import com.solvd.bankingservice.service.mysql.EmployeeService;
import com.solvd.bankingservice.service.xml.IBankingProcessingService;
import com.solvd.bankingservice.service.xml.dom.BankingServiceDOM;
import com.solvd.bankingservice.service.xml.jaxb.BankingServiceJAXB;
import com.solvd.bankingservice.util.patterns.DAOType;
import com.solvd.bankingservice.util.patterns.DepositDAOFactory;
import com.solvd.bankingservice.util.patterns.LoanDAOFactory;
import com.solvd.bankingservice.util.patterns.TransactionDAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    private static final ICustomerAccountService customerAccountService = new CustomerAccountService();
    private static final IEmployeeService employeeService = new EmployeeService();

    private static final IBankingProcessingService bankingServiceJAXB = new BankingServiceJAXB();
    private static final IBankingProcessingService bankingServiceDOM = new BankingServiceDOM();
    private static final IBankingProcessingService bankingServiceJACKSON = new BankingServiceJACKSON();

    private static final IServiceRequestService serviceRequestServiceMySQL = new ServiceRequestService(DAOType.JDBC);
    private static final IAppointmentService appointmentServiceMyBatis = new AppointmentService(DAOType.MYBATIS);
    private static final IServiceRequestService serviceRequestServiceMyBatis = new ServiceRequestService(DAOType.MYBATIS);

    public static void main(String[] args) {

        //MYSQL
        //Customer customer = customerAccountService.getAllCustomerDataById(2L);
        //Employee employee = employeeService.getAllEmployeeDataById(1L);
        //MySQLConnectionPool.getInstance().closeAllConnections();

        //JAXB
        //BankingWrapper bankingWrapper = bankingServiceJAXB.readAllFromFile();
        //log.info(bankingWrapper);
        //log.info(bankingServiceJAXB.readAllCardsFromFile());
        //bankingServiceJAXB.writeAllToFile(bankingWrapper,"output.xml");

        //DOM
        //log.info(bankingServiceDOM.readAllFromFile());
        //log.info(bankingServiceDOM.readAllCardsFromFile());

        //JSON
        //log.info(bankingServiceJACKSON.readAllFromFile());
        //log.info(bankingServiceJACKSON.readAllCardsFromFile());
        //bankingServiceJACKSON.writeAllToFile(bankingWrapper,"output.json");


        //MyBatis & Factory Pattern
        //Appointment appointment = appointmentServiceMyBatis.readById(2L);
        //log.info(appointment);

        //appointment.setNotes("TEST UPDATE: "+ LocalDate.now());
        //appointmentServiceMyBatis.update(appointment);
        //appointment = appointmentServiceMyBatis.readById(2L);
        //log.info(appointment);

        //appointment.setNotes("TEST CREATE: "+ LocalDate.now());
        //appointmentServiceMyBatis.create(appointment);
        //appointmentServiceMyBatis.createWithServiceRequestId(appointment,2L);

        //appointmentServiceMyBatis.deleteById(2L);

        //log.info(serviceRequestServiceMySQL.readAllByForeignKeyId(2L));
        //log.info(serviceRequestServiceMyBatis.readAllByForeignKeyId(2L));

        //ServiceRequest serviceRequest = serviceRequestServiceMyBatis.readById(1L);
        //log.info(serviceRequest);
        //serviceRequest.setNotes("TEST! 1");
        //serviceRequestServiceMyBatis.update(serviceRequest);
        //serviceRequest.setNotes("TEST CREATE! 1");
        //serviceRequestServiceMyBatis.createWithCustomerId(serviceRequest, 2L);
        //log.info(serviceRequestServiceMyBatis.readAllByForeignKeyId(2L));
        //serviceRequestServiceMyBatis.deleteById(5L);

        log.info(serviceRequestServiceMyBatis.readById(1L));
        log.info(appointmentServiceMyBatis.readById(3L));
        log.info(DepositDAOFactory.getDAO(DAOType.MYBATIS).readById(1L));
        log.info(LoanDAOFactory.getDAO(DAOType.MYBATIS).readById(1L));
        log.info(TransactionDAOFactory.getDAO(DAOType.MYBATIS).readById(1L));
    }
}
