package com.solvd.services.database_connection;

import com.solvd.daos.myqsl_impl.*;
import com.solvd.models.account.Account;
import com.solvd.models.customer.Customer;
import com.solvd.services.MyConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-30
 */
public class CustomerAccountService {

    private static final Logger log = LogManager.getLogger(CustomerAccountService.class);

    public static Customer getCustomerFromDataBase(Long customerId){
        Customer customer;

        String URL = "jdbc:mysql://localhost:3306/banking_service";
        String USER = "root";
        String PASSWORD = "root";
        Connection connection;

        try {
            connection = MyConnectionPool.getConnection();
            log.info("Connection received from connection pool");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CustomerDAOImpl customerDAO = new CustomerDAOImpl(connection);
        AddressDAOImpl addressDAO = new AddressDAOImpl(connection);
        AccountDAOImpl accountDAO = new AccountDAOImpl(connection);
        TransactionDAOImpl transactionDAO = new TransactionDAOImpl(connection);
        LoanDAOImpl loanDAO = new LoanDAOImpl(connection);
        DepositDAO depositDAO = new DepositDAO(connection);
        CardDAOImpl cardDAO = new CardDAOImpl(connection);

        customer = customerDAO.readById(customerId);
        customer.setAddresses(addressDAO.readAllByForeignKeyId(customerId));
        List<Account> customerAccounts = accountDAO.readAllByForeignKeyId(customerId);

        for (Account customerAccount : customerAccounts) {
            customerAccount.setTransactions(transactionDAO.readAllByForeignKeyId(customerAccount.getId()));
            customerAccount.setLoans(loanDAO.readAllByForeignKeyId(customerAccount.getId()));
            customerAccount.setDeposits(depositDAO.readAllByForeignKeyId(customerAccount.getId()));
            customerAccount.setCards(cardDAO.readAllByForeignKeyId(customerAccount.getId()));
        }
        customer.setAccounts(customerAccounts);
        MyConnectionPool.releaseConnection(connection);
        log.info("Connection released to connection pool");
        MyConnectionPool.closeAllConnections();

        return customer;
    }
}
