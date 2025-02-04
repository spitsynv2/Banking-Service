package com.solvd.banking_service.services.mysql_dao_communications_services;

import com.solvd.banking_service.daos.*;
import com.solvd.banking_service.daos.myqsl_impl.*;
import com.solvd.banking_service.models.AuditLog;
import com.solvd.banking_service.models.ServiceRequest;
import com.solvd.banking_service.models.account.*;
import com.solvd.banking_service.models.customer.Address;
import com.solvd.banking_service.models.customer.Customer;
import com.solvd.banking_service.services.AbstractService;
import com.solvd.banking_service.services.ICustomerAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CustomerAccountService extends AbstractService<Customer, Long> implements ICustomerAccountService {

    private static final Logger log = LogManager.getLogger(CustomerAccountService.class);

    private final ICustomerDAO customerDAO = new CustomerDAOImpl();
    private final IAccountDAO accountDAO = new AccountDAOImpl();
    private final ITransactionDAO transactionDAO = new TransactionDAOImpl();
    private final ILoanDAO loanDAO = new LoanDAOImpl();
    private final IDepositDAO depositDAO = new DepositDAOImpl();
    private final ICardDAO cardDAO = new CardDAOImpl();
    private final IAuditLogDAO auditLogDAO = new AuditLogDAOImpl();
    private final IServiceRequestDAO serviceRequestDAO = new ServiceRequestDAOImpl();
    private final IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
    private final IAddressDAO addressDAO = new AddressDAOImpl();

    public Customer getAllCustomerDataById(Long customerId) {
        Customer customer = customerDAO.readById(customerId);
        if (customer == null) {
            log.error("Cannot find customer with ID {}", customerId);
            return null;
        }

        List<Account> customerAccounts = accountDAO.readAllByForeignKeyId(customerId);
        List<AuditLog> auditLogs = auditLogDAO.readAllByForeignKeyId(customerId);
        List<ServiceRequest> serviceRequests = serviceRequestDAO.readAllByForeignKeyId(customerId);

        for (Account customerAccount : customerAccounts) {
            customerAccount.setTransactions(transactionDAO.readAllByForeignKeyId(customerAccount.getId()));
            customerAccount.setLoans(loanDAO.readAllByForeignKeyId(customerAccount.getId()));
            customerAccount.setDeposits(depositDAO.readAllByForeignKeyId(customerAccount.getId()));
            customerAccount.setCards(cardDAO.readAllByForeignKeyId(customerAccount.getId()));
        }

        for (ServiceRequest serviceRequest : serviceRequests) {
            serviceRequest.setAppointments(appointmentDAO.readAllByForeignKeyId(serviceRequest.getId()));
        }

        customer.setAddresses(addressDAO.readAllByForeignKeyId(customerId));
        customer.setAccounts(customerAccounts);
        customer.setAuditLogs(auditLogs);
        customer.setServiceRequests(serviceRequests);

        return customer;
    }

    @Override
    public void updateAll(Customer customer) {
        log.info("Updating all data in database for customer: {}",customer);
        customerDAO.update(customer);
        for (Address address : customer.getAddresses()) {
            addressDAO.update(address);
        }
        List<Account> accounts = customer.getAccounts();

        for (Account account : accounts) {
            for (Deposit deposit : account.getDeposits()) {
                depositDAO.update(deposit);
            }
            //etc...
        }
        //etc..
    }

    @Override
    public void updateAddress(Customer customer) {
        log.info("Updating all addresses in database for customer: {}",customer);
        customerDAO.update(customer);
        for (Address address : customer.getAddresses()) {
            addressDAO.update(address);
        }
    }

    @Override
    public Customer readById(Long aLong) {
        return getAllCustomerDataById(aLong);
    }

    @Override
    protected IDAO<Customer, Long> getDao() {
        return customerDAO;
    }
}
