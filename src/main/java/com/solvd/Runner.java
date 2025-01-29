package com.solvd;

import com.solvd.daos.myqsl_impl.AddressDAOImpl;
import com.solvd.daos.myqsl_impl.CustomerDAOImpl;
import com.solvd.daos.myqsl_impl.CustomerRepresentativeDAOImpl;
import com.solvd.models.customer.CompanyCustomer;
import com.solvd.models.customer.CustomerRepresentative;
import com.solvd.models.customer.IndividualCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class Runner
{
    private static final Logger log = LogManager.getLogger(Runner.class);

    public static void main(String[] args )
    {
        String URL = "jdbc:mysql://localhost:3306/banking_service";
        String USER = "root";
        String PASSWORD = "root";
        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*
                CustomerDAOImpl customerDAO = new CustomerDAOImpl(connection);
        CustomerRepresentativeDAOImpl customerRepresentativeDAO = new CustomerRepresentativeDAOImpl(connection);
        IndividualCustomer customer = (IndividualCustomer) customerDAO.readById(1L);
        CompanyCustomer customer2 = (CompanyCustomer) customerDAO.readById(3L);

        log.info(customer);
        log.info(customer2);

        customer.setFirstName("TESTING2UPDATE");
        customer.setEmail("TESTING@GAMAIL.COMINDIVI2UPDATE");

        customer2.setCompanyName("TESTING2UPDATE");
        customer2.setEmail("TESTING@GAMAIL.COMCOMPA2UPDATE");
        customer2.setTaxId("TESTING2UPDATE");

        customerDAO.update(customer);
        customerDAO.update(customer2);

        for (CustomerRepresentative representative : customer2.getRepresentatives()) {
            representative.setEmail(representative.getEmail()+"TESTUPDATE");
            customerRepresentativeDAO.update(representative);
        }
         */

        /*
        AddressDAOImpl addressDAO = new AddressDAOImpl(connection);
        log.info(addressDAO.readAllByCustomerId(1L));
         */
    }
}
