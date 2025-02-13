package com.solvd.bankingservice.service.mysql;

import com.solvd.bankingservice.repo.*;
import com.solvd.bankingservice.repo.impl.mysql.*;
import com.solvd.bankingservice.model.AuditLog;
import com.solvd.bankingservice.model.ServiceRequest;
import com.solvd.bankingservice.model.account.*;
import com.solvd.bankingservice.model.customer.Address;
import com.solvd.bankingservice.model.customer.Customer;
import com.solvd.bankingservice.service.AbstractService;
import com.solvd.bankingservice.service.ICustomerAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CustomerAccountService extends AbstractService<Customer, Long> implements ICustomerAccountService {

    private static final Logger log = LogManager.getLogger(CustomerAccountService.class);

    private final ICustomerDAO customerDAO = new CustomerMySQLJdbcImpl();
    private final IAccountDAO accountDAO = new AccountMySQLJdbcImpl();
    private final ITransactionDAO transactionDAO = new TransactionMySQLJdbcImpl();
    private final ILoanDAO loanDAO = new LoanMySQLJdbcImpl();
    private final IDepositDAO depositDAO = new DepositMySQLJdbcImpl();
    private final ICardDAO cardDAO = new CardMySQLJdbcImpl();
    private final IAuditLogDAO auditLogDAO = new AuditLogMySQLJdbcImpl();
    private final IServiceRequestDAO serviceRequestDAO = new ServiceRequestMySQLJdbcImpl();
    private final IAppointmentDAO appointmentDAO = new AppointmentMySQLJdbcImpl();
    private final IAddressDAO addressDAO = new AddressMySQLJdbcImpl();

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
