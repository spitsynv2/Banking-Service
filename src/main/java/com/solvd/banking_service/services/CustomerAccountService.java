package com.solvd.banking_service.services;

import com.solvd.banking_service.daos.myqsl_impl.*;
import com.solvd.banking_service.models.account.Account;
import com.solvd.banking_service.models.account.Card;
import com.solvd.banking_service.models.customer.Customer;
import com.solvd.banking_service.services.database_connection.MyConnectionPool;
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
        Connection connection = null;

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

        cardDAO.delete(new Card());

        return customer;
    }
}
