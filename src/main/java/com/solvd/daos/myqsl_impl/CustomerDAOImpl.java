package com.solvd.daos.myqsl_impl;

import com.solvd.daos.ICustomerDAO;
import com.solvd.models.customer.CompanyCustomer;
import com.solvd.models.customer.Customer;
import com.solvd.models.customer.CustomerRepresentative;
import com.solvd.models.customer.IndividualCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
public class CustomerDAOImpl extends MYSQLImpl<Customer, Long> implements ICustomerDAO {

    private static final Logger log = LogManager.getLogger(CustomerDAOImpl.class);

    public CustomerDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Customer readById(Long id) {
        Customer customer = null;
        String sql = "SELECT * FROM " + getTableName()+ " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (customer instanceof CompanyCustomer) {
            CompanyCustomer companyCustomer = (CompanyCustomer) customer;
            CustomerRepresentativeDAOImpl representativeDAO = new CustomerRepresentativeDAOImpl(connection);
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
        Customer customer = null;
        String sql = "SELECT * FROM " + getTableName()+ " WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("type");

                    if ("individual".equalsIgnoreCase(type)) {
                        customer = mapToIndividual(rs);
                    } else if ("company".equalsIgnoreCase(type)) {
                        customer = mapToCompany(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (customer instanceof CompanyCustomer) {
            CompanyCustomer companyCustomer = (CompanyCustomer) customer;
            CustomerRepresentativeDAOImpl representativeDAO = new CustomerRepresentativeDAOImpl(connection);
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
        String sql;
        if (customer instanceof IndividualCustomer) {
            sql = "INSERT INTO " + getTableName() + " (customer_type, first_name, last_name, email, phone_number, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        } else if (customer instanceof CompanyCustomer) {
            sql = "INSERT INTO " + getTableName() + " (customer_type, company_name, tax_id, email, phone_number, industry, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else {
            throw new IllegalArgumentException("Unsupported customer type");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (customer instanceof IndividualCustomer) {
                IndividualCustomer individualCustomer = (IndividualCustomer) customer;
                stmt.setString(1, "INDIVIDUAL");
                stmt.setString(2, individualCustomer.getFirstName());
                stmt.setString(3, individualCustomer.getLastName());
                stmt.setString(4, individualCustomer.getEmail());
                stmt.setString(5, individualCustomer.getPhoneNumber());
                stmt.setDate(6, new java.sql.Date(individualCustomer.getDateOfBirth().getTime()));
            } else {
                CompanyCustomer companyCustomer = (CompanyCustomer) customer;
                stmt.setString(1, "COMPANY");
                stmt.setString(2, companyCustomer.getCompanyName());
                stmt.setString(3, companyCustomer.getTaxId());
                stmt.setString(4, companyCustomer.getEmail());
                stmt.setString(5, companyCustomer.getPhoneNumber());
                stmt.setString(6, companyCustomer.getIndustry());
                stmt.setDate(7, new java.sql.Date(companyCustomer.getRegistrationDate().getTime()));
            }
            stmt.executeUpdate();
            log.info("Customer was successfully created/inserted to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        String sql;
        if (customer instanceof IndividualCustomer) {
            sql = "UPDATE " + getTableName() + " SET first_name = ?, last_name = ?, email = ?, phone_number = ?, date_of_birth = ? WHERE id = ?";
        } else if (customer instanceof CompanyCustomer) {
            sql = "UPDATE " + getTableName() + " SET company_name = ?, tax_id = ?, email = ?, phone_number = ?, industry = ?, registration_date = ? WHERE id = ?";
        } else {
            throw new IllegalArgumentException("Unsupported customer type");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (customer instanceof IndividualCustomer) {
                IndividualCustomer individualCustomer = (IndividualCustomer) customer;
                stmt.setString(1, individualCustomer.getFirstName());
                stmt.setString(2, individualCustomer.getLastName());
                stmt.setString(3, individualCustomer.getEmail());
                stmt.setString(4, individualCustomer.getPhoneNumber());
                stmt.setDate(5, new java.sql.Date(individualCustomer.getDateOfBirth().getTime()));
                stmt.setLong(6, individualCustomer.getId());
            } else {
                CompanyCustomer companyCustomer = (CompanyCustomer) customer;
                stmt.setString(1, companyCustomer.getCompanyName());
                stmt.setString(2, companyCustomer.getTaxId());
                stmt.setString(3, companyCustomer.getEmail());
                stmt.setString(4, companyCustomer.getPhoneNumber());
                stmt.setString(5, companyCustomer.getIndustry());
                stmt.setDate(6, new java.sql.Date(companyCustomer.getRegistrationDate().getTime()));
                stmt.setLong(7, companyCustomer.getId());
            }
            stmt.executeUpdate();
            log.info("Customer was successfully updated in database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Customer customer) {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, customer.getId());
            stmt.executeUpdate();
            log.info("Customer was successfully deleted from database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String getTableName() {
        return "customers";
    }

    @Override
    public boolean checkTaxIdExists(String taxId){
        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE tax_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, taxId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    protected Customer mapResultSetToEntity(ResultSet rs) {
        throw new UnsupportedOperationException("Method not implemented in CustomerDAOImpl, use mapToCompany() or mapToIndividual()");
    }

    @Override
    public List<Customer> readAllByForeignKeyId(Long foreignKeyId) {
        throw new UnsupportedOperationException("Method not implemented in CustomerDAOImpl");
    }

    @Override
    protected String getForeignKeyColumnLabel() {
        throw new UnsupportedOperationException("Method not implemented in CustomerDAOImpl");
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }

        return individualCustomer;
    }
}
