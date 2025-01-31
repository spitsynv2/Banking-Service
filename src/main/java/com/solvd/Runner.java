package com.solvd;

import com.solvd.models.customer.Customer;
import com.solvd.services.database_connection.CustomerAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args )
    {
        /*
        String URL = "jdbc:mysql://localhost:3306/banking_service";
        String USER = "root";
        String PASSWORD = "root";
        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         */

        //CustomerDAOImpl customerDAO = new CustomerDAOImpl(connection);
        //CustomerRepresentativeDAOImpl customerRepresentativeDAO = new CustomerRepresentativeDAOImpl(connection);
        //AddressDAOImpl addressDAO = new AddressDAOImpl(connection);
        //AccountDAOImpl accountDAO = new AccountDAOImpl(connection);
        //TransactionDAOImpl transactionDAO = new TransactionDAOImpl(connection);
        //LoanDAOImpl loanDAO = new LoanDAOImpl(connection);
        //DepositDAO depositDAO = new DepositDAO(connection);
        //CardDAOImpl cardDAO = new CardDAOImpl(connection);

        //log.info(customerDAO.readById(1L));
        //CompanyCustomer companyCustomer = (CompanyCustomer) customerDAO.readById(3L);
        //log.info((companyCustomer.getRepresentatives()));
        //log.info((customerRepresentativeDAO.readAllByForeignKeyId(3L)));

        //log.info(addressDAO.readAllByForeignKeyId(1L));
        //log.info(accountDAO.readAllByForeignKeyId(1L));
        //log.info(transactionDAO.readAllByForeignKeyId(1L));
        //log.info(loanDAO.readAllByForeignKeyId(3L));
        //log.info(depositDAO.readAllByForeignKeyId(1L));
        //log.info(cardDAO.readAllByForeignKeyId(1L));

        Customer customer = CustomerAccountService.getCustomerFromDataBase(4L);
        log.info(customer);

    }
}
