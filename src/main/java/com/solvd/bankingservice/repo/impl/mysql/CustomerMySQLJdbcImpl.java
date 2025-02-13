package com.solvd.bankingservice.repo.impl.mysql;

import com.solvd.bankingservice.repo.ICustomerDAO;
import com.solvd.bankingservice.model.customer.CompanyCustomer;
import com.solvd.bankingservice.model.customer.Customer;
import com.solvd.bankingservice.model.customer.CustomerRepresentative;
import com.solvd.bankingservice.model.customer.IndividualCustomer;
import com.solvd.bankingservice.util.MySQLConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
public class CustomerMySQLJdbcImpl extends AbstractMySQLJdbcImpl<Customer, Long> implements ICustomerDAO {

    private static final Logger log = LogManager.getLogger(CustomerMySQLJdbcImpl.class);

    private static final String READ_BY_ID = "SELECT * FROM " + "customers" + " WHERE Id = ?";
    private static final String READ_BY_EMAIL = "SELECT * FROM " + "customers" + " WHERE email = ?";
    private static final String DELETE = "DELETE FROM " + "customers" + " WHERE Id = ?";
    private static final String CREATE_INDIVIDUAL_CUSTOMER = "INSERT INTO " + "customers" + " (customer_type, " +
            "first_name, last_name, email, phone_number, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CREATE_COMPANY_CUSTOMER = "INSERT INTO " + "customers" + " (customer_type, " +
            "company_name, tax_id, email, phone_number, industry, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_INDIVIDUAL_CUSTOMER = "UPDATE " + "customers"+ " SET first_name = ?, last_name = ?, " +
            "email = ?, phone_number = ?, date_of_birth = ? WHERE Id = ?";
    private static final String UPDATE_COMPANY_CUSTOMER = "UPDATE " + "customers" + " SET company_name = ?, tax_id = ?, " +
            "email = ?, phone_number = ?, industry = ?, registration_date = ? WHERE Id = ?";
    private static final String CHECK_TAX_ID_EXISTS = "SELECT COUNT(*) FROM " + "customers" + " WHERE tax_id = ?";
    private static final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM " + "customers" + " WHERE email = ?";


    @Override
    public Customer readById(Long id) {
        Connection connection = null;
        Customer customer = null;

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(READ_BY_ID)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String type = rs.getString("customer_type");

                        if ("individual".equalsIgnoreCase(type)) {
                            customer = mapToIndividual(rs);
                        } else if ("company".equalsIgnoreCase(type)) {
                            customer = mapToCompany(rs);
                        }
                    }
                }
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
            return null;
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        if (customer instanceof CompanyCustomer) {
            CompanyCustomer companyCustomer = (CompanyCustomer) customer;
            CustomerRepresentativeMySQLJdbcImpl representativeDAO = new CustomerRepresentativeMySQLJdbcImpl();
            List<CustomerRepresentative> representatives = representativeDAO.readAllByForeignKeyId(companyCustomer.getId());
            companyCustomer.setRepresentatives(representatives);

            log.info("CompanyCustomer: {} was successfully readById from database", companyCustomer);
            return companyCustomer;
        }else {
            log.info("IndividualCustomer: {} was successfully readById from database", customer);
            return customer;
        }

    }

    @Override
    public Customer readByEmail(String email) {
        Connection connection = null;
        Customer customer = null;

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(READ_BY_EMAIL)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String type = rs.getString("customer_type");

                        if ("individual".equalsIgnoreCase(type)) {
                            customer = mapToIndividual(rs);
                        } else if ("company".equalsIgnoreCase(type)) {
                            customer = mapToCompany(rs);
                        }
                    }
                }
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
            return null;
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        if (customer instanceof CompanyCustomer) {
            CompanyCustomer companyCustomer = (CompanyCustomer) customer;
            CustomerRepresentativeMySQLJdbcImpl representativeDAO = new CustomerRepresentativeMySQLJdbcImpl();
            List<CustomerRepresentative> representatives = representativeDAO.readAllByForeignKeyId(companyCustomer.getId());
            companyCustomer.setRepresentatives(representatives);

            log.info("CompanyCustomer: {} was successfully readByEmail from database", companyCustomer);
            return companyCustomer;
        }else {
            log.info("IndividualCustomer: {} was successfully readByEmail from database", customer);
            return customer;
        }

    }

    @Override
    public void create(Customer customer) {
        Connection connection = null;
        String sql;
        if (customer instanceof IndividualCustomer) {
            sql = CREATE_INDIVIDUAL_CUSTOMER;
        } else if (customer instanceof CompanyCustomer) {
            sql = CREATE_COMPANY_CUSTOMER;
        } else {
            log.error("Unsupported customer type while create");
            return;
        }

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                if (customer instanceof IndividualCustomer) {
                    IndividualCustomer individualCustomer = (IndividualCustomer) customer;
                    stmt.setString(1, "INDIVIDUAL");
                    stmt.setString(2, individualCustomer.getFirstName());
                    stmt.setString(3, individualCustomer.getLastName());
                    stmt.setString(4, individualCustomer.getEmail());
                    stmt.setString(5, individualCustomer.getPhoneNumber());
                    stmt.setDate(6, new Date(individualCustomer.getDateOfBirth().getTime()));
                } else {
                    CompanyCustomer companyCustomer = (CompanyCustomer) customer;
                    stmt.setString(1, "COMPANY");
                    stmt.setString(2, companyCustomer.getCompanyName());
                    stmt.setString(3, companyCustomer.getTaxId());
                    stmt.setString(4, companyCustomer.getEmail());
                    stmt.setString(5, companyCustomer.getPhoneNumber());
                    stmt.setString(6, companyCustomer.getIndustry());
                    stmt.setDate(7, new Date(companyCustomer.getRegistrationDate().getTime()));
                }
                stmt.executeUpdate();
                log.info("Customer was successfully created/inserted to database");
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void update(Customer customer) {
        Connection connection = null;
        String sql;
        if (customer instanceof IndividualCustomer) {
            sql = UPDATE_INDIVIDUAL_CUSTOMER;
        } else if (customer instanceof CompanyCustomer) {
            sql = UPDATE_COMPANY_CUSTOMER;
        } else {
            log.error("Unsupported customer type while update");
            return;
        }

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                if (customer instanceof IndividualCustomer) {
                    IndividualCustomer individualCustomer = (IndividualCustomer) customer;
                    stmt.setString(1, individualCustomer.getFirstName());
                    stmt.setString(2, individualCustomer.getLastName());
                    stmt.setString(3, individualCustomer.getEmail());
                    stmt.setString(4, individualCustomer.getPhoneNumber());
                    stmt.setDate(5, new Date(individualCustomer.getDateOfBirth().getTime()));
                    stmt.setLong(6, individualCustomer.getId());
                } else {
                    CompanyCustomer companyCustomer = (CompanyCustomer) customer;
                    stmt.setString(1, companyCustomer.getCompanyName());
                    stmt.setString(2, companyCustomer.getTaxId());
                    stmt.setString(3, companyCustomer.getEmail());
                    stmt.setString(4, companyCustomer.getPhoneNumber());
                    stmt.setString(5, companyCustomer.getIndustry());
                    stmt.setDate(6, new Date(companyCustomer.getRegistrationDate().getTime()));
                    stmt.setLong(7, companyCustomer.getId());
                }
                stmt.executeUpdate();
                log.info("Customer was successfully updated in database");
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    public void delete(Customer customer) {
        Connection connection = null;

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = connection.prepareStatement(DELETE)) {
                stmt.setLong(1, customer.getId());
                stmt.executeUpdate();
                log.info("Customer was successfully deleted from database");
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    protected String getTableName() {
        return "customers";
    }

    @Override
    public boolean checkTaxIdExists(String taxId){
        Connection connection = null;

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(CHECK_TAX_ID_EXISTS)) {
                statement.setString(1, taxId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
            return false;
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        Connection connection = null;

        try {
            connection = MySQLConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL_EXISTS)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException | InterruptedException e) {
            log.error(e);
            return false;
        } finally {
            if (connection != null) {
                MySQLConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return false;
    }

    @Override
    protected Customer mapResultSetToEntity(ResultSet rs) {
        log.error("Method mapResultSetToEntity is not implemented in CustomerMySQLJdbcImpl, use mapToCompany() or mapToIndividual()");
        return null;
    }

    @Override
    public List<Customer> readAllByForeignKeyId(Long foreignKeyId) {
        log.error("Method readAllByForeignKeyId is not supported in CustomerMySQLJdbcImpl");
        return null;
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        log.error("Method getForeignKeyColumnLabel() is not supported in CustomerMySQLJdbcImpl");
        return null;
    }

    private CompanyCustomer mapToCompany(ResultSet rs) {
        CompanyCustomer companyCustomer = new CompanyCustomer();
        try {
            companyCustomer.setId(rs.getLong("Id"));
            companyCustomer.setEmail(rs.getString("email"));
            companyCustomer.setPhoneNumber(rs.getString("phone_number"));
            companyCustomer.setCompanyName(rs.getString("company_name"));
            companyCustomer.setTaxId(rs.getString("tax_id"));
            companyCustomer.setIndustry(rs.getString("industry"));
            companyCustomer.setRegistrationDate(rs.getDate("registration_date"));
        } catch (SQLException e) {
            log.error(e);
            return null;
        }

        return companyCustomer;
    }

    private IndividualCustomer mapToIndividual(ResultSet rs) {
        IndividualCustomer individualCustomer = new IndividualCustomer();
        try {
            individualCustomer.setId(rs.getLong("Id"));
            individualCustomer.setEmail(rs.getString("email"));
            individualCustomer.setPhoneNumber(rs.getString("phone_number"));
            individualCustomer.setFirstName(rs.getString("first_name"));
            individualCustomer.setLastName(rs.getString("last_name"));
            individualCustomer.setDateOfBirth(rs.getDate("date_of_birth"));
        } catch (SQLException e) {
            log.error(e);
            return null;
        }

        return individualCustomer;
    }
}
